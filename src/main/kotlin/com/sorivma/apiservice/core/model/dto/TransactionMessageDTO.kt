package com.sorivma.apiservice.core.model.dto

data class TransactionMessageDTO(
    val payer: UserDTO,
    val payee: UserDTO,
    val transaction: TransactionDTO
)
