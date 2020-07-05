package com.mauricio.moviles_bg2m.moreFragmentViews

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.mauricio.moviles_bg2m.ApiService
import com.mauricio.moviles_bg2m.Products
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentProductDescriptionBinding
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class DescriptionProductFragment() : Fragment() {
    private lateinit var binding: FragmentProductDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product_description, container, false
        )

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pc-budget.firebaseio.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        var productsList : MutableList<Products> = ArrayList()
        val code = arguments?.getString("product")

        service.getProducts(code!!).enqueue(object : Callback<Products> {
            override fun onResponse(
                call: Call<Products>?, response: Response<Products>?
            ) {
                val productsData = response?.body()
                productsList.add(productsData!!)

                Picasso.with(context).load(productsList[0].imageUrl).into(binding.imgProductoDesc)
                binding.priceTxt.text = productsList[0].productPrice
                binding.nameTxt.text = productsList[0].nameProduct
                binding.notaTxt.text = productsList[0].productRank
                binding.descripcionTxt.text = productsList[0].productDesc

                d("products", "GetAllProducts " + productsData)

            }

            override fun onFailure(call: Call<Products>, t: Throwable?) {
                t?.printStackTrace()
            }
        })

        return binding.root
    }

}
