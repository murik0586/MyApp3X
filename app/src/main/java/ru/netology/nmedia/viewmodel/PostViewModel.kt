package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryMemoryImplementation

class PostViewModel: ViewModel() {

    private val repository: PostRepository = PostRepositoryMemoryImplementation()

    val data = repository.get()

    fun like() = repository.like()
    fun shared() = repository.shared()
}