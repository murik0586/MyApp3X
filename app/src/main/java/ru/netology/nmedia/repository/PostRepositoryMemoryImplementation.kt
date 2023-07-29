package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryMemoryImplementation : PostRepository {

    private var posts = listOf(
        Post(
            1,
            "Нетология. Университет интернет профессий будущего",
            "16 мая в 10:00",
            "Привет. Это новая Нетология. Когда-то Нетология начиналась с интенсивов по онлайн-маркетингую Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netolo.gy/fyb",
            true,
            250,
            1000,
            false,
            20
        ),
        Post(
            1,
            "Аниматор Вася",
            "2 августа в 21:30",
            "сегодня мы по снекам!!! Нам не нужен нетфликс, мы сегодня сами рисуем мультики!",
            true,
            1,
            1,
            true,
            3121
        ),

        Post(
            1,
            "Мурат Мусаев",
            "1 июля в 01:00",
            "Сегодня я вам покажу моего кота, в образе программиста!",
            true,
            1,
            1,
            true,
            542
        ),
        Post(
            1,
            "Нетология. Университет интернет профессий будущего",
            "21 мая в 12:00",
            "Перейдите по ссылки, чтобы увидеть новые возможности http://netolo.gy/fyb",
            true,
            1656,
            1643432,
            false,
            2854
        )
    )

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data


    override fun like(id: Long) {
        posts = posts.map{
            if (it.id != id) {
                it
            } else {
                it.copy(likedByMe = !it.likedByMe, likes = likes(it))
            }

    }
        data.value = posts
    }

    override fun shared(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(sharedByMe = !it.sharedByMe, shared = it.shared + 1 )
        }
        data.value = posts
    }
}
fun likes(post: Post): Int {
    return if (post.likedByMe) post.likes - 1 else post.likes + 1
}