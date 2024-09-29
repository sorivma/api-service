package com.sorivma.apiservice.core.model.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

@MappedSuperclass
abstract class AuditableEntity : BaseEntity() {
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(nullable = false, updatable = true)
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @PrePersist
    fun onCreate() {
        createdAt = LocalDateTime.now()
        updatedAt = createdAt
    }

    @PreUpdate
    fun onUpdate() {
        updatedAt = LocalDateTime.now()
    }
}