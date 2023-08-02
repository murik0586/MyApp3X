package ru.netology.nmedia.repository


import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
//    fun like(id: Long)
  fun shared(id: Long)
    fun removeById(id: Long)
    fun save (post: Post)
   // fun edit (post: Post)

    fun shareById(id: Long)
    fun likeById(id: Long)
}
