package com.sorivma.apiservice.core.service

interface CursorService<D> {
    fun getDtos(first: Int, after: String?): List<D>
    fun hasMoreDtos(after: String?): Boolean
}