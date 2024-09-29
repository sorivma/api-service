package com.sorivma.apiservice.util.extensions

import java.util.*

object OptionalExtensions {
    fun <T> Optional<T>.requiredValue(exception: Throwable): T {
        return this.orElseThrow { exception }
    }
}