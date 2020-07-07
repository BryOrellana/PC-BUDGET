package com.mauricio.moviles_bg2m

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductDescViewModel : ViewModel() {

    val pdRank = MutableLiveData<String>()
    val pdName = MutableLiveData<String>()
    val pdPrice = MutableLiveData<String>()
    val pdImage = MutableLiveData<String>()
    val pdDesc = MutableLiveData<String>()

    fun setInfo(Image: String, name: String, price: String, desc: String, rank : String) {
        pdImage.value = Image
        pdDesc.value = desc
        pdPrice.value = price
        pdName.value = name
        pdRank.value = rank
    }
}