package io.nopecho.search.autocomplete.config

import io.nopecho.search.autocomplete.trie.AutoCompleteTrie

data class AutoCompleteDictionary(
    private val trie: AutoCompleteTrie = AutoCompleteTrie(),
) {

    fun setup(keywords: List<String>) {
        keywords.forEach { it.lowercase() }
        trie.insert(keywords)
    }

    fun setupLowCase(keywords: List<String>) {
        val lower = keywords.map { it.lowercase() }
        trie.insert(lower)
    }

    fun searchWithPrefix(keyword: String, limit: Int = 20): List<String> {
        return trie.searchWithPrefix(keyword, limit)
    }

    fun search(keyword: String, limit: Int = 20): List<String> {
        return trie.search(keyword, limit)
    }
}