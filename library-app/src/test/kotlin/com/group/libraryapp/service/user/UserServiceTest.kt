package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun afterEach() {
        println("=".repeat(50).plus("AFTER EACH").plus("=".repeat(50)))
        this.userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저 저장이 정상 동작한다.")
    fun saveUserTest() {
        // given
        val request = UserCreateRequest("이름", null)

        // when
        this.userService.saveUser(request)

        // then
        val users = this.userRepository.findAll()
        assertEquals(1, users.size)
        assertEquals("이름", users[0].name)
        assertNull(users[0].age)
    }

    @Test
    @DisplayName("유저를 정상 조회한다.")
    fun getUsersTest() {
        // given
        userRepository.saveAll(
            listOf(
                User("홍길동", 20),
                User("임꺾정", null)
            )
        )

        // when
        val users = userService.getUsers()

        // then
        assertEquals(2, users.size)
        assertThat(users).extracting("name").containsExactly("홍길동", "임꺾정")
        assertThat(users).extracting("age").containsExactly(20, null)
    }


    @Test
    @DisplayName("유저 이름을 변경한다")
    fun updateUserNameTest() {
        // given
        val savedUser = userRepository.save(User("이름", null))
        val request = UserUpdateRequest(savedUser.id!!, "바뀐이름")

        // when
        this.userService.updateUserName(request)

        // then
        assertEquals("바뀐이름", this.userRepository.findAll()[0].name);
    }

    @Test
    @DisplayName("유저를 삭제한다.")
    fun deleteUserTest() {
        // given
        userRepository.save(User("철수", null))

        // when
        this.userService.deleteUser("철수")

        // then
        assertEquals(0, userRepository.findAll().size)
    }

    @Test
    @DisplayName("대출 기록이 없는 유저도 응답에 포함된다.")
    fun getUserLoanHistoriesTestNoLoan() {
        // given
        userRepository.save(User("토닥", 32))

        // when
        val userLoanHistories = this.userService.getUserLoanHistories()

        // then
        assertThat(userLoanHistories).hasSize(1)
        assertThat(userLoanHistories[0].name).isEqualTo("토닥")
        assertThat(userLoanHistories[0].books).hasSize(0)
    }

    @Test
    @DisplayName("대출 기록이 많은 유저의 응답이 정상 동작한다.")
    fun getUserLoanHistories2() {
        // given
        val user = this.userRepository.save(User("토닥", 32))

        // when
        this.userLoanHistoryRepository.saveAll(
            listOf(
                UserLoanHistory.fixture(user, "책1", UserLoanStatus.LOANED),
                UserLoanHistory.fixture(user, "책2", UserLoanStatus.LOANED),
                UserLoanHistory.fixture(user, "책3", UserLoanStatus.RETURNED)
            )
        )

        // then
        val userLoanHistories = this.userService
            .getUserLoanHistories()

        assertThat(userLoanHistories).hasSize(1)
        assertThat(userLoanHistories[0].name).isEqualTo("토닥")
        assertThat(userLoanHistories[0].books).hasSize(3)
        assertThat(userLoanHistories[0].books).extracting("name")
            .containsExactlyInAnyOrder("책1", "책2", "책3")
        assertThat(userLoanHistories[0].books).extracting("isReturn")
            .containsExactlyInAnyOrder(false, false, true)
    }

}