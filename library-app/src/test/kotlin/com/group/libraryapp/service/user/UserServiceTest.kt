package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
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
    private val userService: UserService
) {

    @AfterEach
    fun afterEach() {
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
        val users = userService.users

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

}