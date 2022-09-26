package com.example.demo.controller

import com.example.demo.domain.Notice
import com.example.demo.service.NoticeService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
class NoticeController(private val noticeService: NoticeService) {

    @GetMapping("/notices")
    fun getALL(@PageableDefault(size = 5) pageable: Pageable) =
        noticeService.findNotices(pageable)

//    @GetMapping("/notices/title/{title}")
//    fun getByTitle(@PathVariable("title") title: String) =
//        noticeService.findByTitle(title)?.let { notice ->
//            NoticeDto(title = notice.title, text = notice.text, updateTime = notice.updateTime, email = notice.email)
//        }

    @PostMapping("/notices")
    fun create(@RequestBody noticeDto: NoticeDto) =
        noticeService.saveNotice(
            Notice(
                title = noticeDto.title,
                text = noticeDto.text,
                updateTime = LocalDateTime.now(),
                email = noticeDto.email
            )
        )

    @PatchMapping("/notices/{title}")
    fun update(@RequestBody noticeDto: NoticeDto) =
        noticeService.updateNotice(title = noticeDto.title, text = noticeDto.text)


    @GetMapping("/notices/text/{text}")
    fun getByText(@PathVariable("text") text: String) =
        noticeService.findByText(text).stream().map {
            it
        }


    @DeleteMapping("/notices/{title}")
    fun delete(@RequestBody noticeDto: NoticeDto) =
        noticeService.findByTitle(noticeDto.title)?.let { notice ->
            noticeService.deleteNotice(notice)
        }




    class NoticeDto(val title: String, val text: String, val updateTime: LocalDateTime, val email: String)
    class NoticeDto2(val title: String, val text: String, val updateTime: LocalDateTime, val email: String)
}