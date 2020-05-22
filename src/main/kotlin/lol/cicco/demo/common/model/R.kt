package lol.cicco.demo.common.model

data class R(val code: Int, val message: String, val data: Any?) {

    companion object {
        private const val SUCCESS = 200
        private const val OTHER = 400
        private const val ERROR = 500
        private const val LOGIN = 999

        fun ok(): R {
            return ok(null)
        }

        fun ok(data: Any?): R {
            return R(SUCCESS, "success", data)
        }

        fun other(message: String): R {
            return R(OTHER, message, null)
        }

        fun error(message: String): R {
            return R(ERROR, message, null)
        }

        fun login(): R {
            return R(LOGIN, "用户信息失效", null)
        }
    }
}