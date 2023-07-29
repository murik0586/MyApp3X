package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryMemoryImplementation

class PostViewModel: ViewModel() {

    private val repository: PostRepository = PostRepositoryMemoryImplementation()

    val data = repository.getAll()

    fun like(id: Long) = repository.like(id)
    fun shared(id: Long) = repository.shared(id)
}