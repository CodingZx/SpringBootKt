package lol.cicco.demo.common

import com.google.common.collect.Lists
import lol.cicco.demo.common.util.gson
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.GsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.charset.StandardCharsets

@Configuration
class WebMvcConfiguration : WebMvcConfigurer {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.removeIf { converter: HttpMessageConverter<*>? -> converter is GsonHttpMessageConverter }
        val httpMessageConverter = GsonHttpMessageConverter(gson)
        httpMessageConverter.defaultCharset = StandardCharsets.UTF_8
        httpMessageConverter.supportedMediaTypes = Lists.newArrayList(MediaType.APPLICATION_JSON)
        converters.add(0, httpMessageConverter)
    }
}