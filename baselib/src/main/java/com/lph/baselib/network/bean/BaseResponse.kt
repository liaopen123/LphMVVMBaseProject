package com.lph.baselib.network.bean

abstract class BaseResponse<T> {
    abstract fun  isSuccess():Boolean
    abstract  fun getResponseData():T
    abstract  fun getResponseCode():String
    abstract  fun getResponseMessage():String
}