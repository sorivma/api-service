package com.sorivma.apiservice.core.service

import com.sorivma.apiservice.core.model.AccountStatus
import com.sorivma.apiservice.core.model.dto.AccountDTO
import com.sorivma.apiservice.core.model.dto.RegistrationDto
import com.sorivma.apiservice.core.model.dto.UserDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface UserService {
    fun getPagedUsers(pageable: Pageable): Page<UserDTO>
    fun registerUser(registrationDto: RegistrationDto): UserDTO
    fun getUser(id: UUID): UserDTO
    fun getUserAccount(id: UUID): AccountDTO
    fun changeAccountStatus(userId: UUID, accountStatus: AccountStatus)
}