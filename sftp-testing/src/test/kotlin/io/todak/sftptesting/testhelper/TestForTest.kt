package io.todak.sftptesting.testhelper

import org.junit.jupiter.api.Test
import java.nio.file.Paths

class TestForTest {

    @Test
    fun getCurrentDirectory(){
        val resourceDirectory = Paths.get("src", "test", "resources")
        val absolutePath = resourceDirectory.toFile().absolutePath
        println(absolutePath)
    }

}