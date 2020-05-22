package lol.cicco.demo.mapper

import lol.cicco.demo.entity.TestEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface TestMapper {

    fun createTable()

    fun findAll() : List<TestEntity>

    fun save(entity : TestEntity)

}