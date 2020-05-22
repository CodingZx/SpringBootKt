package lol.cicco.demo.common.exception

import java.lang.RuntimeException

class UnknownException(throwable: Throwable) : RuntimeException(throwable) {

    companion object {
        fun make(throwable: Throwable): UnknownException {
            return UnknownException(throwable)
        }
    }
}