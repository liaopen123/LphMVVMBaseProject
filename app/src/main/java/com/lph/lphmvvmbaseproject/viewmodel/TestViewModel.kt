package com.lph.lphmvvmbaseproject.viewmodel

import androidx.lifecycle.MutableLiveData
import com.lph.baselib.viewmodel.BaseViewModel

class TestViewModel :BaseViewModel(){
    val title:MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }

    fun additle(name:String){
        title.postValue(name)
    }


}