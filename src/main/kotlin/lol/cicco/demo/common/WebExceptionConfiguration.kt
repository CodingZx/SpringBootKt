package lol.cicco.demo.common

import lol.cicco.demo.common.exception.BusinessException
import lol.cicco.demo.common.exception.UnknownException
import lol.cicco.demo.common.model.R
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import kotlin.reflect.KClass

@ResponseBody
@Configuration
@ControllerAdvice
class WebExceptionConfiguration {
    private val log = LoggerFactory.getLogger(this.javaClass)

    companion object {
        private val ignoreExceptions = mutableListOf<KClass<out Throwable>>()
    }

    init {
        ignoreExceptions.add(MissingServletRequestParameterException::class)
        ignoreExceptions.add(HttpRequestMethodNotSupportedException::class)
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): R {
        if (!ignoreExceptions.contains(exception::class)) {
            log.warn(exception.message, exception)
        }
        return R.error(exception.message ?: "System Error")
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException::class, MethodArgumentNotValidException::class, BusinessException::class)
    fun handleValidationFailure(ex: Exception): R {
        val message = when (ex) {
            is BindException -> ex.fieldError?.defaultMessage
            is MethodArgumentNotValidException -> ex.bindingResult.fieldError?.defaultMessage
            is BusinessException -> ex.message
            else -> throw UnknownException.make(ex)
        }
        return R.other(message ?: "ValidationFailure")
    }
}