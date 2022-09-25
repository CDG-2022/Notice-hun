package com.example.demo.repository.custom

import com.example.demo.domain.Admin

interface AdminCustomRepository {
    fun updatePassword(email: String, password: String): Admin?
}