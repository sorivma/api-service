package com.sorivma.apiservice.api.rest.v1.hateoas.assembler

import com.sorivma.apiservice.api.rest.v1.controller.TransactionController
import com.sorivma.apiservice.api.rest.v1.controller.UserController
import com.sorivma.apiservice.api.rest.v1.hateoas.model.UserRepresentation
import com.sorivma.apiservice.core.model.dto.UserDTO
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserRepresentationAssembler : RepresentationModelAssemblerSupport<UserDTO, UserRepresentation>(
    UserDTO::class.java,
    UserRepresentation::class.java
) {
    override fun toModel(entity: UserDTO): UserRepresentation {
        val representation = UserRepresentation(
            id = entity.id,
            name = entity.name
        )

        representation.add(
            linkTo(methodOn(UserController::class.java).getUser(UUID.fromString(entity.id)))
                .withSelfRel()
        )

        representation.add(
            linkTo(
                methodOn(TransactionController::class.java).getIncomingTransactions(
                    UUID.fromString(entity.id),
                    Pageable.unpaged()
                )
            ).withRel(INCOMING_REL)
        )

        representation.add(
            linkTo(
                methodOn(TransactionController::class.java).getOutcomingTransactions(
                    UUID.fromString(entity.id),
                    Pageable.unpaged()
                )
            ).withRel(OUTCOMING_REL)
        )

        representation.add(
            linkTo(
                methodOn(UserController::class.java).getAccount(UUID.fromString(entity.id))
            ).withRel(ACCOUNT_REL)
        )


        return representation
    }

    companion object {
        const val ACCOUNT_REL = "account"
        const val INCOMING_REL = "incoming"
        const val OUTCOMING_REL = "outcoming"
    }
}