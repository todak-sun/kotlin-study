package io.todak.study.kotlin.kotlinspring.controller.post

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PostApiController {

    class Person(val name: String, val age: Int)

    @PostMapping("/post-mapping")
    fun postMapping(@RequestBody person: Person): String {
        println("${person.name}의 나이는 ${person.age}")
        return "post-mapping"
    }

}