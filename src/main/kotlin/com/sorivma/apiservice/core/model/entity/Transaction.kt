package com.sorivma.apiservice.core.model.entity

import jakarta.persistence.*
import org.example.antifraudapi.rest.models.PaymentMethod
import org.example.antifraudapi.rest.models.TransactionStatus

@Entity
@Table(name = "api_transactions")
data class Transaction(
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payer_id")
    val payer: User,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payee_id")
    val payee: User,

    @Column(nullable = false)
    val amount: Double,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: TransactionStatus,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val paymentMethod: PaymentMethod,
) : AuditableEntity()