package com.sorivma.apiservice.core.model.dto

import com.sorivma.apiservice.core.model.AccountType

data class RegistrationDto(
    val name: String,
    val email: String,
    val currencyCode: String,
    val accountType: AccountType
)
