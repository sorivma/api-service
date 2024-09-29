package com.sorivma.apiservice.api.rest.v1.hateoas.assembler

import com.sorivma.apiservice.api.rest.v1.controller.UserController
import com.sorivma.apiservice.api.rest.v1.hateoas.model.UserRepresentation
import com.sorivma.apiservice.api.rest.v1.hateoas.util.Models
import com.sorivma.apiservice.core.model.dto.UserDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Suppress("UNCHECKED_CAST")
@Component
class UserPageRepresentationAssembler(
    private val userAssembler: UserRepresentationAssembler
) : RepresentationModelAssemblerSupport<Page<UserDTO>, PagedModel<UserRepresentation>>(
    UserController::class.java,
    PagedModel::class.java as Class<PagedModel<UserRepresentation>>
) {
    override fun toModel(entity: Page<UserDTO>): PagedModel<UserRepresentation> {
        val representations = entity.map { userAssembler.toModel(it) }

        return Models.pagedModel(
            methodOn(UserController::class.java).getUserPages(Pageable.unpaged()),
            representations,
            entity
        )
    }
}