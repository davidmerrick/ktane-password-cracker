package com.merricklabs.ktane

class Trie {
    val root = Node('*', mutableMapOf())

    fun insert(word: String){
        val chars = word.toCharArray()
        var node = root

        for(i in 0 until chars.size) {
            val key = chars[i]
            node.children.putIfAbsent(key, Node(key, mutableMapOf()))
            node = node.children[key]!!
        }
    }
}