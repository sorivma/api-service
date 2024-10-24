package com.sorivma.apiservice.core.service.impl

import com.sorivma.apiservice.core.model.dto.AccountDTO
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.model.dto.UserDTO
import com.sorivma.apiservice.core.model.entity.Account
import com.sorivma.apiservice.core.model.entity.Transaction
import com.sorivma.apiservice.core.model.entity.User
import com.sorivma.apiservice.core.model.mapper.impl.AccountMapper
import com.sorivma.apiservice.core.model.mapper.impl.TransactionMapper
import com.sorivma.apiservice.core.model.mapper.impl.UserMapper
import com.sorivma.apiservice.core.repository.AccountRepository
import com.sorivma.apiservice.core.repository.TransactionRepository
import com.sorivma.apiservice.core.repository.UserRepository
import com.sorivma.apiservice.core.service.TransactionService
import com.sorivma.apiservice.core.service.UserAggregateService
import com.sorivma.apiservice.core.service.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class JpaUserAggregateService(
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository,
    private val transactionMapper: TransactionMapper,
    private val userMapper: UserMapper,
    private val accountMapper: AccountMapper,
    private val userService: UserService,
    private val transactionService: TransactionService
) : UserAggregateService, BaseJpaService() {
    private fun Transaction.toDTO() = transactionMapper.toDTO(this)
    private fun User.toDTO(): UserDTO = userMapper.toDTO(this)
    private fun Account.toDTO(): AccountDTO = accountMapper.toDTO(this)


    override fun getUserById(userId: UUID): UserDTO {
        return userRepository.findById(userId).required(userId).toDTO()
    }

    override fun getUsers(): List<UserDTO> {
        return userRepository.findAll().map { it.toDTO() }
    }

    override fun getIncomingTransactions(userId: UUID): List<TransactionDTO> {
        return transactionRepository.getTransactionsByPayeeId(userId).map { it.toDTO() }
    }

    override fun getOutcomingTransactions(userId: UUID): List<TransactionDTO> {
        return transactionRepository.getTransactionByPayerId(userId).map { it.toDTO() }
    }

    override fun getUserAccount(userId: UUID): AccountDTO {
        return accountRepository.findById(userId).required(userId).toDTO()
    }
}