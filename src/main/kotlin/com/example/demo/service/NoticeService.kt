package com.example.demo.service

import com.example.demo.domain.Notice
import com.example.demo.repository.NoticeRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class NoticeService(private val noticeRepository: NoticeRepository) {

    fun saveNotice(notice: Notice) = noticeRepository.save(notice)

    fun getNotices(pageable: Pageable): MutableList<Notice> = noticeRepository.findAll(pageable).toList()

    fun getNoticesByTitle(title: String): Notice? = noticeRepository.findByTitle(title)

    fun updateNotice(title: String, text: String) = noticeRepository.updateText(title, text)

    fun deleteNotice(notice: Notice) = noticeRepository.delete(notice)
}