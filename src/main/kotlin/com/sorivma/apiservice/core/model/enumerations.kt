package com.sorivma.apiservice.core.model

enum class AccountStatus {
    ACTIVE, SUSPENDED, CLOSED
}

enum class AccountType {
    CHECKING, SAVINGS, BUSINESS
}

enum class TransactionStatus {
    PENDING, COMPLETED, FAILED
}

enum class PaymentMethod {
    CREDIT_CARD, BANK_TRANSFER, //DEPRECATED QIWI
}