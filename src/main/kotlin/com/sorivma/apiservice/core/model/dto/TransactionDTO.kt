package com.sorivma.apiservice.core.model.dto

import org.example.antifraudapi.rest.models.PaymentMethod
import org.example.antifraudapi.rest.models.TransactionStatus


data class TransactionDTO (
    val id: String? = null,
    val amount: Double,
    val status: TransactionStatus? = null,
    val payerId: String,
    val payeeId: String,
    val paymentMethod: PaymentMethod
)