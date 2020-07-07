package com.mauricio.moviles_bg2m

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class observable : ViewModel() {
    val pId = MutableLiveData<String>()
    val pName = MutableLiveData<String>()
    val pPrice = MutableLiveData<String>()
    val pImage = MutableLiveData<String>()
    val pRank = MutableLiveData<String>()
    val pDesc = MutableLiveData<String>()

    val tgId = MutableLiveData<String>()
    val tgName = MutableLiveData<String>()
    val tgPrice = MutableLiveData<String>()
    val tgImage = MutableLiveData<String>()
    val tgRank = MutableLiveData<String>()
    val tgDesc = MutableLiveData<String>()

    val rId = MutableLiveData<String>()
    val rName = MutableLiveData<String>()
    val rPrice = MutableLiveData<String>()
    val rImage = MutableLiveData<String>()
    val rRank = MutableLiveData<String>()
    val rDesc = MutableLiveData<String>()

    val tmId = MutableLiveData<String>()
    val tmName = MutableLiveData<String>()
    val tmPrice = MutableLiveData<String>()
    val tmImage = MutableLiveData<String>()
    val tmRank = MutableLiveData<String>()
    val tmDesc = MutableLiveData<String>()

    val aId = MutableLiveData<String>()
    val aName =MutableLiveData<String>()
    val aPrice = MutableLiveData<String>()
    val aImage = MutableLiveData<String>()
    val aRank = MutableLiveData<String>()
    val aDesc = MutableLiveData<String>()

    val fpId = MutableLiveData<String>()
    val fpName = MutableLiveData<String>()
    val fpPrice = MutableLiveData<String>()
    val fpImage = MutableLiveData<String>()
    val fpRank = MutableLiveData<String>()
    val fpDesc = MutableLiveData<String>()

    val cId = MutableLiveData<String>()
    val cName = MutableLiveData<String>()
    val cPrice = MutableLiveData<String>()
    val cImage = MutableLiveData<String>()
    val cRank = MutableLiveData<String>()
    val cDesc = MutableLiveData<String>()

    val totalP = MutableLiveData<Double>()

}