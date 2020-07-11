package com.mauricio.moviles_bg2m

data class UploadBudget (
    val bid : String?,
    val bname : String,
    val budgetDesc: String,
    val totalP : String?,
    val pid : String?,
    val pimage : String?,
    val pname : String?,
    val pprice : String?,
    val tgId : String?,
    val tgImage : String?,
    val tgName : String?,
    val tgPrice : String?,
    val rid : String?,
    val rimage : String?,
    val rname : String?,
    val rprice : String?,
    val tmId : String?,
    val tmImage : String?,
    val tmName : String?,
    val tmPrice : String?,
    val aid : String?,
    val aimage : String?,
    val aname : String?,
    val aprice : String?,
    val fpId : String?,
    val fpImage : String?,
    val fpName : String?,
    val fpPrice : String?,
    val cid : String?,
    val cimage : String?,
    val cname : String?,
    val cprice : String?
){
    constructor() : this("","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""){

    }
}