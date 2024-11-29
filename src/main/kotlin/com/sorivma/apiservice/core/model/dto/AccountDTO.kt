package com.sorivma.apiservice.core.model.dto

import org.example.antifraudapi.rest.models.AccountStatus
import org.example.antifraudapi.rest.models.AccountType


data class AccountDTO(
    val userId: String,
    val balance: Double,
    val status: AccountStatus,
    val accountType: AccountType,
    val currencyCode: String,
)
