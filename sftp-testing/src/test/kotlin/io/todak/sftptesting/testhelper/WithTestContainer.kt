package io.todak.sftptesting.testhelper

import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.ChannelSftp.LsEntry
import com.jcraft.jsch.JSch
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors

@Testcontainers
class WithTestContainer {

    companion object {

        private const val username: String = "sftpuser"
        private const val password: String = "strong!@#pasword"
        private val tempDirPath: File = Paths.get("src", "test", "resources", "tempdir").toFile()

        @Container
        private val sftpServierContainer: GenericContainer<*> =
            GenericContainer(DockerImageName.parse("atmoz/sftp:alpine-3.7"))
                .withExposedPorts(22)
                .withFileSystemBind(tempDirPath.absolutePath, "/home/${username}/tempdir", BindMode.READ_WRITE)
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

        val channel = session.openChannel("sftp") as ChannelSftp
        channel.connect()
        assertTrue(channel.isConnected)

        channel.cd("./tempdir")

        val lsEntries = channel.ls(".") as Vector<LsEntry>
        val founded = lsEntries.find { it.filename == "test.json" }
        assertNotNull(founded)

        founded?.let {
            val content = BufferedReader(InputStreamReader(channel.get(it.filename)))
                .use { br -> br.lines().collect(Collectors.joining()) }
            println(content)
        }

        channel.disconnect()
        session.disconnect()
    }

}