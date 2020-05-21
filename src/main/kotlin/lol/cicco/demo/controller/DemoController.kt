package lol.cicco.demo.controller

import lol.cicco.demo.dto.request.DemoRequest
import lol.cicco.demo.dto.response.DemoResponse
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class DemoController {

    @GetMapping("/demo")
    fun demo(): DemoResponse {
        return DemoResponse(UUID.randomUUID(), "lalala")
    }

    @GetMapping("/demo2")
    fun demo2(@Valid request : DemoRequest, result : BindingResult) : DemoResponse {
        if(result.hasErrors()) {
            println(result.fieldError?.defaultMessage)
            return DemoResponse(UUID.randomUUID(), "Error");
        }
        return DemoResponse(UUID.randomUUID(), "lalala")
    }

}