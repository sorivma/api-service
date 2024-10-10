package com.sorivma.apiservice.core.service

import com.sorivma.apiservice.core.exception.BaseException
import com.sorivma.apiservice.util.extensions.OptionalExtensions.requiredValue
import java.util.*

open class JpaServiceException(
    override val message: String?,
    override val cause: Throwable?
) : BaseException(message, cause) {
    override val messagePrefix: String
        get() = "JPA Service exception: "

    class NoEntityWithId(
        message: String? = null,
        cause: Throwable? = null,
        val entity: String?,
        val id: String
        ) : JpaServiceException(message, cause)

    companion object {
        fun <I> noEntityWithId(id: I, entity: String): NoEntityWithId {
            return NoEntityWithId(
                "No entity [${entity}] with specified id [$id]",
                entity = entity,
                id = id.toString()
            )
        }

        fun <I> noEntityWithId(id: I): NoEntityWithId {
            return NoEntityWithId(
                "No entity with specified id [$id]",
                entity = null,
                id = id.toString()
            )
        }

        fun default(message: String? = null, cause: Throwable? = null): JpaServiceException {
            return JpaServiceException(message, cause)
        }
    }

    object ExceptionExtensions {
        fun <I, T> Optional<T>.requiredEntity(id: I, entity: String): T = this.requiredValue(noEntityWithId(id, entity))
        fun <I, T> Optional<T>.requiredEntity(id: I): T = this.requiredValue(noEntityWithId(id))
    }
}