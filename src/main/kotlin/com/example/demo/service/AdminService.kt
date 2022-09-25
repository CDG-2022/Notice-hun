package com.example.demo.service

import com.example.demo.domain.Admin
import com.example.demo.repository.AdminRepository
import org.springframework.stereotype.Service

@Service
class AdminService(private val adminRepository: AdminRepository) {

    fun saveAdmin(admin: Admin) = adminRepository.save(admin)

    fun updatePassword(email: String, password: String) = adminRepository.updatePassword(email, password)

    fun deleteAdmin(admin: Admin) = adminRepository.delete(admin)

    fun get(email: String) = adminRepository.findByEmail(email)
}