package com.sorivma.apiservice.core.model.mapper.impl

import com.sorivma.apiservice.core.model.dto.UserDTO
import com.sorivma.apiservice.core.model.entity.User
import com.sorivma.apiservice.core.model.mapper.Mapper
import org.springframework.stereotype.Component

@Component
class UserMapper : Mapper<UserDTO, User> {
    override fun toDTO(entity: User): UserDTO {
        return UserDTO(
            id = entity.id.toString(),
            name = entity.name,
            email = entity.email
        )
    }

    override fun toEntity(dto: UserDTO): User {
        return User(
            transactions = listOf(),
            name = dto.name,
            email = dto.email,
        )
    }
}