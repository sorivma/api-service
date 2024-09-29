package com.sorivma.apiservice.api.rest.v1.advice

import com.sorivma.apiservice.core.service.JpaServiceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice(basePackages = ["com.sorivma.apiservice.api.rest.v1.controller"])
class RestApiV1Advise {
    data class NoEntityBody(
        val message: String,
        val id: String,
        val entity: String
    )

    @ExceptionHandler(JpaServiceException.NoEntityWithId::class)
    fun handleNoEntityAdvice(ex: JpaServiceException.NoEntityWithId): ResponseEntity<NoEntityBody> {
        val body = NoEntityBody(
            message = ex.message.toString(),
            id = ex.id,
            entity = ex.entity
        )

        return ResponseEntity<NoEntityBody>(body, HttpStatus.OK)
    }
}