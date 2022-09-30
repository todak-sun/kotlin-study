package io.todak.study.kotlinsimplescraper.loader

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.nio.file.Paths

class WebDriverLoader {


    fun load(): ChromeDriver {
        val WEB_DRIVER_ID = "webdriver.chrome.driver"
        val WEB_DRIVER_PATH =
            Paths.get("src", "main", "resources", "webdriver", "chromedriver.exe").toFile().absolutePath

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH)

        val options = ChromeOptions()
        val chromeDriver = ChromeDriver(options)

        return chromeDriver
    }

}