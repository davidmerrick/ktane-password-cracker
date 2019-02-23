package com.merricklabs.ktane

class Node(val value: String, var children: MutableMap<String, Node>) {
    fun insert(key: String, node: Node) = children.putIfAbsent(key, node)
}