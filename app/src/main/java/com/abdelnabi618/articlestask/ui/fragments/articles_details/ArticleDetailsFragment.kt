package com.abdelnabi618.articlestask.ui.fragments.articles_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.abdelnabi618.articlestask.databinding.FragmentArticleDetailsBinding


class ArticleDetailsFragment : Fragment() {

    private val articleId: Int by lazy {
        navArgs<ArticleDetailsFragmentArgs>().value.articleId
    }

    private val articleDetailsFragmentBinding: FragmentArticleDetailsBinding by lazy {
        FragmentArticleDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return articleDetailsFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}