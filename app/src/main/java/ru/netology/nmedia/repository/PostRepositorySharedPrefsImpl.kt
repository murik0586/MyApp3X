package ru.netology.nmedia.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.dto.Post

class PostRepositorySharedPrefsImpl( context: Context) : PostRepository {
    //реализация интерфейса, в которой пост храниться в переменной post
    //в классе также есть свойство типа MutableLiveData куда записана переменная post
    //функция get возвращает эту переменную, то есть отдает пост
    //функция like создает копию поста у которого изменено поле likedByMe, и записывает измененный пост в переменную data в свойство value
    private var nextId = 1L
    private var posts = emptyList<Post>()
    private val gson = Gson() //вызываем конструктор класса гсон
    private val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private val key = "posts"
    private val typeToken = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val data = MutableLiveData(posts)

    init {
        prefs.getString(key, null)?.let { //чтение
            posts = gson.fromJson(it, typeToken)
            nextId = (posts.maxOfOrNull {it.id} ?: 0) + 1 // присваиваем максимальное значение из текущих, которые есть в списке
            data.value = posts
        }
    }

    private fun sync() {
        prefs.edit().apply() {
            putString(key, gson.toJson(posts))
            apply()
        }
    }

    override fun getAll(): LiveData<List<Post>> = data
    override fun shared(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(shared = it.shared + 1)
        }
        data.value = posts
        sync()
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Me",
                    likedByMe = false,
                    published = "now"
                )
            ) + posts
            data.value = posts
            sync()
            return
        }

        posts = posts.map { //если id совпали, значит произошло редактирование
            if (it.id != post.id) it else it.copy(content = post.content)

        }
        data.value = posts
        sync()
    }


    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(likedByMe = !it.likedByMe, likes = liked(it))
            }

        }
        data.value = posts
        sync()
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(shared = it.shared + 1)
        }
        data.value = posts
        sync()
    }


    fun liked(post: Post): Int {
        return if (post.likedByMe) post.likes - 1 else post.likes + 1
    }

    override fun removeById(id: Long) {
        posts =
            posts.filter { it.id != id }
        data.value = posts
        sync()
    }
}