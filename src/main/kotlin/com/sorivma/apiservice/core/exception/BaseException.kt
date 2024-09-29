package com.sorivma.apiservice.core.exception

open class BaseException(
    override val message: String?,
    override val cause: Throwable?,
) : Throwable(message, cause) {

    protected open val messagePrefix: String
        get() = "Core exception: "

    private fun withErrorPrefix(message: String? = null): String {
        val prefix = messagePrefix
        var output = prefix
        if (!message.isNullOrBlank()) output = "$output : $message"
        return output
    }

    override fun toString(): String {
        return withErrorPrefix(messagePrefix)
    }

    companion object {
        fun default(message: String? = null, cause: Throwable? = null): BaseException {
            return BaseException(message, cause)
        }
    }
}