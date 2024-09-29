package com.sorivma.apiservice.api.rest.v1.hateoas.assembler

import com.sorivma.apiservice.api.rest.v1.controller.UserController
import com.sorivma.apiservice.api.rest.v1.hateoas.model.AccountRepresentation
import com.sorivma.apiservice.core.model.dto.AccountDTO
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountRepresentationAssembler :
    RepresentationModelAssemblerSupport<AccountDTO, AccountRepresentation>(
        UserController::class.java,
        AccountRepresentation::class.java
    ) {
    override fun toModel(entity: AccountDTO): AccountRepresentation {
        val representation = AccountRepresentation(
            id = entity.userId,
            balance = entity.balance,
            accountType = entity.accountType,
            currency = entity.currencyCode,
            status = entity.status
        )

        representation.add(linkTo(methodOn(UserController::class.java).getAccount(UUID.fromString(entity.userId)))
            .withSelfRel())

        representation.add(linkTo(methodOn(UserController::class.java).getUser(UUID.fromString(entity.userId)))
            .withRel(USER_REL))

        return representation
    }

    companion object {
        const val USER_REL = "user"
    }
}
