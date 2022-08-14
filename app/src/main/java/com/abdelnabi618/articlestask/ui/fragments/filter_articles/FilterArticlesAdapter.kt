package com.abdelnabi618.articlestask.ui.fragments.filter_articles

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdelnabi618.articlestask.databinding.ItemArticleBinding
import com.abdelnabi618.articlestask.model.ArticlesModel

class FilterArticlesAdapter(
    private var itemClicked: OnItemClick? = null
) : RecyclerView.Adapter<FilterArticlesAdapter.FilterArticlesViewHolder>() {

    var list: List<ArticlesModel> = arrayListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(newList) {
            field = newList
            notifyDataSetChanged()
        }

    inner class FilterArticlesViewHolder(var binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindDataToView(model: ArticlesModel) {
            binding.articleModel = model
        }

        fun onEvent(model: ArticlesModel) {
            binding.root.setOnClickListener {
                itemClicked?.itemClicked(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterArticlesViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterArticlesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FilterArticlesViewHolder, position: Int) {
        val model = list[position]
        holder.bindDataToView(model)
        holder.onEvent(model)
    }

    interface OnItemClick {
        fun itemClicked(item: ArticlesModel)
    }
}