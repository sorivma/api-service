package com.sorivma.apiservice.api.rest.v1.controller

import com.sorivma.apiservice.api.rest.ApiCollection
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.AccountRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.UserPageRepresentationAssembler
import com.sorivma.apiservice.api.rest.v1.hateoas.assembler.UserRepresentationAssembler
import com.sorivma.apiservice.core.service.UserService
import org.example.antifraudapi.rest.controllers.UserController
import org.example.antifraudapi.rest.models.AccountRepresentation
import org.example.antifraudapi.rest.models.AccountStatus
import org.example.antifraudapi.rest.models.RegistrationRequest
import org.example.antifraudapi.rest.models.UserRepresentation
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.hateoas.PagedModel
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("${ApiCollection.API_V1}/users")
class UserController(
    private val userService: UserService,
    private val userAssembler: UserRepresentationAssembler,
    private val userPageAssembler: UserPageRepresentationAssembler,
    private val accountAssembler: AccountRepresentationAssembler
): UserController {
    override fun getUserPages(@PageableDefault pageable: Pageable): PagedModel<UserRepresentation> {
        return userPageAssembler.toModel(userService.getPagedUsers(pageable))
    }

    @GetMapping("/{userId}")
    override fun getUser(@PathVariable userId: UUID): UserRepresentation {
        return userAssembler.toModel(userService.getUser(userId))
    }

    @GetMapping("/{userId}/account")
    override fun getAccount(@PathVariable userId: UUID): AccountRepresentation {
        return accountAssembler.toModel(userService.getUserAccount(userId))
    }

    @PostMapping("/register")
    override fun registerUser(@RequestBody registrationRequest: RegistrationRequest): UserRepresentation {
       return userAssembler.toModel(userService.registerUser(registrationRequest))
    }

    @PutMapping("/{userId}/account/{status}")
    override fun updateStatus(@PathVariable userId: UUID, @PathVariable status: AccountStatus) {
        userService.changeAccountStatus(userId, status)
    }
}