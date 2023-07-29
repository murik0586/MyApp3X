package ru.netology.nmedia

//import android.util.Log
//import android.view.View
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.FormatDigital.formatNumber
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

//import ru.netology.nmedia.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->


            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likess.text = post.likes.toString()

                binding.shared?.text = formatNumber(post.shared)
                binding.likess?.text = formatNumber(post.likes)
                binding.view.text = formatNumber(post.views)
                root.setOnClickListener {
                    Log.d("root", "root")
                }
                authorAvatars.setOnClickListener {
                    Log.d("avatar", "avatar")
                }
//                shared?.setOnClickListener {
//                    Log.d("share", "share")
//                    post.sharedByMe = !post.sharedByMe
//                    post.shared++
//                    shared.text = formatNumber(post.shared)
//                }

                like.setImageResource(if (post.likedByMe) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)

                likess.text = post.likes.toString()
                likess.text = formatNumber(post.likes)



//                    likess.text = post.likes.toString()
//                    likess.text = formatNumber(post.likes)

                }
            }
        binding.like.setOnClickListener {
           viewModel.like()

            binding.shared?.setOnClickListener {
                Log.d("share", "share")
//                post.sharedByMe = !post.sharedByMe   //данные которые надо ввести в viewmodel
//                post.shared++
//                shared.text = formatNumber(post.shared)
                viewModel.shared()
            }

        }
    }
}

