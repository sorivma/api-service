package com.sorivma.apiservice.util.extensions

import org.example.antifraudapi.rest.exceiption.ResourceNotFoundException
import java.util.*

object OptionalExtensions {
    fun <T> Optional<T>.requiredValue(exception: Throwable): T {
        return this.orElseThrow { exception }
    }

    fun <T> Optional<T>.requiredEntity(id: String, entity: String): T = requiredValue(
        ResourceNotFoundException(
            entity = entity,
            id = id
        )
    )

    fun <T> Optional<T>.required(id: String): T = requiredValue(
        ResourceNotFoundException(
            id = id,
            entity = null
        )
    )
}