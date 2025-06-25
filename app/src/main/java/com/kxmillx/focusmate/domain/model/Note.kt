package com.kxmillx.focusmate.domain.model

import java.time.LocalDateTime

data class Note(
    val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
