package com.mauricio.moviles_bg2m

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mauricio.moviles_bg2m.databinding.FragmentProductInfoBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class ProductInfo : Fragment() {
    private lateinit var binding: FragmentProductInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_info, container, false)

        setInfoFromBudget()

        return binding.root
    }

    private fun setInfoFromBudget() {
        var pBudgetInfoDesc: ProductDescViewModel
        pBudgetInfoDesc = activity?.run {
            ViewModelProviders.of(this).get(ProductDescViewModel::class.java)
        } ?: throw Exception("Invalid Fragment")

        binding.apply {
            Picasso.with(context).load(pBudgetInfoDesc.pdImage.value).into(imgProduct)
            nameProduct.text = pBudgetInfoDesc.pdName.value
            priceProduct.text = pBudgetInfoDesc.pdPrice.value
            rankProduct.text = pBudgetInfoDesc.pdRank.value
            descripcionProduct.text = pBudgetInfoDesc.pdDesc.value
        }
    }

}
