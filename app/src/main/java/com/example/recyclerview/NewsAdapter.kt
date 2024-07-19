import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.News
import com.example.recyclerview.databinding.NewsItemBinding


class NewsAdapter(
    private val onItemClick: (News, Int) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val news = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(news[position])
    }

    override fun getItemCount() = news.size

    fun setData(data: List<News>) {
        news.clear()
        news.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: NewsItemBinding,
        private val onTitleClick: (News, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: News) {
            binding.apply {
                textTitle.text = item.title
                textContent.text = item.content

                textContent.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
                textTitle.setOnClickListener { onTitleClick(item, adapterPosition) }
            }
        }
    }
}
