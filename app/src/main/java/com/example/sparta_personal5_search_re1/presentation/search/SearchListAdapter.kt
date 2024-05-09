package com.example.sparta_personal5_search_re1.presentation.search

import android.icu.util.LocaleData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sparta_personal5_search_re1.R
import com.example.sparta_personal5_search_re1.databinding.ItemSearchBinding
import java.text.SimpleDateFormat
import java.util.Locale

class SearchListAdapter
    :ListAdapter<SearchListItem, SearchListAdapter.ViewHolder>(
        object :DiffUtil.ItemCallback<SearchListItem>(){
            override fun areItemsTheSame(
                oldItem: SearchListItem,
                newItem: SearchListItem
            ): Boolean {
                return oldItem.thumbnailUrl == newItem.thumbnailUrl
            }

            override fun areContentsTheSame(
                oldItem: SearchListItem,
                newItem: SearchListItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchListAdapter.ViewHolder {
        return ImageViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchListAdapter.ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    abstract class ViewHolder(
        view: View)
    : RecyclerView.ViewHolder(view){
        abstract fun onBind(item:SearchListItem)
    }

    class ImageViewHolder(
        private val binding: ItemSearchBinding
    ) :ViewHolder(binding.root){
            override fun onBind(item: SearchListItem) = with(binding){
                ivSearch.load(item.thumbnailUrl)
                tvSiteName.text = item.siteName
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                tvDateTime.text = sdf.format(item.dateTime)

                if(item.isBookmarked == true) ivBookmarker.setImageResource(R.drawable.ic_bookmark_red)
                else ivBookmarker.setImageResource(R.drawable.ic_bookmark_none)
            }
        }
}