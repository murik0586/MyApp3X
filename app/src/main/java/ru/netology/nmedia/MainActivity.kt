
    package ru.netology.nmedia.activity

    import android.os.Bundle
    import androidx.activity.viewModels
    import androidx.appcompat.app.AppCompatActivity
    import ru.netology.nmedia.R
    import ru.netology.nmedia.adapter.PostsAdapter
    import ru.netology.nmedia.databinding.ActivityMainBinding
    import ru.netology.nmedia.databinding.CardPostBinding
    import ru.netology.nmedia.viewmodel.PostViewModel

    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val viewModel: PostViewModel by viewModels() //получаем viewmodel

            val adapter = PostsAdapter ({ viewModel.like(it.id)}, {viewModel.shared(it.id)})

            binding.list.adapter = adapter

            viewModel.data.observe(this) { posts ->
                adapter.submitList(posts)
            }
        }
    }




