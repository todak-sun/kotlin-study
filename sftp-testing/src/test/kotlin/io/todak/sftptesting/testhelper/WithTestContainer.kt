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

        private const val username: String = "sftpuser"
        private const val password: String = "strong!@#pasword"

        @Container
        private val sftpServierContainer: GenericContainer<*> =
            GenericContainer(DockerImageName.parse("atmoz/sftp:alpine-3.7"))
                .withExposedPorts(22)
                .withCommand("${username}:${password}:1001")

        init {
            System.setProperty("sftp.host", "localhost")
            System.setProperty("sftp.username", username)
            System.setProperty("sftp.password", password)
            sftpServierContainer.start()
            System.setProperty("sftp.port", sftpServierContainer.getMappedPort(22).toString())
        }

        @DynamicPropertySource
        @JvmStatic
        fun registryDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("sftp.host") { "localhost" }
            registry.add("sftp.password") { password }
            registry.add("sftp.username") { username }
        }
    }

    @Test
    fun test() {
        val jSch = JSch()
        val session = jSch.getSession(
            System.getProperty("sftp.username"),
            System.getProperty("sftp.host"),
            System.getProperty("sftp.port").toInt()
        )

        session.setPassword(System.getProperty("sftp.password"))
        session.setConfig("StrictHostKeyChecking", "no")

        session.connect()
        assertTrue(session.isConnected)

        val channel = session.openChannel("sftp")
        channel.connect()
        assertTrue(channel.isConnected)

        channel.disconnect()
        session.disconnect()
    }

}