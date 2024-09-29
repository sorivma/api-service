package com.sorivma.apiservice.core.model.mapper

interface Mapper<D, E> {
    fun toDTO(entity: E): D
    fun toEntity(dto: D): E
}