package com.sorivma.apiservice.core.service.impl

import com.sorivma.apiservice.core.constants.RMQConstants
import com.sorivma.apiservice.core.model.dto.TransactionMessageDTO
import com.sorivma.apiservice.core.service.TransactionSenderService
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.stereotype.Service

@Service
class RMQTransactionSenderService(private val amqpTemplate: AmqpTemplate) : TransactionSenderService {
    override fun sendTransaction(transaction: TransactionMessageDTO) {
        amqpTemplate.convertAndSend(RMQConstants.Transactions.TRANSACTION_EXCHANGE_NAME, RMQConstants.Transactions.ROUTING_KEY, transaction)
    }
}