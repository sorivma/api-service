package com.sorivma.apiservice.core.service

import com.sorivma.apiservice.core.model.dto.AccountDTO
import com.sorivma.apiservice.core.model.dto.UserDTO
import org.example.antifraudapi.rest.models.AccountStatus
import org.example.antifraudapi.rest.models.RegistrationRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface UserService {
    fun getPagedUsers(pageable: Pageable): Page<UserDTO>
    fun registerUser(registrationRequest: RegistrationRequest): UserDTO
    fun getUser(id: UUID): UserDTO
    fun getUserAccount(id: UUID): AccountDTO
    fun changeAccountStatus(userId: UUID, accountStatus: AccountStatus)
}