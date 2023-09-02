package io.nopecho.search.autocomplete.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "search")
class SearchProperties(
    var autoComplete: AutoCompleteProperties
)