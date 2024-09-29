package com.sorivma.apiservice.core.service.impl

import com.sorivma.apiservice.core.model.AccountStatus
import com.sorivma.apiservice.core.model.dto.AccountDTO
import com.sorivma.apiservice.core.model.dto.RegistrationDto
import com.sorivma.apiservice.core.model.dto.UserDTO
import com.sorivma.apiservice.core.model.entity.Account
import com.sorivma.apiservice.core.model.entity.User
import com.sorivma.apiservice.core.model.mapper.impl.AccountMapper
import com.sorivma.apiservice.core.model.mapper.impl.UserMapper
import com.sorivma.apiservice.core.repository.AccountRepository
import com.sorivma.apiservice.core.repository.UserRepository
import com.sorivma.apiservice.core.service.JpaServiceException.ExceptionExtensions.requiredEntity
import com.sorivma.apiservice.core.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class JpaUserService(
    private val userRepository: UserRepository,
    private val accountRepository: AccountRepository,
    private val userMapper: UserMapper,
    private val accountMapper: AccountMapper
) : UserService, BaseJpaService() {

    override val entity: String
        get() = "User"

    fun User.toDTO(): UserDTO = userMapper.toDTO(this)
    fun Account.toDTO(): AccountDTO = accountMapper.toDTO(this)

    override fun getPagedUsers(pageable: Pageable): Page<UserDTO> {
        return userRepository.findAll(pageable).map { user -> user.toDTO() }
    }

    @Transactional
    override fun registerUser(registrationDto: RegistrationDto): UserDTO {
        val user = User(
            name = registrationDto.name,
            email = registrationDto.email,
            transactions = listOf()
        )

        val savedUser = userRepository.save(user)
        accountRepository.save(
            Account(
                user = savedUser,
                balance = DEFAULT_BALANCE,
                status = DEFAULT_ACCOUNT_STATUS,
                accountType = registrationDto.accountType,
                currency = Currency.getInstance(registrationDto.currencyCode)
            )
        )

        return savedUser.toDTO()
    }

    override fun getUser(id: UUID): UserDTO {
        return userRepository.findById(id).required(id).toDTO()
    }

    override fun getUserAccount(id: UUID): AccountDTO {
        return accountRepository
            .findById(id).requiredEntity(id, ACCOUNT).toDTO()
    }

    override fun changeAccountStatus(userId: UUID, accountStatus: AccountStatus) {
        accountRepository.save(
            accountRepository.findById(userId).requiredEntity(userId, ACCOUNT).copy(status = accountStatus)
        )
    }

    companion object {
        private const val DEFAULT_BALANCE = 0.0

        private val DEFAULT_ACCOUNT_STATUS = AccountStatus.ACTIVE
        private const val ACCOUNT = "Account"
    }
}