package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryMemoryImplementation
private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = true,
    published = "",
    likes = 0,
    shared = 0,
    sharedByMe = false,
    views = 0
)
class PostViewModel: ViewModel() {

    private val repository: PostRepository = PostRepositoryMemoryImplementation()

    val data = repository.getAll()
    val edited = MutableLiveData (empty)

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
    fun like(id: Long) = repository.like(id)
    fun shared(id: Long) = repository.shared(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun cancelEdit() {
        edited.value = empty
    }
}