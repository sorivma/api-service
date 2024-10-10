package com.sorivma.apiservice.core.repository

import com.sorivma.apiservice.core.model.entity.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserCursorSpecificationRepository : UserRepository {
    @Query("SELECT u FROM User u WHERE u.id > :afterUUID ORDER BY u.id ASC")
    fun findNextUser(@Param("afterUUID") afterUUID: UUID, pageable: Pageable)

    @Query("SELECT u FROM User u ORDER BY u.id ASC")
    fun findFirstUsers(pageable: Pageable): List<User>

    @Query("SELECT COUNT(u) FROM User u WHERE u.id > :afterUuid")
    fun countAfter(@Param("afterUuid") afterUuid: UUID): Long
}