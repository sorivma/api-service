package com.sorivma.apiservice.core.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "api_user")
data class User(
    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @OneToMany(mappedBy = "payer")
    val transactions: List<Transaction> = mutableListOf(),
) : AuditableEntity()
