package io.nopecho.search.autocomplete.trie

import java.util.*

data class SimpleTrie(
    private val root: TrieNode = TrieNode()
) {

    private fun insert(keyword: String) {
        var current = root
        keyword.forEach { current = current.children.getOrPut(it) { TrieNode(it) } }
        current.isComplete = true
        current.frequency++
    }

    fun insert(keywords: List<String>) {
        keywords.forEach { insert(it) }
    }

    fun searchWithPrefix(prefix: String, limit: Int = 20): List<String> {
        val results = PriorityQueue<WordFrequency>(compareBy { -it.frequency })

        fun dfs(node: TrieNode, path: String) {
            if (node.isComplete) {
                results.offer(WordFrequency(path, node.frequency))
            }
            node.children.forEach { dfs(it.value, path + it.value.value) }
        }

        var current = root
        prefix.forEach { current = current.children[it] ?: return emptyList() }

        dfs(current, prefix)
        return results.take(limit).map { it.word }
    }

    fun search(keyword: String, limit: Int = 20): List<String> {
        val results = PriorityQueue<WordFrequency>(compareBy { -it.frequency })

        fun dfs(node: TrieNode, path: String) {
            if (node.isComplete && path.contains(keyword)) {
                results.offer(WordFrequency(path, node.frequency))
            }
            node.children.forEach { dfs(it.value, path + it.value.value) }
        }

        dfs(root, "")
        return results.take(limit).map { it.word }
    }
}