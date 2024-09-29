package com.sorivma.apiservice.core.model.mapper.impl

import com.sorivma.apiservice.core.exception.BaseException
import com.sorivma.apiservice.core.model.dto.AccountDTO
import com.sorivma.apiservice.core.model.entity.Account
import com.sorivma.apiservice.core.model.mapper.Mapper
import com.sorivma.apiservice.core.repository.UserRepository
import com.sorivma.apiservice.util.extensions.OptionalExtensions.requiredValue
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountMapper(
    private val userRepository: UserRepository
) : Mapper<AccountDTO, Account> {
    override fun toDTO(entity: Account): AccountDTO {
        return AccountDTO(
            userId = entity.user.id.toString(),
            balance = entity.balance,
            status = entity.status,
            accountType = entity.accountType,
            currencyCode = entity.currency.currencyCode
        )
    }

    override fun toEntity(dto: AccountDTO): Account {
        return Account(
            user = userRepository.findById(UUID.fromString(dto.userId)).requiredValue(BaseException.default("")),
            balance = dto.balance,
            status = dto.status,
            accountType = dto.accountType,
            currency = Currency.getInstance(dto.currencyCode)
        )
    }
}