package com.sorivma.apiservice.core.service

import com.sorivma.apiservice.core.model.TransactionStatus
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface TransactionService {
    fun createTransaction(transactionDTO: TransactionDTO): TransactionDTO
    fun updateTransactionStatus(transactionId: UUID, status: TransactionStatus)
    fun getTransaction(transactionId: UUID): TransactionDTO
    fun getTransactions(pageable: Pageable): Page<TransactionDTO>
    fun getTransactions(userId: UUID, pageable: Pageable): Page<TransactionDTO>
    fun getIncomingTransactions(userId: UUID, pageable: Pageable): Page<TransactionDTO>
}