package com.sorivma.apiservice.api.rest.v1.hateoas.util

import org.springframework.data.domain.Pageable
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder

object Links {
    fun pageLink(invocation: Any, pageable: Pageable, rel : String): Link {
        val uriBuilder = WebMvcLinkBuilder.linkTo(invocation).toUriComponentsBuilder()
        uriBuilder.queryParam("page", pageable.pageNumber)
        uriBuilder.queryParam("size", pageable.pageSize)

        pageable.sort.forEach { order ->
            uriBuilder.queryParam("sort", "${order.property},${order.direction}")
        }

        return Link.of(uriBuilder.toUriString(), rel)
    }
}