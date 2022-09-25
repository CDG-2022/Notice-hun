package com.example.demo.repository

import com.example.demo.domain.Admin
import com.example.demo.repository.custom.AdminCustomRepository
import org.springframework.data.mongodb.repository.MongoRepository

interface AdminRepository : MongoRepository<Admin, String>, AdminCustomRepository {
    fun findByEmail(email: String): Admin?
}