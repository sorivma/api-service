package com.sorivma.apiservice.core.constants

object RMQConstants {
    const val MAIN_QUEUE_NAME = "main-queue"

    object Transactions {
        const val TRANSACTION_EXCHANGE_NAME = "transaction-exchange"
        const val ROUTING_KEY = "main.transaction"
    }
}