package com.abdelnabi618.articlestask.ui.fragments.filter_articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdelnabi618.articlestask.databinding.FragmentFilterArticlesBinding
import com.abdelnabi618.articlestask.model.ArticlesModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilterArticlesFragment : Fragment(), FilterArticlesAdapter.OnItemClick {


    private val articlesFilterBinding by lazy {
        FragmentFilterArticlesBinding.inflate(layoutInflater)
    }

    private val articlesFilterViewModel: FilterArticlesViewModel by viewModels()

    private val articlesFilterAdapter: FilterArticlesAdapter by lazy {
        FilterArticlesAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return articlesFilterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleView()
        onEvent()
        observeData()
    }

    private fun initRecycleView() {
        articlesFilterBinding.articlesListRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articlesFilterAdapter
        }
    }

    private fun onEvent() {
        articlesFilterBinding.filterBackImg.setOnClickListener {
            requireActivity().onBackPressed()
        }

        articlesFilterBinding.articleListFilterEt.doOnTextChanged { text, _, _, _ ->
            text?.let {
                if (it.toString().isNotBlank() && it.toString().toInt() != 0) {
                    lifecycleScope.launch (Dispatchers.IO){
                        articlesFilterViewModel.filterArticles(it.toString().toInt())
                    }
                }else{
                    articlesFilterAdapter.list = listOf()
                }
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launchWhenResumed {
            articlesFilterViewModel.articlesFlow.collect {
                articlesFilterAdapter.list = it
            }
        }
    }

    override fun itemClicked(item: ArticlesModel) {
        findNavController().navigate(
            FilterArticlesFragmentDirections.actionFilterArticlesFragmentToArticleDetailsFragment(
                item.apiId
            )
        )
    }
}