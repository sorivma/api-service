package com.sorivma.apiservice.api.rest.v1.controller

import com.sorivma.apiservice.api.rest.ApiCollection
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.AccountRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.UserPageRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.UserRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.model.UserRepresentation
import com.sorivma.apiservice.core.model.AccountStatus
import com.sorivma.apiservice.core.model.dto.RegistrationDto
import com.sorivma.apiservice.core.service.UserService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.RepresentationModel
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("${ApiCollection.API_V1}/users")
class UserController(
    private val userService: UserService,
    private val userAssembler: UserRepresentationAssembler,
    private val userPageAssembler: UserPageRepresentationAssembler,
    private val accountAssembler: AccountRepresentationAssembler
) {
    @GetMapping
    fun getUserPages(@PageableDefault pageable: Pageable): PagedModel<UserRepresentation> {
        return userPageAssembler.toModel(userService.getPagedUsers(pageable))
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: UUID): RepresentationModel<*> {
        return userAssembler.toModel(userService.getUser(userId))
    }

    @GetMapping("/{userId}/account")
    fun getAccount(@PathVariable userId: UUID): RepresentationModel<*> {
        return accountAssembler.toModel(userService.getUserAccount(userId))
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody registrationDto: RegistrationDto) {
        userService.registerUser(registrationDto)
    }

    @PutMapping("/{userId}/account/{status}")
    fun updateStatus(@PathVariable userId: UUID, @PathVariable status: AccountStatus) {
        userService.changeAccountStatus(userId, status)
    }
}