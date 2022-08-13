package com.abdelnabi618.articlestask.ui.fragments.articles_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.abdelnabi618.articlestask.databinding.FragmentArticlesListBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class ArticlesListFragment : Fragment() {

    private val articlesListBinding by lazy {
        FragmentArticlesListBinding.inflate(layoutInflater)
    }

    private val articlesViewModel: ArticlesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return articlesListBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launchWhenResumed {
            articlesViewModel.getAllArticles().collectLatest {
                Log.e("TAG", "onViewCreated: ${Gson().toJson(it)}", )
            }

        }




    }


}