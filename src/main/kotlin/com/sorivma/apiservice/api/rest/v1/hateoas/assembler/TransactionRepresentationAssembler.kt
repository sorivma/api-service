package com.sorivma.apiservice.api.rest.v1.hateoas.assembler

import com.sorivma.apiservice.api.rest.v1.controller.TransactionController
import com.sorivma.apiservice.api.rest.v1.controller.UserController
import com.sorivma.apiservice.api.rest.v1.hateoas.model.TransactionRepresentation
import com.sorivma.apiservice.core.model.dto.TransactionDTO
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionRepresentationAssembler :
    RepresentationModelAssemblerSupport<TransactionDTO, TransactionRepresentation>(
        TransactionController::class.java,
        TransactionRepresentation::class.java
    ) {
    override fun toModel(entity: TransactionDTO): TransactionRepresentation {
        val representation = TransactionRepresentation(
            id = entity.id.toString(),
            amount = entity.amount,
            status = entity.status!!
        )

        representation.add(linkTo(TransactionController::class.java).slash(entity.id).withSelfRel())

        representation.add(
            linkTo(methodOn(UserController::class.java).getUser(UUID.fromString(entity.payeeId))).withRel(PAYEE_REL)
        )
        representation.add(
            linkTo(methodOn(UserController::class.java).getUser(UUID.fromString(entity.payerId))).withRel(PAYER_REL)
        )

        return representation
    }

    companion object {
        const val PAYEE_REL = "payee"
        const val PAYER_REL = "payer"
    }
}