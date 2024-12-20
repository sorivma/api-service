package com.sorivma.apiservice.core.model.dto

import org.example.antifraudapi.rest.models.TransactionStatus

data class TransactionCheckResultDto(
    val transactionId: String,
    val status: TransactionStatus,
    val message: String,
    val payerId: String,
    val payeeId: String
)