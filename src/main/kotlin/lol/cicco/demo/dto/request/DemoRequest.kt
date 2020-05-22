package lol.cicco.demo.dto.request

import lol.cicco.demo.common.annotation.NoArgs
import javax.validation.constraints.NotEmpty

@NoArgs
data class DemoRequest(
        @get:NotEmpty(message = "ID不能为空")
        var id: String?,
        @get:NotEmpty(message = "Name不能为空")
        var name: String?) {
}