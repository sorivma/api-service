package com.sorivma.apiservice.api.rest.v1.hateoas.util

import org.springframework.data.domain.Page
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.RepresentationModel

object Models {
    fun <T : RepresentationModel<T>> pagedModel(
        dummyCall: Any,
        representations: Page<T>,
        entity: Page<*>
    ): PagedModel<T> {
        return PagedModel.of(
            representations.content,
            PagedModel.PageMetadata(
                entity.size.toLong(),
                entity.number.toLong(),
                entity.totalElements,
                entity.totalPages.toLong()
            )
        ).apply {
            add(
                Links.pageLink(
                    dummyCall,
                    entity.pageable, "self"
                ),
            )

            if (entity.hasNext()) {
                add(
                    Links.pageLink(
                        dummyCall,
                        entity.nextPageable(), PagedRels.NEXT
                    )
                )
            }

            if (entity.hasPrevious()) {
                add(
                    Links.pageLink(
                        dummyCall,
                        entity.previousPageable(), PagedRels.PREV
                    )
                )
            }
        }
    }

    object PagedRels {
        const val NEXT = "next"
        const val PREV = "previous"
    }
}