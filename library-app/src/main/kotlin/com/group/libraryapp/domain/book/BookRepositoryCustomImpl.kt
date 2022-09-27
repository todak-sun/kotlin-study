package com.group.libraryapp.domain.book

import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.dto.book.response.QBookStatResponse
import com.querydsl.jpa.impl.JPAQueryFactory

class BookRepositoryCustomImpl(
    private val qf: JPAQueryFactory
) : BookRepositoryCustom {
    override fun getStats(): List<BookStatResponse> {

        return qf.select(
            QBookStatResponse(
                book.type,
                book.id.count()
            )
        ).from(book)
            .groupBy(book.type)
            .fetch()
    }


}