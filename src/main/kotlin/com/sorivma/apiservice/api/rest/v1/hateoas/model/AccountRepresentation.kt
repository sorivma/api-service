package com.sorivma.apiservice.api.rest.v1.hateoas.model

import com.sorivma.apiservice.core.model.AccountStatus
import com.sorivma.apiservice.core.model.AccountType
import org.springframework.hateoas.RepresentationModel

data class AccountRepresentation(
    val id: String? = null,
    val balance: Double,
    val status: AccountStatus,
    val accountType: AccountType,
    val currency: String
): RepresentationModel<AccountRepresentation>()
