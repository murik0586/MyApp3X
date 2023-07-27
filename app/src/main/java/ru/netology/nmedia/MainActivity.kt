package ru.netology.nmedia

//import android.util.Log
//import android.view.View
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.FormatDigital.formatNumber
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

//import ru.netology.nmedia.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            1,
            "Нетология. Университет интернет профессий будущего",
            "16 мая в 10:00",
            "Привет. Это новая Нетология. Когда-то Нетология начиналась с интенсивов по онлайн-маркетингую Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netolo.gy/fyb",
            true,
            11000,
            1,
            true,
            20

            )


        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likess.text = post.likes.toString()

            binding.shared?.text = formatNumber(post.shared)
            binding.likess?.text = formatNumber(post.likes)
            binding.view.text = formatNumber(post.views)
            root.setOnClickListener {
                Log.d("stuff", "stuff")
            }
            authorAvatars.setOnClickListener {
                Log.d("stuff", "avatar")
            }
            shared?.setOnClickListener {
                Log.d("stuff", "share")
                post.sharedByMe = !post.sharedByMe
                post.shared++
                shared.text = formatNumber(post.shared)
            }

            if (post.likedByMe) {
                like.setImageResource(R.drawable.baseline_favorite_24)
            }


            like.setOnClickListener {
                print("liked clicked")
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) post.likes++ else post.likes--

                like.setImageResource(if (post.likedByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
                likess.text = post.likes.toString()
                likess.text = formatNumber(post.likes)

            }


        }



        //var counter = 0
        //with(binding.shared) {
          //  setOnClickListener {
            //  text = DigitalFormat.format(++counter)
            //}
        //}

    }
}

