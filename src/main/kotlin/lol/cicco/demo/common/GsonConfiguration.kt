package lol.cicco.demo.common

import com.google.gson.Gson
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import lol.cicco.demo.common.util.gson

@Configuration
class GsonConfiguration {

    @Bean
    fun gson(): Gson {
        return gson
    }
}