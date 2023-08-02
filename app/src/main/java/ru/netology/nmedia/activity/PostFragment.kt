

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.NewPostFragment.Companion.textArg
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostViewHolder
import ru.netology.nmedia.databinding.FragmentPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.LongArg
import ru.netology.nmedia.viewmodel.PostViewModel

class PostFragment : Fragment() {
    companion object {
        var Bundle.postId by LongArg
    }
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostBinding.inflate(
            inflater,
container,
false
)
val postViewHolder = PostViewHolder(binding.post, object : OnInteractionListener {
    override fun onEdit(post: Post) {
        viewModel.edit(post)
        findNavController().navigate(R.id.action_postFragment_to_newPostFragment,
            Bundle().apply {
                textArg = post.content
            }
        )
    }
    override fun onLike(post: Post) {
        viewModel.likeById(post.id)
    }
    override fun onRemove(post: Post) {
        viewModel.removeById(post.id)}

override fun onShare(post: Post) {
    viewModel.shareById(post.id)
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, post.content)
        type = "text/plain"
    }
    val shareIntent =
        Intent.createChooser(intent, getString(R.string.chooser_share_post))
    startActivity(shareIntent)
}
})
viewModel.data.observe(viewLifecycleOwner) { posts ->
    val post = posts.find { it.id == requireArguments().postId } ?: run {
        findNavController().navigateUp()
        return@observe
    }
    postViewHolder.bind(post)
}
postViewHolder.bind(Post(1, "Me", "content", "now", true,0,0))
return binding.root
}
}