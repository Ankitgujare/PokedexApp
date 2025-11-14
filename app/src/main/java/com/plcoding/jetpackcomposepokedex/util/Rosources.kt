package com.plcoding.jetpackcomposepokedex.util

import android.content.res.Resources

sealed class Resource<T>(val data:T?=null,val message:String?=null){
    //In Success Case it Cannot be Null which it will In Other cases
    class Success<T>(data:T) : Resource<T>(data)
    //Show an Error
    class Error<T>(message: String,data:T?=null) : Resource<T>(data,message)
//    //Show the ProgressBar
//    class Loading<T>(data:T?=null):Resource<T>(data)

    class Loading<T>: Resource<T>()



}