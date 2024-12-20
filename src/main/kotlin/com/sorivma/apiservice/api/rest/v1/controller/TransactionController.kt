package com.sorivma.apiservice.api.rest.v1.controller

import com.sorivma.apiservice.api.rest.ApiCollection
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.TransactionIncomingPageRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.TransactionOutcomingPageRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.TransactionPageRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.TransactionRepresentationAssembler
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.service.TransactionService
import org.example.antifraudapi.rest.controllers.TransactionController
import org.example.antifraudapi.rest.models.TransactionCreationRequest
import org.example.antifraudapi.rest.models.TransactionRepresentation
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.hateoas.PagedModel
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("${ApiCollection.API_V1}/transactions")
class TransactionController(
    private val transactionService: TransactionService,
    private val transactionPageIncomingAssembler: TransactionIncomingPageRepresentationAssembler,
    private val transactionPageOutcomingAssembler: TransactionOutcomingPageRepresentationAssembler,
    private val transactionPageAssembler: TransactionPageRepresentationAssembler,
    private val transactionAssembler: TransactionRepresentationAssembler
): TransactionController {
    @GetMapping("/all")
    override fun getTransactions(@PageableDefault pageable: Pageable): PagedModel<TransactionRepresentation> {
        return transactionPageAssembler.toModel(transactionService.getTransactions(pageable))
    }

    @GetMapping("/{transactionId}")
    override fun getTransaction(@PathVariable transactionId: UUID): TransactionRepresentation {
        return transactionAssembler.toModel(transactionService.getTransaction(transactionId))
    }

    @GetMapping("/incoming/{userId}")
    override fun getIncomeTransactions(
        @PathVariable userId: UUID,
        @PageableDefault pageable: Pageable
    ): PagedModel<TransactionRepresentation> {
        return transactionPageIncomingAssembler.toModel(transactionService.getIncomingTransactions(userId, pageable))
    }

    @GetMapping("/outcoming/{userId}")
    override fun getOutcomeTransactions(
        @PathVariable userId: UUID,
        @PageableDefault pageable: Pageable
    ): PagedModel<TransactionRepresentation> {
        return transactionPageOutcomingAssembler.toModel(transactionService.getTransactions(userId, pageable))
    }

    @PostMapping("/create")
    override fun createTransaction(@RequestBody transaction: TransactionCreationRequest): TransactionRepresentation {
        return transactionAssembler.toModel(
            transactionService.createTransaction(
                transaction.toDto()
            )
        )
    }

    fun TransactionCreationRequest.toDto(): TransactionDTO {
        return TransactionDTO(
            payeeId = this.payeeId,
            payerId = this.payerId,
            paymentMethod = this.paymentMethod,
            amount = this.amount
        )
    }
}