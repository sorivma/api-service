package com.sorivma.apiservice.core.model.mapper.impl

import com.sorivma.apiservice.core.exception.BaseException
import com.sorivma.apiservice.core.model.TransactionStatus
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.model.entity.Transaction
import com.sorivma.apiservice.core.model.mapper.Mapper
import com.sorivma.apiservice.core.repository.UserRepository
import com.sorivma.apiservice.util.extensions.OptionalExtensions.requiredValue
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class TransactionMapper(
    private val userRepository: UserRepository
) : Mapper<TransactionDTO, Transaction> {
    override fun toDTO(entity: Transaction): TransactionDTO {
        return TransactionDTO(
            id = entity.id.toString(),
            payerId = entity.payer.id.toString(),
            payeeId = entity.payee.id.toString(),
            paymentMethod = entity.paymentMethod,
            amount = entity.amount,
            status = entity.status
        )
    }

    @Transactional
    override fun toEntity(dto: TransactionDTO): Transaction {
        return Transaction(
            payer = userRepository.findById(UUID.fromString(dto.payerId)).requiredValue(BaseException.default("")),
            payee = userRepository.findById(UUID.fromString(dto.payeeId)).requiredValue(BaseException.default("")),
            paymentMethod = dto.paymentMethod,
            amount = dto.amount,
            status = dto.status ?: TransactionStatus.PENDING
        )
    }
}