package com.example.personal5_search_image.presentation.bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.personal5search_image.R
import com.example.personal5search_image.databinding.ItemSearchImageBinding
import java.text.SimpleDateFormat
import java.util.Locale

class BookmarkListAdapter
    : ListAdapter<BookmarkListItem, BookmarkListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<BookmarkListItem>() {
        override fun areItemsTheSame(
            oldItem: BookmarkListItem,
            newItem: BookmarkListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BookmarkListItem,
            newItem: BookmarkListItem
        ): Boolean {
            return oldItem == newItem
        }

    }
) {


    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(item: BookmarkListItem)
    }

    class ImageViewHolder(private val binding: ItemSearchImageBinding) : ViewHolder(binding.root) {

        override fun onBind(item: BookmarkListItem) = with(binding) {
            ivSearchImage.load(item.thumbnailUrl)
            tvSiteName.text = item.displaySiteName
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            tvDate.text = sdf.format(item.dateTime)
            if(item.isBookmarked)ivBookmaker.setImageResource(R.drawable.ic_bookmark_black)
            else ivBookmaker.setImageResource(R.drawable.ic_bookmark_none)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookmarkListAdapter.ViewHolder{
        return ImageViewHolder(
            ItemSearchImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarkListAdapter.ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}