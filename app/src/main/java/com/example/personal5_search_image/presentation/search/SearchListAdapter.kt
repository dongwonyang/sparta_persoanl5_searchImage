package com.example.personal5_search_image.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.personal5search_image.R
import com.example.personal5search_image.databinding.ItemSearchImageBinding
import com.example.personal5search_image.databinding.ItemUnknownBinding
import java.text.SimpleDateFormat
import java.util.Locale

class SearchListAdapter(
    private val onBookmark: (SearchListItem) -> Unit
) : ListAdapter<SearchListItem, SearchListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<SearchListItem>() {
        override fun areItemsTheSame(oldItem: SearchListItem, newItem: SearchListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchListItem, newItem: SearchListItem): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SearchListItem.ImageItem -> ViewType.IMAGE.ordinal
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return when (viewType) {
            ViewType.IMAGE.ordinal -> {
                ImageViewHolder(
                    ItemSearchImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onBookmark
                )
            }

            else -> {
                UnknownViewHolder(
                    ItemUnknownBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int {
        return (currentList.size + 1) / 2
    }

    enum class ViewType {
        IMAGE
    }

    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(item: SearchListItem)
    }

    class ImageViewHolder(
        private val binding: ItemSearchImageBinding,
        private val onBookmark: (SearchListItem) -> Unit
    ) : ViewHolder(binding.root) {
        override fun onBind(item: SearchListItem): Unit = with(binding) {
            ivSearchImage.load(item.thumbnailUrl)
            tvSiteName.text = item.displaySiteName
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            tvDate.text = sdf.format(item.dateTime)

            if(item.isBookmarked){
                ivBookmaker.setImageResource(R.drawable.ic_bookmark_black)
            } else ivBookmaker.setImageResource(R.drawable.ic_bookmark_none)

            ivBookmaker.setOnClickListener {
                onBookmark(item)
            }
        }
    }

    class UnknownViewHolder(
        binding: ItemUnknownBinding
    ) : ViewHolder(binding.root) {
        override fun onBind(item: SearchListItem) {
            TODO("Not yet implemented")
        }
    }
}