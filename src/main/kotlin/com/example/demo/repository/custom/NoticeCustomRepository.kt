package com.example.demo.repository.custom

import com.example.demo.domain.Notice

interface NoticeCustomRepository {
    fun updateText(title: String, text: String): Notice?
}