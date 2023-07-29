package ru.netology.nmedia.dto

import ru.netology.nmedia.Like

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likedByMe: Boolean,
    val likes: Int,
    val shared: Int,
    val sharedByMe: Boolean,
    val views: Int
)
