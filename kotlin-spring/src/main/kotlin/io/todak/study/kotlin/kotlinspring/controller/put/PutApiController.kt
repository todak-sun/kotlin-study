package io.todak.study.kotlin.kotlinspring.controller.put

import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController {

    data class ResultSample(var code: String? = null, var message: String? = null)

    @PutMapping("/put-mapping")
    fun putMapping(): String {

        ResultSample().apply {
            this.code = "CODE"
            this.message = "MESSAGE"
        }

        return "put-mapping"
    }


}