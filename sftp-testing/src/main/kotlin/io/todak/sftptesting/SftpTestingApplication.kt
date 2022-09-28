package io.todak.sftptesting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SftpTestingApplication

fun main(args: Array<String>) {
    runApplication<SftpTestingApplication>(*args)
}
