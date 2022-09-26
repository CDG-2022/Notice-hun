package com.example.demo.repository

import com.example.demo.domain.Notice
import com.example.demo.repository.custom.NoticeCustomRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface NoticeRepository : MongoRepository<Notice, Long>, NoticeCustomRepository {
    fun findByTitle(title: String): Notice?

    @Query(fields = """{ 'title' : true}""")
    fun findByTextContains(text: String): List<Dto?>

    data class Dto(val title: String)
}