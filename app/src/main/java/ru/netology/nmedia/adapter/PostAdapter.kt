package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.WallService



typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post : Post) -> Unit
typealias OnWatchListener = (post : Post) -> Unit

class PostAdapter (
    private val onLikeListener : OnLikeListener,
    private val onShareListener: OnShareListener,
    private val onWatchListener: OnWatchListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener, onWatchListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
    private val onWatchListener: OnWatchListener

) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post : Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            txtLiked.text = WallService.displayCount(post.likes)
            txtShare.text = WallService.displayCount(post.shares)
            txtWatch.text = WallService.displayCount(post.watches)
            imgbLiked.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )

            imgbShare.setImageResource(
                if (post.sharedByMe) R.drawable.ic_shared_24 else R.drawable.ic_baseline_share_24
            )

            imgbLiked.setOnClickListener {
                onLikeListener(post)
            }

            imgbShare.setOnClickListener {
                onShareListener(post)
            }

            imgbWatch.setOnClickListener {
                onWatchListener(post)
            }

        }
    }

}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}
