package com.sorivma.apiservice.util.dbinit

import com.sorivma.apiservice.core.service.TransactionService
import com.sorivma.apiservice.core.service.impl.JpaUserService
import com.sorivma.apiservice.util.dbinit.config.DataInitializerConfig
import com.sorivma.apiservice.util.dbinit.util.Fakers
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component


@Component
@Profile("local")
class DatabaseInitializer(
    private val config: DataInitializerConfig,
    private val userService: JpaUserService,
    private val transactionService: TransactionService
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val users = List(config.userCount) {
            userService.registerUser(Fakers.getRegistrationDto())
        }

        Fakers.getTransactions(
            users.subList(0, config.userCount / 2),
            users.subList(config.userCount / 2, config.userCount)
        ).forEach {
            transactionService.createTransaction(it)
        }

        Fakers.getTransactions(
            users.subList(config.userCount / 2, config.userCount),
            users.subList(0, config.userCount / 2)
        ).forEach {
            transactionService.createTransaction(it)
        }
    }
}