package lol.cicco.demo.entity

import lol.cicco.demo.common.annotation.GsonIgnore
import lol.cicco.demo.common.annotation.NoArgs
import lol.cicco.demo.common.util.gson

@NoArgs
data class TestEntity(
        @GsonIgnore
        var id : String,
        var name : String
) {
}
