package ru.netology.nmedia.dto

import ru.netology.nmedia.Like

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likedByMe: Boolean,
    var likes: Int,




    //var likes: Int = 0,
    //var likedByMe: Boolean = false,
    var shared: Int,
    var sharedByMe: Boolean,
    val views: Int
)
