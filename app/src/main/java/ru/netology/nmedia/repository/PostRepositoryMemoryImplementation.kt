package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

 class PostRepositoryMemoryImplementation : PostRepository {

    private var nextId = 1L
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
            2,
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
            3,
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
            4,
            "Нетология. Университет интернет профессий будущего",
            "21 мая в 12:00",
            "Перейдите по ссылки, чтобы увидеть новые возможности http://netolo.gy/fyb",
            true,
            1656,
            1643432,
            false,
            2854
        ), Post(
            5,
            "Вальтер",
            "28 июля в 02:40",
            "Они думают, что я сплю, когда они спят - но я выхожу гулять вместе со своей кошачьей братвой",
            false,
            1_111_111,
            654,
            true, 200_323_121
        ), Post(
            6,
            "Zero Fistashka",
            "5 июля в 21:45",
            "Сегодня интересный день, я напбрал мешок фисташек и разного рода орешкев, вот бы мне удержаться, а то я кажется влюбился в местные фисташки!",
            true,
            732,
            20,
            false,
            8211
        ),

        Post(
            6,
            "Иваныч Сергей Оборин",
            "12 июля в 02:02",
            "Эх,Коля - Коля, шутка далеко зашла.Кто видел местного утырка(фото прилагается) сообщите ему, за машину может не переживать",
            false,
            256,
            10,
            true,
            2_543
        ),
        Post(
            7,
            "Автосервис 'Пермьская у ВовТохи'",
            "25 июля в 09:33",
            "Мы короче с Вованом придумали идею, мы займемся незамерзайками, ждем вас завтра, местная незамерзайка, недорого!",
            false,
            257,
            0,
            true,
            14221
        )
    ).reversed()

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data
     fun likes(post: Post): Int {
         return if (post.likedByMe) post.likes - 1 else post.likes + 1}
    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    likedByMe = false,
                )
            ) + posts
        } else {
            posts = posts.map { //если id совпали, значит произошло редактирование
                if (it.id != post.id) it else it.copy(content = post.content)
            }
        }
        data.value = posts
    }


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

    override fun removeById(id: Long) {
        posts = posts.filter {it.id != id}
        data.value = posts
    }
}
fun likes(post: Post): Int {
    return if (post.likedByMe) post.likes - 1 else post.likes + 1
}


