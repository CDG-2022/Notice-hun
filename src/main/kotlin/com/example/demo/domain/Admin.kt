package com.example.demo.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "admin")
data class Admin(
    @Id
    val id: String? = null,
    val name: String,
    val email: String,
    val password: String,
)
