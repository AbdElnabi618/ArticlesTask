package com.abdelnabi618.articlestask.ui.fragments.articles_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdelnabi618.articlestask.R
import com.abdelnabi618.articlestask.databinding.FragmentArticlesListBinding
import com.abdelnabi618.articlestask.model.ArticlesModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class ArticlesListFragment : Fragment(), ArticlesAdapter.OnItemClick {

    private val articlesListBinding by lazy {
        FragmentArticlesListBinding.inflate(layoutInflater)
    }

    private val articlesViewModel: ArticlesViewModel by viewModels()

    private val articlesAdapter: ArticlesAdapter by lazy {
        ArticlesAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return articlesListBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleView()
        onEvent()
        observeData()
    }

    private fun initRecycleView() {
        articlesListBinding.articlesListRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articlesAdapter
        }

    }

    private fun onEvent() {
        articlesListBinding.articlesFilterImg.setOnClickListener {
            if (articlesListBinding.articleListFilterEt.isVisible) {
                articlesListBinding.articleListFilterEt.isVisible = false
                getAllArticles()
            } else {
                articlesListBinding.articleListFilterEt.isVisible = true
            }
        }

        articlesListBinding.articleListFilterEt.doOnTextChanged { text, _, _, _ ->
            text?.let {
                val articleId = it.toString().toInt()
                if (articleId != 0){
                    filterArticles(articleId)
                }else{
                    getAllArticles()
                }
            }
        }
    }

    private fun observeData() {

        articlesAdapter.addLoadStateListener { loadState ->

            articlesListBinding.articlesCenterLoadingPb.isVisible =
                loadState.refresh is LoadState.Loading
            articlesListBinding.articlesFooterLoadingPb.isVisible =
                loadState.append is LoadState.Loading

            getErrorStateOrNull(loadState)?.let {
                Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG).show()
            }
        }

        getAllArticles()
    }

    private fun getAllArticles() {
        lifecycleScope.launchWhenResumed {
            articlesViewModel.getAllArticles().collectLatest {
                articlesAdapter.submitData(it)
            }
        }
    }

    private fun filterArticles(articleId: Int) {
        lifecycleScope.launchWhenResumed {
            articlesViewModel.filterArticles(articleId).collectLatest {
                articlesAdapter.submitData(it)
            }
        }
    }

    private fun getErrorStateOrNull(loadState: CombinedLoadStates) = when {
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        else -> null
    }

    override fun itemClicked(item: ArticlesModel?) {
        if (item != null) {
            findNavController().navigate(
                ArticlesListFragmentDirections.actionArticlesListFragmentToArticleDetailsFragment(
                    item.apiId
                )
            )
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.msg_no_data_found),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}