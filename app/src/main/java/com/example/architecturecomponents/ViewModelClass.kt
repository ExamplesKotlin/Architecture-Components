package com.example.architecturecomponents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelClass: ViewModel() {

    var contador: Int = 0 // property ViewModel
    private var mCurrentName: MutableLiveData<String> = MutableLiveData() // @Published

    fun getCurrentName(): MutableLiveData<String> {
        return mCurrentName
    }
}