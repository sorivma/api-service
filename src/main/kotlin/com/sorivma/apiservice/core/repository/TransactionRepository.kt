package com.sorivma.apiservice.core.repository

import com.sorivma.apiservice.core.model.entity.Transaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction, UUID> {
    fun getTransactionByPayerId(payerId: UUID, pageable: Pageable): Page<Transaction>
    fun getTransactionsByPayeeId(payeeId: UUID, pageable: Pageable): Page<Transaction>
}