package io.todak.study.kotlinsimplescraper.loader

import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class WebDriverLoaderTest {

    @Test
    fun loadTest() {
        val driver = WebDriverLoader().load()
        try {
            driver.get("http://gs25.gsretail.com/gscvs/ko/products/event-goods")

            val foundedElement =
                driver.findElement(By.cssSelector("#contents > div.cnt > div.cnt_section.mt50 > div > div > div:nth-child(3) > ul"))

            val productBoxes = foundedElement.findElements(By.cssSelector("li > div.prod_box"))
            productBoxes.forEach { box ->
                val imageSrc = box.findElement(By.cssSelector("p.img > img")).getAttribute("src")
                val title = box.findElement(By.cssSelector("p.tit")).text
                val price = box.findElement(By.cssSelector("p.price > span.cost")).text
                println("imageSrc: ${imageSrc}, title: ${title}, price: $price")
            }
        } finally {
            driver.close()
        }


    }

}

class TempDto (val imageSrc: String, val title: String){

}