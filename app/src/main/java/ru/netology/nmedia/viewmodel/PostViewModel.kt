package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository

import ru.netology.nmedia.repository.PostRepositoryFileImpl
private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    published = "",
    sharedByMe = false,
    likes = 0,
    shared = 0,
    views = 0,
    video = null
)
class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository =
        PostRepositoryFileImpl(application) // репозиторий с шередпрефс используем во вьюмодели


    val data = repository.getAll()
val edited = MutableLiveData(empty)

fun save() {
    edited.value?.let {
        repository.save(it)
    }
    edited.value = empty
}
fun edit(post: Post) {
    edited.value = post
}
fun changeContent(content: String) { // функция изменения контента
    val text = content.trim()
    if (edited.value?.content == text) {
        return
    }
    edited.value = edited.value?.copy(content = text)
}

fun likeById(id: Long) = repository.likeById(id)
fun shareById(id: Long) = repository.shared(id)

fun removeById(id: Long) = repository.removeById(id)
fun cancelEdit() {
    edited.value = empty
}
}
