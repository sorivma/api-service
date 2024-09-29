package com.sorivma.apiservice.api.rest.v1.hateoas.assembler

import com.sorivma.apiservice.api.rest.v1.controller.TransactionController
import com.sorivma.apiservice.api.rest.v1.hateoas.model.TransactionRepresentation
import com.sorivma.apiservice.api.rest.v1.hateoas.util.Models
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component
import java.util.*

@Component
@Suppress("UNCHECKED_CAST")
class TransactionIncomingPageRepresentationAssembler(
    private val transactionAssembler: TransactionRepresentationAssembler
) : RepresentationModelAssemblerSupport<Page<TransactionDTO>, PagedModel<TransactionRepresentation>>(
    TransactionController::class.java,
    PagedModel::class.java as Class<PagedModel<TransactionRepresentation>>
) {
    override fun toModel(entity: Page<TransactionDTO>): PagedModel<TransactionRepresentation> {
        val representations = entity.map { transactionAssembler.toModel(it) }

        if (entity.content.isEmpty()) {
            return PagedModel.empty()
        }

        return Models.pagedModel(
            methodOn(TransactionController::class.java).getIncomingTransactions(
                UUID.fromString(entity.content.first().payerId),
                Pageable.unpaged()
            ),
            representations,
            entity
        )
    }
}