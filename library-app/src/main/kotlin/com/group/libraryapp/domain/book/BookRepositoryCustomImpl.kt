package com.group.libraryapp.domain.book

import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.jpa.impl.JPAQueryFactory

class BookRepositoryCustomImpl(
    private val qf: JPAQueryFactory
) : BookRepositoryCustom {
    override fun getStats(): List<BookStatResponse> {
//        @Query("SELECT new com.group.libraryapp.dto.book.response.BookStatResponse(b.type, COUNT(b.id)) FROM Book b GROUP BY b.type")

    }


}