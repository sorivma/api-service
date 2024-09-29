package com.sorivma.apiservice.core.model.dto

import com.sorivma.apiservice.core.model.PaymentMethod
import com.sorivma.apiservice.core.model.TransactionStatus

data class TransactionDTO (
    val id: String? = null,
    val amount: Double,
    val status: TransactionStatus? = null,
    val payerId: String,
    val payeeId: String,
    val paymentMethod: PaymentMethod
)