package lol.cicco.demo.controller

import lol.cicco.demo.common.exception.BusinessException
import lol.cicco.demo.common.model.R
import lol.cicco.demo.dto.request.DemoRequest
import lol.cicco.demo.dto.response.DemoResponse
import lol.cicco.demo.entity.TestEntity
import lol.cicco.demo.mapper.TestMapper
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class DemoController : InitializingBean {
    @Autowired
    lateinit var mapper : TestMapper

    @GetMapping("/demo")
    fun demo(): DemoResponse {
        throw BusinessException.make("lalalala")
    }

    @GetMapping("/demo2")
    fun demo2(@Validated request : DemoRequest) : DemoResponse {
        return DemoResponse(UUID.randomUUID(), "lalala")
    }

    @GetMapping("/findAll")
    fun findAll() : R {
        return R.ok(mapper.findAll())
    }

    @GetMapping("/save")
    fun save() : R {
        val entity = TestEntity(UUID.randomUUID().toString(), "lalala")
        mapper.save(entity)
        return R.ok()
    }

    override fun afterPropertiesSet() {
        mapper.createTable()
    }

}