package com.sorivma.apiservice.api.rest.v1.advice

import NoEntityResponse
import com.sorivma.apiservice.core.service.JpaServiceException
import org.example.antifraudapi.rest.exceiption.AntifraudApiAdvise
import org.example.antifraudapi.rest.exceiption.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice(basePackages = ["com.sorivma.apiservice.api.rest.v1.controller"])
class RestApiV1Advise: AntifraudApiAdvise {
    @ExceptionHandler(JpaServiceException.NoEntityWithId::class)
    override fun handleNoEntityAdvice(exception: EntityNotFoundException): ResponseEntity<NoEntityResponse> {
        val body = NoEntityResponse(
            message = exception.message ?: "Could not find entity",
            id = exception.id,
            entity = exception.entity
        )

        return ResponseEntity<NoEntityResponse>(body, HttpStatus.NOT_FOUND)
    }
}