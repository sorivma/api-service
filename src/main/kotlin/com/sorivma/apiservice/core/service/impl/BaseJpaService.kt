package com.sorivma.apiservice.core.service.impl

import com.sorivma.apiservice.core.service.JpaServiceException.ExceptionExtensions.requiredEntity
import java.util.*

abstract class BaseJpaService {
    abstract val entity: String

    fun <I, T> Optional<T>.required(id: I) = this.requiredEntity(id, entity)
}