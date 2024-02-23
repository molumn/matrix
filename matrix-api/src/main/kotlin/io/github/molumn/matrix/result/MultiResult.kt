package io.github.molumn.matrix.result

class MultiResult<out T> private constructor(
    private val code : UInt,
    val value : T? = null
) {

    companion object {
        fun <T> of(statusCode: UInt, value: T) : MultiResult<T> = MultiResult(statusCode, value)
        fun of(statusCode: UInt) : MultiResult<Nothing> = MultiResult(statusCode)
        fun <T> success(value: T) : MultiResult<T> = MultiResult(StatusCode.SUCCESS, value)
        fun failure() : MultiResult<Nothing> = MultiResult(StatusCode.FAILURE)
    }

    object StatusCode {
        const val SUCCESS : UInt = 0x80000000u
        const val FAILURE : UInt = 0u
    }

    private fun succeed() : Boolean = status(StatusCode.SUCCESS)
    private fun failed() : Boolean = status(StatusCode.FAILURE)

    fun whenSuccess(callback: (MultiResult<T>) -> Unit) : MultiResult<T> {
        if (succeed()) also(callback)
        return this
    }
    fun whenFailure(callback: (MultiResult<T>) -> Unit) : MultiResult<T> {
        if (failed()) also(callback)
        return this
    }


    private fun status(statusCode: UInt) : Boolean = code == statusCode
    fun whenStatus(statusCode: UInt, callback: (MultiResult<T>) -> Unit) : MultiResult<T> {
        if (status(statusCode)) also(callback)
        return this
    }

}