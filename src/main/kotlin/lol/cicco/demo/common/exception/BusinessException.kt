package lol.cicco.demo.common.exception

import java.lang.RuntimeException

class BusinessException(override val message: String) : RuntimeException(message) {

    companion object {
        fun make(message: String): BusinessException {
            return BusinessException(message)
        }
    }
}