package com.sorivma.apiservice.api.rest.v1.controller

import com.sorivma.apiservice.api.rest.ApiCollection
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.TransactionIncomingPageRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.TransactionOutcomingPageRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.TransactionPageRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.TransactionRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.model.TransactionRepresentation
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import com.sorivma.apiservice.core.service.TransactionService
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
) {
    @GetMapping("/all")
    fun getTransactions(@PageableDefault pageable: Pageable): PagedModel<TransactionRepresentation> {
        return transactionPageAssembler.toModel(transactionService.getTransactions(pageable))
    }

    @GetMapping("/{transactionId}")
    fun getTransaction(@PathVariable transactionId: UUID): TransactionRepresentation {
        return transactionAssembler.toModel(transactionService.getTransaction(transactionId))
    }

    @GetMapping("/incoming/{userId}")
    fun getIncomingTransactions(
        @PathVariable userId: UUID,
        @PageableDefault pageable: Pageable
    ): PagedModel<TransactionRepresentation> {
        return transactionPageIncomingAssembler.toModel(transactionService.getIncomingTransactions(userId, pageable))
    }

    @GetMapping("/outcoming/{userId}")
    fun getOutcomingTransactions(
        @PathVariable userId: UUID,
        @PageableDefault pageable: Pageable
    ): PagedModel<TransactionRepresentation> {
        return transactionPageOutcomingAssembler.toModel(transactionService.getTransactions(userId, pageable))
    }

    @PostMapping("/create")
    fun createTransaction(@RequestBody transactionDTO: TransactionDTO) {
        transactionService.createTransaction(
            transactionDTO
        )
    }
}