package com.abdelnabi618.articlestask.ui.fragments.articles_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdelnabi618.articlestask.R
import com.abdelnabi618.articlestask.databinding.FragmentArticlesListBinding


class ArticlesListFragment : Fragment() {

    private val articlesListBinding by lazy {
        FragmentArticlesListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return articlesListBinding.root
    }


}