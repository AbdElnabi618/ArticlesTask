package com.abdelnabi618.articlestask.ui.fragments.articles_list;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdelnabi618.articlestask.databinding.ItemArticleBinding
import com.abdelnabi618.articlestask.model.ArticlesModel

class ArticlesAdapter(private var itemClicked: OnItemClick? = null) :
    PagingDataAdapter<ArticlesModel, ArticlesAdapter.ArticlesViewHolder>(ArticlesComparator) {

    inner class ArticlesViewHolder(var binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindDataToView(model: ArticlesModel?) {
            binding.articleModel = model
        }

        fun onEvent(model: ArticlesModel?) {
            binding.root.setOnClickListener {
                itemClicked?.itemClicked(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val model = getItem(position)
        holder.bindDataToView(model)
        holder.onEvent(model)
    }

    interface OnItemClick {
        fun itemClicked(item: ArticlesModel?)
    }


    object ArticlesComparator : DiffUtil.ItemCallback<ArticlesModel>() {
        override fun areItemsTheSame(oldItem: ArticlesModel, newItem: ArticlesModel): Boolean {
            return oldItem.apiId == newItem.apiId
        }

        override fun areContentsTheSame(oldItem: ArticlesModel, newItem: ArticlesModel): Boolean {
            return oldItem == newItem
        }
    }
}