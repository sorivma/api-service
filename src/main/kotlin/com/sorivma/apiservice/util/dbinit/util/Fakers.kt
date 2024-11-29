package com.sorivma.apiservice.util.dbinit.util

import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.model.dto.UserDTO
import net.datafaker.Faker
import org.example.antifraudapi.rest.models.AccountType
import org.example.antifraudapi.rest.models.PaymentMethod
import org.example.antifraudapi.rest.models.RegistrationRequest

object Fakers {
    private val faker: Faker = Faker()
    private val isoMoneyCodes = listOf("EUR", "USD", "RUB")


    fun getRegistrationDto(): RegistrationRequest {
        return RegistrationRequest(
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