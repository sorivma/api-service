package com.sorivma.apiservice.core.service

import com.sorivma.apiservice.core.model.dto.TransactionMessageDTO

interface TransactionSenderService {
    fun sendTransaction(transaction: TransactionMessageDTO)
}
