package com.sorivma.apiservice.core.model.dto

import com.sorivma.apiservice.core.model.AccountStatus
import com.sorivma.apiservice.core.model.AccountType

data class AccountDTO(
    val userId: String,
    val balance: Double,
    val status: AccountStatus,
    val accountType: AccountType,
    val currencyCode: String,
)
