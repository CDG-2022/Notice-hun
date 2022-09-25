package com.example.demo.controller

import com.example.demo.domain.Admin
import com.example.demo.service.AdminService
import org.springframework.web.bind.annotation.*

@RestController
class AdminController(private val adminService: AdminService) {

    @PostMapping("/admins")
    fun create(admin: Admin) =
        adminService.saveAdmin(admin)

    @GetMapping("/admins")
    fun find(email: String) =
        adminService.get(email)

    @DeleteMapping("/admins")
    fun delete(email: String) =
        adminService.get(email)?.let {admin-> adminService.deleteAdmin(admin) }

    @PatchMapping("/admins")
    fun update(@RequestBody password: String, email: String) =
        adminService.updatePassword(email = email, password = password)
}