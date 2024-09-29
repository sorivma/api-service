package com.sorivma.apiservice.core.model.entity

import com.sorivma.apiservice.core.model.PaymentMethod
import com.sorivma.apiservice.core.model.TransactionStatus
import jakarta.persistence.*

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