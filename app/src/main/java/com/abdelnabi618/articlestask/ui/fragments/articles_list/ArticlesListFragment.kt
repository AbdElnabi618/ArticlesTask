package com.abdelnabi618.articlestask.ui.fragments.articles_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
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
        // Inflate the layout for this fragment
        return articlesListBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleView()

        observeData()
    }

    private fun initRecycleView() {
        articlesListBinding.articlesListRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articlesAdapter
        }

    }

    private fun observeData() {

        articlesAdapter.addLoadStateListener { loadState ->

            articlesListBinding.articlesCenterLoadingPb.isVisible =
                loadState.refresh is LoadState.Loading
            articlesListBinding.articlesFooterLoadingPb.isVisible =
                loadState.append is LoadState.Loading

            // If we have an error, show a toast
            getErrorStateOrNull(loadState)?.let {
                Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG).show()
            }
        }

        lifecycleScope.launchWhenResumed {
            articlesViewModel.getAllArticles().collectLatest {
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
        // todo open new fragment
    }


}