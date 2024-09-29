package com.sorivma.apiservice.api.rest.v1.hateoas.model

import org.springframework.hateoas.RepresentationModel

data class UserRepresentation(
    val id: String? = null,
    val name: String
): RepresentationModel<UserRepresentation>()