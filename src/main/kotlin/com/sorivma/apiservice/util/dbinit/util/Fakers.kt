package com.sorivma.apiservice.util.dbinit.util

import com.sorivma.apiservice.core.model.AccountType
import com.sorivma.apiservice.core.model.PaymentMethod
import com.sorivma.apiservice.core.model.dto.RegistrationDto
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.model.dto.UserDTO
import net.datafaker.Faker

object Fakers {
    private val faker: Faker = Faker()
    private val isoMoneyCodes = listOf("EUR", "USD", "RUB")


    fun getRegistrationDto(): RegistrationDto {
        return RegistrationDto(
            name = faker.name().fullName(),
            email = faker.internet().emailAddress(),
            currencyCode = isoMoneyCodes[faker.random().nextInt(isoMoneyCodes.size)],
            accountType = faker.options().option(AccountType::class.java)
        )
    }

    fun getTransactions(payers: List<UserDTO>, payees: List<UserDTO>): List<TransactionDTO> {
        return payees.zip(payers).map {
            TransactionDTO(
                amount = faker.random().nextDouble(),
                payeeId = it.first.id,
                payerId = it.second.id,
                paymentMethod = faker.options().option(PaymentMethod::class.java)
            )
        }
    }
}