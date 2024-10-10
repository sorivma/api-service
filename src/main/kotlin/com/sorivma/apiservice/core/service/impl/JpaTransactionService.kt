package com.sorivma.apiservice.core.service.impl

import com.sorivma.apiservice.core.model.TransactionStatus
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.model.entity.Transaction
import com.sorivma.apiservice.core.model.mapper.impl.TransactionMapper
import com.sorivma.apiservice.core.repository.TransactionRepository
import com.sorivma.apiservice.core.service.JpaServiceException.ExceptionExtensions.requiredEntity
import com.sorivma.apiservice.core.service.TransactionService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class JpaTransactionService(
    private val transactionRepository: TransactionRepository,
    private val transactionMapper: TransactionMapper
) : TransactionService {

    private fun TransactionDTO.toEntity() = transactionMapper.toEntity(this)
    private fun Transaction.toDTO() = transactionMapper.toDTO(this)

    override fun createTransaction(transactionDTO: TransactionDTO): TransactionDTO {
        return transactionRepository.save(
            transactionDTO.toEntity().copy(status = DEFAULT_TRANSACTION_STATUS)
        ).toDTO()
    }

    override fun updateTransactionStatus(transactionId: UUID, status: TransactionStatus) {
        transactionRepository.save(
            transactionRepository.findById(transactionId)
                .requiredEntity(transactionId, ENTITY)
                .copy(status = status)
        )
    }

    override fun getTransaction(transactionId: UUID): TransactionDTO {
        return transactionRepository.findById(transactionId)
            .requiredEntity(transactionId, ENTITY).toDTO()
    }

    override fun getTransactions(pageable: Pageable): Page<TransactionDTO> {
        return transactionRepository.findAll(pageable).map { it.toDTO() }
    }

    override fun getTransactions(userId: UUID, pageable: Pageable): Page<TransactionDTO> {
        return transactionRepository.getTransactionByPayerId(userId, pageable).map { it.toDTO() }
    }

    override fun getIncomingTransactions(userId: UUID, pageable: Pageable): Page<TransactionDTO> {
        return transactionRepository.getTransactionsByPayeeId(userId, pageable).map { it.toDTO() }
    }

    override fun getAnyTransactions(userId: UUID, pageable: Pageable): Page<TransactionDTO> {
        return transactionRepository.getAnyTransaction(userId, pageable).map { it.toDTO() }
    }

    companion object {
        private val DEFAULT_TRANSACTION_STATUS = TransactionStatus.PENDING
        private const val ENTITY = "Transaction"
    }
}