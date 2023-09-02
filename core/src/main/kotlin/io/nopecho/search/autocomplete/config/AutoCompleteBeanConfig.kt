package io.nopecho.search.autocomplete.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AutoCompleteBeanConfig(
    val searchProperties: SearchProperties
) {
    @Bean
    fun autoCompleteDictionary(): AutoCompleteDictionary {
        val dictionary = AutoCompleteDictionary()
        dictionary.setup(searchProperties.autoComplete.keywords)

        return dictionary
    }
}