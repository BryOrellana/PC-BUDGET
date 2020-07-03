package com.mauricio.moviles_bg2m

class Upload {
    private lateinit var mIdProduct: String
    private lateinit var mNameProduct: String
    private lateinit var mImageUrl: String
    private lateinit var mProductDesc: String
    private lateinit var mProductPrice: String
    private lateinit var mProductRank: String

    fun Upload() {
        //empty constructor needed
    }

    fun Upload(
        id: String,
        name: String,
        imageUrl: String,
        productDesc: String,
        productPrice: String,
        productRank: String
    ) {
        mIdProduct = id
        mNameProduct = name
        mImageUrl = imageUrl
        mProductDesc = productDesc
        mProductPrice = productPrice
        mProductRank = productRank

    }

    //GETTERS
    fun getProductId(): String {
        return mIdProduct
    }

    fun getNameProduct(): String {
        return mNameProduct
    }

    fun getImageUrl(): String {
        return mImageUrl
    }

    fun getProductDesc(): String {
        return mProductDesc
    }

    fun getProductPrice(): String {
        return mProductPrice
    }

    fun getProductRank(): String {
        return mProductRank
    }


    //SETTERS
    fun setProducId(id: String) {
        mIdProduct = id
    }

    fun setProductName(name: String) {
        mNameProduct = name
    }

    fun setImgUrl(imageUrl: String) {
        mNameProduct = imageUrl
    }

    fun setProductDesc(productDesc: String) {
        mNameProduct = productDesc
    }

    fun setProductPrice(productPrice: String) {
        mNameProduct = productPrice
    }

    fun setProductRank(productRank: String) {
        mNameProduct = productRank
    }

}