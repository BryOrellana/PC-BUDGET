package com.mauricio.moviles_bg2m.moreFragmentViews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.mauricio.moviles_bg2m.*
import com.mauricio.moviles_bg2m.databinding.FragmentProductDescriptionBinding
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

/**
 * A simple [Fragment] subclass.
 */
class DescriptionProductFragment() : Fragment() {
    private lateinit var binding: FragmentProductDescriptionBinding
    private lateinit var viewModel: observable
    private lateinit var SBViewModel: SavedBudgetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product_description, container, false
        )

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(observable::class.java)
        } ?: throw Exception("Invalid Fragment")

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pc-budget.firebaseio.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        var productsList: MutableList<Products> = ArrayList()
        val code = arguments?.getString("product")



        service.getProducts(code!!).enqueue(object : Callback<Products> {
            override fun onResponse(
                call: Call<Products>?, response: Response<Products>?
            ) {
                val productsData = response?.body()
                productsList.add(productsData!!)
                var url = productsList[0]!!.URL

                Picasso.with(context).load(productsList[0].imageUrl).into(binding.imgProduct)
                //viewModel.image.value = binding.imgProductoDesc


                binding.priceProduct.text = productsList[0].productPrice
                binding.nameProduct.text = productsList[0].nameProduct
                binding.rankProduct.text = productsList[0].productRank
                binding.descripcionProduct.text = productsList[0].productDesc

                if (productsList[0].productId.substring(0, 1) == "L") {
                    binding.btnAction.visibility = View.GONE
                    binding.amazon.visibility = View.VISIBLE
                    }
                //d("products", "GetAllProducts " + productsData)

                binding.btnAction.setOnClickListener { view: View ->
                    if (productsList[0].productId.substring(0, 1) == "P") {
                        viewModel.pPrice.value = productsList[0].productPrice
                        viewModel.pName.value = productsList[0].nameProduct
                        viewModel.pId.value = productsList[0].productId.substring(0, 1)
                        viewModel.pImage.value = productsList[0].imageUrl
                        viewModel.pRank.value = productsList[0].productRank
                        viewModel.pDesc.value = productsList[0].productDesc
                    }
                    if (productsList[0].productId.substring(0, 1) == "G") {
                        viewModel.tgPrice.value = productsList[0].productPrice
                        viewModel.tgName.value = productsList[0].nameProduct
                        viewModel.tgId.value = productsList[0].productId.substring(0, 1)
                        viewModel.tgImage.value = productsList[0].imageUrl
                        viewModel.tgRank.value = productsList[0].productRank
                        viewModel.tgDesc.value = productsList[0].productDesc
                    }
                    if (productsList[0].productId.substring(0, 1) == "R") {
                        viewModel.rPrice.value = productsList[0].productPrice
                        viewModel.rName.value = productsList[0].nameProduct
                        viewModel.rId.value = productsList[0].productId.substring(0, 1)
                        viewModel.rImage.value = productsList[0].imageUrl
                        viewModel.rRank.value = productsList[0].productRank
                        viewModel.rDesc.value = productsList[0].productDesc
                    }
                    if (productsList[0].productId.substring(0, 1) == "T") {
                        viewModel.tmPrice.value = productsList[0].productPrice
                        viewModel.tmName.value = productsList[0].nameProduct
                        viewModel.tmId.value = productsList[0].productId.substring(0, 1)
                        viewModel.tmImage.value = productsList[0].imageUrl
                        viewModel.tmRank.value = productsList[0].productRank
                        viewModel.tmDesc.value = productsList[0].productDesc
                    }
                    if (productsList[0].productId.substring(0, 1) == "A") {
                        viewModel.aPrice.value = productsList[0].productPrice
                        viewModel.aName.value = productsList[0].nameProduct
                        viewModel.aId.value = productsList[0].productId.substring(0, 1)
                        viewModel.aImage.value = productsList[0].imageUrl
                        viewModel.aRank.value = productsList[0].productRank
                        viewModel.aDesc.value = productsList[0].productDesc
                    }
                    if (productsList[0].productId.substring(0, 1) == "F") {
                        viewModel.fpPrice.value = productsList[0].productPrice
                        viewModel.fpName.value = productsList[0].nameProduct
                        viewModel.fpId.value = productsList[0].productId.substring(0, 1)
                        viewModel.fpImage.value = productsList[0].imageUrl
                        viewModel.fpRank.value = productsList[0].productRank
                        viewModel.fpDesc.value = productsList[0].productDesc
                    }
                    if (productsList[0].productId.substring(0, 1) == "C") {
                        viewModel.cPrice.value = productsList[0].productPrice
                        viewModel.cName.value = productsList[0].nameProduct
                        viewModel.cId.value = productsList[0].productId.substring(0, 1)
                        viewModel.cImage.value = productsList[0].imageUrl
                        viewModel.cRank.value = productsList[0].productRank
                        viewModel.cDesc.value = productsList[0].productDesc
                    }
                    Toast.makeText(
                        context,
                        "Producto añadido a tu presupuesto",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

            override fun onFailure(call: Call<Products>, t: Throwable?) {
                t?.printStackTrace()
            }
        })


        binding.amazon.setOnClickListener { view: View ->
            if (productsList[0].productId.substring(0, 1) == "L") {


                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(productsList[0].URL.toString()))
                startActivity(webIntent)
                Toast.makeText(
                    context,
                    "Abriendo...",
                    Toast.LENGTH_SHORT
                ).show()
            }


            }
        return binding.root
    }

}
