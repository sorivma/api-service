package com.sorivma.apiservice.api.graphql.dto

import com.sorivma.apiservice.core.model.dto.AccountDTO
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.model.dto.UserDTO

data class UserAggregate(
    val id: String,
    val name: String,
    val email: String,
    val account: AccountDTO? = null,
    val incomingTransactions: List<TransactionDTO> = emptyList(),
    val outcomingTransactions: List<TransactionDTO> = emptyList()
) {
    constructor(userDTO: UserDTO) : this(
        userDTO.id,
        userDTO.name,
        userDTO.email
        )
}
