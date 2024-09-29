package com.sorivma.apiservice.core.repository

import com.sorivma.apiservice.core.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID>