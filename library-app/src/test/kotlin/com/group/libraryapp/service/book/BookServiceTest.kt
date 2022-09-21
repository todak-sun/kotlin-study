package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun afterEach() {
        this.bookRepository.deleteAll()
        this.userRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록이 정상 동작")
    fun saveBookTest() {
        // given
        val request = BookRequest("해리포터", BookType.COMPUTER)

        // when
        this.bookService.saveBook(request)

        // then
        val books = this.bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].name).isEqualTo("해리포터")
        assertThat(books[0].type).isEqualTo(BookType.COMPUTER)
    }

    @Test
    @DisplayName("책 대출이 정상 동작")
    fun loanBookTest() {
        // given
        this.bookRepository.save(Book.fixture(name = "해리포터"))
        val savedUser = this.userRepository.save(
            User(
                "Todak",
                32
            )
        )
        val request = BookLoanRequest("Todak", "해리포터")

        // when
        this.bookService.loanBook(request)

        // then
        val loanHistories = userLoanHistoryRepository.findAll()
        assertThat(loanHistories).hasSize(1)
        assertThat(loanHistories[0].bookName).isEqualTo("해리포터")
        assertThat(loanHistories[0].user.id).isEqualTo(savedUser.id)
        assertThat(loanHistories[0].status).isEqualTo(UserLoanStatus.LOANED)
    }

    @Test
    @DisplayName("책이 대출되어 있다면 신규 대출이 실패한다.")
    fun loanBookFailTest() {
        // given
        this.bookRepository.save(Book.fixture(name = "해리포터"))
        val savedUser = this.userRepository.save(
            User(
                "Todak",
                32
            )
        )
        val request = BookLoanRequest("Todak", "해리포터")
        this.bookService.loanBook(request)

        // when & then
        assertThrows<IllegalArgumentException> { this.bookService.loanBook(request) }
            .apply { assertEquals("진작 대출되어 있는 책입니다", message) }
    }

    @Test
    @DisplayName("책 반납이 정상 동작")
    fun returnBookTest() {
        // given
        this.bookRepository.save(Book.fixture(name = "해리포터"))
        val savedUser = this.userRepository.save(
            User(
                "Todak",
                32
            )
        )
        val request = BookLoanRequest("Todak", "해리포터")
        this.bookService.loanBook(request)

        // when
        this.bookService.returnBook(BookReturnRequest("Todak", "해리포터"))

        // then
        val loanHistories = this.userLoanHistoryRepository.findAll()
        assertThat(loanHistories).hasSize(1)
        assertThat(loanHistories[0].status).isEqualTo(UserLoanStatus.RETURNED)
    }


    @Test
    @DisplayName("책 대여 권수를 정상 확인한다.")
    fun countLoanedBookTest() {
        // given
        val user = this.userRepository.save(User("아저씨", 50))
        this.userLoanHistoryRepository.saveAll(
            listOf(
                UserLoanHistory.fixture(user = user, "A"),
                UserLoanHistory.fixture(user = user, "B", UserLoanStatus.RETURNED),
                UserLoanHistory.fixture(user = user, "B", UserLoanStatus.RETURNED),
            )
        )

        // when
        val result = this.bookService.countLoanedBook();

        // then
        assertThat(result).isEqualTo(1)
    }

    @Test
    @DisplayName("책 별로 얼마나 많은 수량이 있는지 리턴한다.")
    fun getBookStatisticTest() {
        // given
        this.bookRepository.saveAll(
            listOf(
                Book.fixture(type = BookType.COMPUTER),
                Book.fixture(type = BookType.COMPUTER),
                Book.fixture(type = BookType.ECONOMY),
                Book.fixture(type = BookType.LANGUAGE)
            )
        )

        // when
        val statistic = this.bookService.getBookStatistic()

        // then
        assertThat(statistic).hasSize(3)
        assertCount(statistic, BookType.COMPUTER, 2)
        assertCount(statistic, BookType.ECONOMY, 1)
        assertCount(statistic, BookType.LANGUAGE, 1)
    }

    private fun assertCount(results: List<BookStatResponse>, type: BookType, count: Int) {
        assertThat(results.first { result -> result.type == type }.count).isEqualTo(count);
    }


}