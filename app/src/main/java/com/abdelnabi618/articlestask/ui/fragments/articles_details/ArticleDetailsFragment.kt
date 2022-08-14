package com.abdelnabi618.articlestask.ui.fragments.articles_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.abdelnabi618.articlestask.R
import com.abdelnabi618.articlestask.databinding.FragmentArticleDetailsBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {

    private val articleId: Int by lazy {
        navArgs<ArticleDetailsFragmentArgs>().value.articleId
    }

    private val articleDetailsViewModel: ArticleDetailsViewModel by viewModels()

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

        lifecycleScope.launchWhenResumed {

            launch {
                articleDetailsViewModel.loadingStateFlow.collectLatest {
                    articleDetailsFragmentBinding.articleDetailsLoadingPb.isVisible = it
                }
            }

            launch {
                articleDetailsViewModel.articleStateFlow.collectLatest {
                    articleDetailsFragmentBinding.articleModel = it
                    it?.tags?.forEach{ tag ->
                        addTagChip(tag)
                    }
                }
            }

            launch {
                articleDetailsViewModel.getSingleArticle(articleId)
            }
        }

        onEvent()
    }

    private fun onEvent() {
        articleDetailsFragmentBinding.articleDetailsBackImg.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun addTagChip(tag: String) {
        val lChip = Chip(requireContext())
        lChip.text = tag
        lChip.setTextColor(ActivityCompat.getColorStateList(requireContext(),R.color.black))
        lChip.chipBackgroundColor = ActivityCompat.getColorStateList(requireContext(), R.color.primer_babe_blue)
        articleDetailsFragmentBinding.articleDetailsTagsCg.addView(lChip)
    }

}