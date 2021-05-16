package com.example.searchapp.ui.search

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.searchapp.R
import com.example.searchapp.databinding.SearchFragmentBinding
import com.example.searchapp.di.SearchComponentProvider
import com.example.searchapp.repository.model.BlocksItem
import com.example.searchapp.ui.search.adapter.ViewPagerAdapter
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding
    private var mViewPagerAdapter: ViewPagerAdapter? = null

    private val searchQueryListener: SearchView.OnQueryTextListener = object :
        SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            // no-op
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            if (newText.isNotEmpty())
                viewModel.setQueryText(newText)
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as SearchComponentProvider).getSearchComponent()
            .inject(
                this
            )
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        registerObservers()
        bindPagerWithTabLayout()
        bindAdapter()
        setListeners()
    }

    private fun setListeners() {
        binding.searchView.setOnQueryTextListener(
            searchQueryListener
        )
    }

    private fun registerObservers() {

        viewModel.blocksList.observe(viewLifecycleOwner, Observer { it ->
            it?.let {
                setupViewPagerData(it)
            }
        })

        viewModel.noTermFoundText.observe(viewLifecycleOwner, Observer { it ->
            it?.let {
                binNoTermFoundText(it)
            }
        })

    }

    private fun binNoTermFoundText(queryText: String) {
        val termNotFound: Spannable =
            SpannableString("${context?.getString(R.string.term)} $queryText ${context?.getString(R.string.not_found)}")

        context?.let {
            termNotFound.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.yellow)),
                getString(R.string.term).length,
                getString(R.string.term).length + queryText.length + 1,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            binding.noDataFound.text = termNotFound
        }
    }

    private fun setupViewPagerData(blocksItems: List<BlocksItem>) {
        mViewPagerAdapter?.addFrag(blocksItems)
        binding.viewPager.adapter = mViewPagerAdapter
        binding.viewPager.adapter?.notifyDataSetChanged()
    }


    private fun bindAdapter() {
        mViewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        binding.viewPager.adapter = mViewPagerAdapter
    }

    private fun bindPagerWithTabLayout() {
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}