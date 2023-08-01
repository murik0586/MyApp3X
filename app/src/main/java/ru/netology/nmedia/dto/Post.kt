package ru.netology.nmedia.dto


data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likedByMe: Boolean,
    val likes: Int,
    val shared: Int,
    val sharedByMe: Boolean = false,
    val views: Int = 0
)
/*package ru.netology.nmedia.dto

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
)*/
