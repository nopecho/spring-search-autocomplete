package io.nopecho.search.autocomplete.config

import io.nopecho.search.autocomplete.trie.SimpleTrie

data class AutoCompleteDictionary(
    private val trie: SimpleTrie = SimpleTrie(),
) {

    fun setup(keywords: List<String>) {
        trie.insert(keywords)
    }

    fun setupLowCase(keywords: List<String>) {
        val lowercaseKeywords = keywords.map { it.lowercase() }
        trie.insert(lowercaseKeywords)
    }

    fun searchWithPrefix(keyword: String, limit: Int = 20): List<String> {
        return trie.searchWithPrefix(keyword, limit)
    }

    fun search(keyword: String, limit: Int = 20): List<String> {
        return trie.search(keyword, limit)
    }
}