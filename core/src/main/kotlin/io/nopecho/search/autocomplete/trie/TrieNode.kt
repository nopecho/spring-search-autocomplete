package io.nopecho.search.autocomplete.trie

data class TrieNode(
    var value: Char? = null,
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isComplete: Boolean = false,
    var frequency: Int = 0
)