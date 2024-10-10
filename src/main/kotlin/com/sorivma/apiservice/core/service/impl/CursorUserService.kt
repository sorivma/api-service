package com.sorivma.apiservice.core.service.impl

import com.sorivma.apiservice.core.model.dto.UserDTO
import com.sorivma.apiservice.core.repository.UserCursorSpecificationRepository
import com.sorivma.apiservice.core.service.CursorService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Qualifier("cursor")
class CursorUserService(
    private val cursorRepository: UserCursorSpecificationRepository
) : CursorService<UserDTO>{
    @Transactional
    override fun getDtos(first: Int, after: String?): List<UserDTO> {
        after?.let {
            it.
        }
    }

    override fun hasMoreDtos(after: String?): Boolean {
        TODO("Not yet implemented")
    }
}