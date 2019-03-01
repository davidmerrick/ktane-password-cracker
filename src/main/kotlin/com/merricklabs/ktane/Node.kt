package com.merricklabs.ktane

data class Node(val value: Char, var children: MutableMap<Char, Node>){
    fun isRoot() = value != '*'
}