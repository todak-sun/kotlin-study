package io.study.kotlininaction._06

class MyService {
    fun performAction(): String = "Hello World"
}

class MyTest {
    private lateinit var myService: MyService;

    fun setUp() {
        myService = MyService()
    }

    fun testAction() {
        myService.performAction()
    }

}