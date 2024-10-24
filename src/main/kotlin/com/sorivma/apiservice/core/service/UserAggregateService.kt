package com.sorivma.apiservice.core.service

import com.sorivma.apiservice.core.model.dto.AccountDTO
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.model.dto.UserDTO
import java.util.*

interface UserAggregateService {
    fun getUserById(userId: UUID): UserDTO
    fun getUsers(): List<UserDTO>
    fun getIncomingTransactions(userId: UUID): List<TransactionDTO>
    fun getOutcomingTransactions(userId: UUID): List<TransactionDTO>
    fun getUserAccount(userId: UUID): AccountDTO
}