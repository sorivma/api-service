package com.sorivma.apiservice.api.graphql

import com.netflix.graphql.dgs.*
import com.sorivma.apiservice.api.graphql.dto.UserAggregate
import com.sorivma.apiservice.core.exception.BaseException
import com.sorivma.apiservice.core.model.dto.AccountDTO
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.service.UserAggregateService
import com.sorivma.apiservice.util.extensions.DtoExtensions.toUUID

@DgsComponent
class UserDataFetcher(
    private val userService: UserAggregateService
) {
    @DgsQuery
    fun user(@InputArgument userId: String): UserAggregate {
        return UserAggregate(userService.getUserById(userId.toUUID()))
    }

    @DgsQuery
    fun users(): List<UserAggregate> {
        return userService.getUsers().map { UserAggregate(it) }
    }

    @DgsData(parentType = "UserAggregate", field = "incomingTransactions")
    fun getIncomingTransactions(dfe: DgsDataFetchingEnvironment): List<TransactionDTO> {
        val user = dfe.getSource<UserAggregate>()

        return user?.let {
            userService.getIncomingTransactions(it.id.toUUID())
        } ?: listOf()
    }

    @DgsData(parentType = "UserAggregate", field = "incomingTransactions")
    fun getOutcomingTransactions(dfe: DgsDataFetchingEnvironment): List<TransactionDTO> {
        val user = dfe.getSource<UserAggregate>()

        return user?.let {
            userService.getOutcomingTransactions(it.id.toUUID())
        } ?: listOf()
    }

    @DgsData(parentType = "UserAggregate", field = "account")
    fun getAccount(dfe: DgsDataFetchingEnvironment): AccountDTO {
        val user = dfe.getSource<UserAggregate>()
        return user?.let {
            userService.getUserAccount(user.id.toUUID())
        } ?: throw BaseException.default("Invalid user state [user got no account]")
    }


}