package com.example.demo.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime


@Document(collection = "notice")
data class Notice(
    @Id
    val id: String? = null,
    val title: String,
    val text: String,
    @Field("update_time")
    val updateTime: LocalDateTime,

    val email: String,
)