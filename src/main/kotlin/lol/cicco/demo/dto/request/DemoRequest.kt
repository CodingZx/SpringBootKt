package lol.cicco.demo.dto.request

import javax.validation.constraints.NotEmpty

class DemoRequest {
    @NotEmpty(message = "ID不能为空")
    lateinit var id : String
    @NotEmpty(message = "Name不能为空")
    lateinit var name : String
}