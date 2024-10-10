package com.sorivma.apiservice.core.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface UserAggregateService {
    fun getUserAggregate(userId: UUID, pageable: Pageable): UserAggregate
    fun getUserAggregate(pageable: Pageable): Page<UserAggregate>
    fun getPayerAggregate(userId: UUID, pageable: Pageable): UserAggregate
    fun getPayeeAggregate(userId: UUID, pageable: Pageable): UserAggregate
}