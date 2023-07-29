package ru.netology.nmedia.repository

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryMemoryImplementation : PostRepository {

    private var post = Post(
        1,
        "Нетология. Университет интернет профессий будущего",
        "16 мая в 10:00",
        "Привет. Это новая Нетология. Когда-то Нетология начиналась с интенсивов по онлайн-маркетингую Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netolo.gy/fyb",
        true,
        1,
        1,
        true,
        20

    )
    val data = MutableLiveData(post)


    override fun get() = data

    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe, likes = post.likes + if (post.likedByMe) - 1 else +1)
        data.value = post
    }
    override fun shared() { //будет изменять пост
        post = post.copy(sharedByMe = !post.sharedByMe, shared = post.shared + 1)
        data.value = post
    }

}