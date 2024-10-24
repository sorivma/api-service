package com.sorivma.apiservice.api.graphql.fetchers

import com.netflix.graphql.dgs.*
import com.sorivma.apiservice.api.graphql.dto.IncomingTransactionInput
import com.sorivma.apiservice.api.graphql.dto.OutgoingTransactionInput
import com.sorivma.apiservice.api.graphql.dto.UserAggregate
import com.sorivma.apiservice.api.graphql.dto.toDTO
import com.sorivma.apiservice.core.exception.BaseException
import com.sorivma.apiservice.core.model.dto.AccountDTO
import com.sorivma.apiservice.core.model.dto.RegistrationDto
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.service.TransactionService
import com.sorivma.apiservice.core.service.UserAggregateService
import com.sorivma.apiservice.core.service.UserService
import com.sorivma.apiservice.util.extensions.DtoExtensions.toUUID

@DgsComponent
class UserDataFetcher(
    private val userAggregateService: UserAggregateService,
    private val userService: UserService,
    private val transactionService: TransactionService
) {
    @DgsQuery
    fun user(@InputArgument userId: String): UserAggregate {
        return UserAggregate(userAggregateService.getUserById(userId.toUUID()))
    }

    @DgsQuery
    fun users(): List<UserAggregate> {
        return userAggregateService.getUsers().map { UserAggregate(it) }
    }

    @DgsData(parentType = "UserAggregate", field = "incomingTransactions")
    fun getIncomingTransactions(dfe: DgsDataFetchingEnvironment): List<TransactionDTO> {
        val user = dfe.getSource<UserAggregate>()

        return user?.let {
            userAggregateService.getIncomingTransactions(it.id.toUUID())
        } ?: listOf()
    }

    @DgsData(parentType = "UserAggregate", field = "outcomingTransactions")
    fun getOutcomingTransactions(dfe: DgsDataFetchingEnvironment): List<TransactionDTO> {
        val user = dfe.getSource<UserAggregate>()

        return user?.let {
            userAggregateService.getOutcomingTransactions(it.id.toUUID())
        } ?: listOf()
    }

    @DgsData(parentType = "UserAggregate", field = "account")
    fun getAccount(dfe: DgsDataFetchingEnvironment): AccountDTO {
        val user = dfe.getSource<UserAggregate>()
        return user?.let {
            userAggregateService.getUserAccount(user.id.toUUID())
        } ?: throw BaseException.default("Invalid user state [user got no account]")
    }

    @DgsMutation
    fun createUser(input: RegistrationDto): UserAggregate {
        return UserAggregate(userService.registerUser(input))
    }

    @DgsMutation
    fun addIncomingTransaction(userId: String, transactionInput: IncomingTransactionInput): TransactionDTO {
        return transactionService.createTransaction(
            transactionInput.toDTO(userId)
        )
    }

    @DgsMutation
    fun addOutcomingTransaction(userId: String, transactionInput: OutgoingTransactionInput): TransactionDTO {
        return transactionService.createTransaction(
            transactionInput.toDTO(userId)
        )
    }
}