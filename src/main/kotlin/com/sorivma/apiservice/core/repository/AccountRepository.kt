package com.sorivma.apiservice.core.repository

import com.sorivma.apiservice.core.model.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, UUID>