package io.todak.sftptesting.testhelper

import com.jcraft.jsch.JSch
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
class WithTestContainer {

    companion object {

        private val username: String = "sftpuser"
        private val password: String = "strong!@#pasword"

        @Container
        private val sftpServierContainer: GenericContainer<*> =
            GenericContainer(DockerImageName.parse("atmoz/sftp:alpine-3.7"))
                .withExposedPorts(22)
                .withCommand("${username}:${password}:1001")

        init {
            sftpServierContainer.start()
            println("mapped port : ${sftpServierContainer.getMappedPort(22)}")
        }

        @DynamicPropertySource
        @JvmStatic
        fun registryDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("sftp.host") { "localhost" }
        }
    }

    @Test
    fun test() {
        val jSch = JSch()
        val session = jSch.getSession("sftpuser", "localhost", sftpServierContainer.getMappedPort(22))
        session.setPassword("strong!@#pasword")
        session.setConfig("StrictHostKeyChecking", "no")
        session.connect()

        assertTrue(session.isConnected)

        session.disconnect()
    }

}