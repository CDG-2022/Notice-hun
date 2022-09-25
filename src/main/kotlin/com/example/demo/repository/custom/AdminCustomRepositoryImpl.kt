package com.example.demo.repository.custom

import com.example.demo.domain.Admin
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo


class AdminCustomRepositoryImpl(private val mongoTemplate: MongoTemplate) : AdminCustomRepository {
    override fun updatePassword(email: String, password: String): Admin? {
        val query: Query = Query().apply {
            addCriteria(Criteria.where("email").isEqualTo(email))

        }
        val update = Update().apply {
            set("password", password)
        }
        val options = FindAndModifyOptions.options()
            .returnNew(true)
            .upsert(false)
        return mongoTemplate.findAndModify(
            query,
            update,
            options,
            Admin::class.java
        )
    }
}