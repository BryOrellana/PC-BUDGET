package com.mauricio.moviles_bg2m

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedBudgetViewModel : ViewModel() {

    val pId = MutableLiveData<String>()
    val pName = MutableLiveData<String>()
    val pPrice = MutableLiveData<String>()
    val pImage = MutableLiveData<String>()
    val pRank = MutableLiveData<String>()
    val pDesc = MutableLiveData<String>()

    val totalP = MutableLiveData<Double>()

}