package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.FormatDigital.formatNumber
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit
typealias OnRemoveListener = (post: Post) -> Unit

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onRemove(post: Post) {}

    fun onShare(post: Post) {}
    fun onEdit(post: Post) {}

    fun onCancelEdit(post: Post) {}

    fun onVideo(post: Post) {}
}
class PostsAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder { // ДЛЯ СОЗДАНИЯ ВЕРСТКИ
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostViewHolder(
            binding,
            onInteractionListener
        ) //для каждого холдера мы должны передать верстку(binding) и вторым параметром передаем весь набор функций, которые отслеживают клики(onInteractionListener)
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) { //ДЛЯ СВЯЗЫВАНИЯ ДАННЫХ, когда item готовится к отображению
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    //для каждого холдера мы должны передать верстку(binding) и вторым параметром передаем весь набор функций, которые отслеживают клики(onInteractionListener)
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) { // фун bind связывает данные с нашим интерфейсом который создан из верстки
        binding.apply {
            var videoLayout = videoGroup
            if (post.video != null) {
                videoLayout.visibility = View.VISIBLE
            } else {
                videoLayout.visibility = View.GONE
            }

            author.text = post.author
            published.text = post.published
            content.text = post.content
            share.text = formatNumber(post.shared)
            viewsCount.text = formatNumber(post.views)
            like.isChecked = post.likedByMe
            like.text = "${formatNumber(post.likes)}"


            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post) // из onInteractionListener мы вызываем нужный нам обработчик. В параметр передаем пост, т.е. будет срабатывать на опред пост
                                true // обязательно должны вернуть true. Означает, что данный клик по пункту меню уже был нам обработан и дальше обработчики уже вызывать не нужно. Иначе ОС попытается найти все оставщиеся обработчики
                            }

                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }
            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            video.setOnClickListener {
                onInteractionListener.onVideo(post)
            }

            videoButton.setOnClickListener {
                onInteractionListener.onVideo(post)
            }

        }
    }
}
class PostDiffCallback :
    DiffUtil.ItemCallback<Post>() { // поскольку используем ListAdapter, то в ListAdapter мы можем использовать ItemCallback из DiffUtil
    override fun areItemsTheSame(
        oldItem: Post,
        newItem: Post
    ): Boolean { // умеет сравнивать два листа и понимать что у них изменилось. то есть какая запись была в листе удалена, добавлена или отредактирована
        return oldItem.id == newItem.id // когда DiffUtil узнает какой элемент как изменился, он передает это в адаптер и адаптер сам принимает решение какую анимацию как применить
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}


