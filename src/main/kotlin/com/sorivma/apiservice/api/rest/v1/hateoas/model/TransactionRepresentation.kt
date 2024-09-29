package com.sorivma.apiservice.api.rest.v1.hateoas.model

import com.sorivma.apiservice.core.model.TransactionStatus
import org.springframework.hateoas.RepresentationModel

data class TransactionRepresentation(
    val id: String? = null,
    val amount: Double,
    val status: TransactionStatus,
): RepresentationModel<TransactionRepresentation>()

