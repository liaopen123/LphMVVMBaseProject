package com.lph.lphmvvmbaseproject.viewmodel

import androidx.lifecycle.ViewModel

class SingleInstanceModel:ViewModel(){

    companion object{
        val Instance by lazy {
            SingleInstanceModel()
        }
    }


}