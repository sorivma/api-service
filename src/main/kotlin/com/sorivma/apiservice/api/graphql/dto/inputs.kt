package com.sorivma.apiservice.api.graphql.dto

import com.sorivma.apiservice.core.model.PaymentMethod
import com.sorivma.apiservice.core.model.TransactionStatus
import com.sorivma.apiservice.core.model.dto.TransactionDTO

data class IncomingTransactionInput(
    val amount: Double,
    val status: TransactionStatus? = TransactionStatus.PENDING,
    val payerId: String,
    val paymentMethod: PaymentMethod
)

data class OutgoingTransactionInput(
    val amount: Double,
    val status: TransactionStatus? = TransactionStatus.PENDING,
    val payeeId: String,
    val paymentMethod: PaymentMethod
)

fun IncomingTransactionInput.toDTO(payeeId: String) = TransactionDTO(
    amount = this.amount,
    status = this.status,
    payeeId = payeeId,
    payerId = this.payerId,
    paymentMethod = this.paymentMethod
)

fun OutgoingTransactionInput.toDTO(payerId: String) = TransactionDTO(
    amount = this.amount,
    status = this.status,
    payeeId = payeeId,
    payerId = payerId,
    paymentMethod = this.paymentMethod
)