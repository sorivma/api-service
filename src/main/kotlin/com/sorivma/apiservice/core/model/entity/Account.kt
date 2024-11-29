package com.sorivma.apiservice.core.model.entity

import jakarta.persistence.*
import org.example.antifraudapi.rest.models.AccountStatus
import org.example.antifraudapi.rest.models.AccountType
import java.util.*

@Entity
@Table(name = "api_accounts")
data class Account(
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(nullable = false)
    val balance: Double,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: AccountStatus,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val accountType: AccountType,

    @Column(nullable = false, length = 3)
    val currency: Currency
): AuditableEntity()