package com.sorivma.apiservice.core.service.impl

import com.sorivma.apiservice.core.service.JpaServiceException.ExceptionExtensions.requiredEntity
import java.util.*

abstract class BaseJpaService {
    open val entity: String? = null

    fun <I, T> Optional<T>.required(id: I): T {
        entity?.let {
            return this.requiredEntity(id, it)
        }
        return this.requiredEntity(id)
    }
}