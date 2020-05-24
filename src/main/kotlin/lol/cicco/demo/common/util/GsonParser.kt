package lol.cicco.demo.common.util

import com.google.gson.*
import lol.cicco.demo.common.annotation.GsonIgnore
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val DEFAULT_DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss"
const val DEFAULT_DATE_FORMATTER = "yyyy-MM-dd"

private class LocalDateTimeSerializer : JsonSerializer<LocalDateTime> {
    override fun serialize(src: LocalDateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMATTER)))
    }
}

private class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDateTime {
        return LocalDateTime.parse(json.asJsonPrimitive.asString, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMATTER))
    }
}

private class LocalDateSerializer : JsonSerializer<LocalDate> {
    override fun serialize(src: LocalDate, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER)))
    }
}

private class LocalDateDeserializer : JsonDeserializer<LocalDate> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDate {
        return LocalDate.parse(json.asJsonPrimitive.asString, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATTER))
    }
}

private class AnnotationExclude : ExclusionStrategy {
    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return false
    }
    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return f.getAnnotation(GsonIgnore::class.java) != null
    }
}

val gson : Gson = GsonBuilder()
        .disableHtmlEscaping()
        .setDateFormat(DEFAULT_DATE_TIME_FORMATTER)
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeSerializer())
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
        .registerTypeAdapter(LocalDate::class.java, LocalDateSerializer())
        .registerTypeAdapter(LocalDate::class.java, LocalDateDeserializer())
        .setExclusionStrategies(AnnotationExclude())
        .create()


