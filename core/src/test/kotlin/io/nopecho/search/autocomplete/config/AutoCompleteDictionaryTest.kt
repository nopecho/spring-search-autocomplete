package io.nopecho.search.autocomplete.config

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.util.StopWatch

@ActiveProfiles("test")
@SpringBootTest
class AutoCompleteDictionaryTest {

    @Autowired
    lateinit var dictionary: AutoCompleteDictionary

    private val watch: StopWatch = StopWatch()

    @Test
    fun searchWithPrefix() {
        watch.start("searchWithPrefix")
        val actual = dictionary.searchWithPrefix("a", 1000)
        watch.stop()
        printTime(watch)

        println(actual)
        assertThat(actual).isNotEmpty
    }

    @Test
    fun search() {
        watch.start("search")
        val actual = dictionary.search("ab", 1000)
        watch.stop()
        printTime(watch)

        println(actual)
        assertThat(actual).isNotEmpty
    }

    private fun printTime(watch: StopWatch) {
        println("${watch.lastTaskName} -> ${watch.totalTimeMillis} ms")
    }
}