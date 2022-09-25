package com.example.demo.repository.custom

import com.example.demo.domain.Notice
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import java.time.LocalDateTime

class NoticeCustomRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : NoticeCustomRepository {
    override fun updateText(title: String, text: String): Notice? {
        val query: Query = Query().apply {
            addCriteria(Criteria.where("title").isEqualTo(title))

        }
        val update = Update().apply {
            set("text", text)
            set("title", title)
            set("update_time", LocalDateTime.now())
        }
        val options = FindAndModifyOptions.options()
            .returnNew(true)
            .upsert(false)
        return mongoTemplate.findAndModify(
            query,
            update,
            options,
            Notice::class.java
        )
    }

}