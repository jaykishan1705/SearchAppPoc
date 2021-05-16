package com.example.searchapp.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchapp.repository.model.BlocksItem
import com.example.searchapp.utils.Constants
import com.example.searchapp.utils.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {
    private val _allBlocksList = MutableLiveData<List<BlocksItem>>()
    private val _blocksList = MutableLiveData<List<BlocksItem>>()
    val blocksList: LiveData<List<BlocksItem>> = _blocksList
    private val _searchText = MutableLiveData<String>()
    private val _noTermFoundText = MutableLiveData<String>()
    val noTermFoundText: LiveData<String> = _noTermFoundText
    private val _blocksSize: MutableLiveData<Int> = MutableLiveData()
    val blocksSize: LiveData<Int> = _blocksSize

    init {
        getAllBlocksList()
    }

    /**
     * this method will get all blocks list stored in app
     */
    private fun getAllBlocksList() {
        viewModelScope.launch {
            val jsonString =
                Utils.getJsonDataFromAsset(context, Constants.GeneralConstants.DATA_FILE)
            val listPersonType = object : TypeToken<List<BlocksItem>>() {}.type
            val blocksList: List<BlocksItem> = Gson().fromJson(jsonString, listPersonType)
            _allBlocksList.postValue(blocksList)
        }
    }

    /**
     * this method will filter items that user has searched for
     */
    fun setQueryText(queryText: String) {
        _searchText.value = queryText

        val filterList = ArrayList<BlocksItem>()
        for (block in _allBlocksList.value!!) {
            val unitsData = block.units?.filter { unit ->
                (unit?.name?.contains(queryText, true) ?: false) or
                        (unit?.activities?.filter { act ->
                            (act?.name?.contains(queryText, true) ?: false) or
                                    (act?.stepName?.contains(queryText, true) ?: false)
                        }?.size!! > 0)
            }
            if (unitsData?.size!! > 0) {
                filterList.add(BlocksItem(block.name, unitsData))
            }
        }

        _blocksList.postValue(filterList)
        _blocksSize.postValue(filterList.size)
        setNoFoundText(queryText, filterList.size)
    }

    /**
     * this method sets value when no items found
     */
    private fun setNoFoundText(queryText: String, size: Int) {
        if (size == 0) {
            _noTermFoundText.postValue(queryText)
        }
    }
}