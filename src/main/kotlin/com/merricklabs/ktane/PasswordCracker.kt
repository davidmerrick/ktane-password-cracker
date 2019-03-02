package com.merricklabs.ktane

import java.util.Stack

data class PasswordCracker(val columns: List<String>) {

    val words = listOf(
            "about", "after", "again", "below", "could",
            "every", "first", "found", "great", "house",
            "large", "learn", "never", "other", "place",
            "plant", "point", "right", "small", "sound",
            "spell", "still", "study", "their", "there",
            "these", "thing", "think", "three", "water",
            "where", "which", "world", "would", "write"
    )

    fun crackPassword(): String {
        // Build a Trie out of the valid words
        val trie = Trie()
        words.forEach { trie.insert(it) }

        // Perform a depth-first search of the tree
        val visited = mutableListOf<Node>()
        var node = trie.root

        val stack = Stack<Node>()
        stack.push(node)
        var columnIndex = 0
        while(stack.size < columns.size + 1){ // +1 to account for root node
            // Have we visited everything here?
            if(shouldBacktrack(node, columns[columnIndex], visited)){
                stack.pop()
                node = stack.peek()
                columnIndex--
                check(columnIndex >= 0)
            } else {
                // Visit stuff here
                val validChars = columns[columnIndex].toSet().intersect(node.children.keys).toList()
                for(i in 0 until validChars.size){
                    val child = node.children[validChars[i]]
                    if(!visited.contains(child)){
                        node = child!!
                        columnIndex++
                        check(columnIndex <= columns.size)
                        stack.push(node)
                        visited.add(node)
                        break
                    }
                }
            }
        }

        return stack.asSequence().filter { it.value != trie.root.value }
                .map { it.value.toString() }
                .reduce { a, b -> "$a$b" }
    }

    fun shouldBacktrack(node: Node, column: String, visited: List<Node>): Boolean {
        // If we've visited all the nodes, or if none of the nodes are in the column, backtrack
        val keySet = node.children.keys
        val columnSet = column.toSet()
        val noValidNodes = keySet.intersect(columnSet).isEmpty()

        val visitedSet = visited.toSet()
        val nodeSet = node.children.values.toSet()
        val allVisited = visitedSet.intersect(nodeSet) == nodeSet
        return noValidNodes || allVisited
    }
}

fun main(args: Array<String>) {
    val columns = listOf(
            "dcwpjz",
            "niexrg",
            "nidsbz",
            "xxxtxx",
            "xxexxx"
    )
    val cracker = PasswordCracker(columns)
    println("Solution: ${cracker.crackPassword()}")
}