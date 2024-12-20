package com.sorivma.apiservice.core.service.impl

import com.sorivma.apiservice.core.model.dto.TransactionCheckResultDto
import com.sorivma.apiservice.core.service.TransactionService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionStatusUpdateListener(
    private val transactionService: TransactionService
) {
    @RabbitListener(queues = ["update-queue"])
    fun updateTransactionStatus(transactionCheckDto: TransactionCheckResultDto) {
        transactionService.updateTransactionStatus(UUID.fromString(transactionCheckDto.transactionId), transactionCheckDto.status)
    }
}