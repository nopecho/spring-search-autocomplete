# StringAutoComplete

## setup

* build.gradle
```groovy
dependencies {
    //
    annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'
    //
}
```

* application.yml

```yaml
search:
  auto-complete:
    keywords:
      - 자동 완성 하려는
      - 단어 설정
      - 추가 가능
```

* spring bean register
```kotlin
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
```

## usage

빈으로 등록한 `AutoCompleteDictionary`에서 특정 단어에 대한 문자열 검색을 합니다.
* `searchWithPrefix` 함수는 사전에서 접미사가 포함된 모든 문자열을 List 형태로 반환 합니다.
* `search` 함수는 사전에서 특정 단어가 포함된 모든 문자열을 List 형태로 반환 합니다.

### example

```kotlin
@Component
class AutoCompleteService(
    private val dictionary: AutoCompleteDictionary
) {

    fun searchWithPrefix(keyword: String) {
        // ..
        val searchResults = dictionary.searchWithPrefix(keyword)
        // ..
    }

    fun search(keyword: String) {
        // ..
        val searchResults = dictionary.search(keyword)
        // ..
    }
}
```