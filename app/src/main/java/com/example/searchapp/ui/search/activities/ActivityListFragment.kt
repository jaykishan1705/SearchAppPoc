package com.example.searchapp.ui.search.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchapp.R
import com.example.searchapp.databinding.ActivityListFragmentBinding
import com.example.searchapp.repository.model.BlocksItem
import com.example.searchapp.ui.search.adapter.UnitsItemAdapter
import com.example.searchapp.utils.Constants

class ActivityListFragment : Fragment() {

    private lateinit var binding: ActivityListFragmentBinding
    private var unitsItemAdapter: UnitsItemAdapter? = null
    private var blocksItem: BlocksItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.activity_list_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readBundleData()
    }

    private fun readBundleData() {
        blocksItem =
            arguments?.getParcelable(Constants.GeneralConstants.ACTIVITY_LIST)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAdapter(blocksItem)
    }

    private fun bindAdapter(blocksItem: BlocksItem?) {

        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvActivities.layoutManager = linearLayoutManager

        blocksItem?.units.let {
            unitsItemAdapter = UnitsItemAdapter(it)
            binding.rvActivities.adapter = unitsItemAdapter
        }
    }

    companion object {

        fun newInstance(blocksItem: BlocksItem): ActivityListFragment {
            val bundle = Bundle()
            bundle.putParcelable(Constants.GeneralConstants.ACTIVITY_LIST, blocksItem)
            val activityListFragment =
                ActivityListFragment()
            activityListFragment.arguments = bundle
            return activityListFragment
        }
    }
}