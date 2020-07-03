package com.mauricio.moviles_bg2m.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mauricio.moviles_bg2m.ApiService
import com.mauricio.moviles_bg2m.ProductAdapter
import com.mauricio.moviles_bg2m.Products
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentCategoriesInsideBinding
import kotlinx.android.synthetic.main.fragment_categories_inside.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesTarjMadre : Fragment() {
    private lateinit var binding: FragmentCategoriesInsideBinding
    private val mMotherBoardsIds = listOf<String>(
        "-MB70PWv3aW8-3Cpgx1p",
        "-MB71H3dUyYBykr_yzDt",
        "-MB72IPG_-ZgD7FZFAEl",
        "-MB72jvfpRaK6b_5-D32",
        "-MB73AIoDi_UmPvZo_5G",
        "-MB73f_dNpUxBNgeKFX0",
        "-MB742mFgYov-QYizT3M",
        "-MB74Ocyc531lYJVEiNk",
        "-MB74n_VnZLSVkC_qUyt",
        "-MB759J4bgjhAy-G7lUf"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_categories_inside,
            container,
            false
        )

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pc-budget.firebaseio.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        var productsList : MutableList<Products> = ArrayList()

        mMotherBoardsIds.forEach() {
            service.getProducts(it).enqueue(object : Callback<Products> {
                override fun onResponse(
                    call: Call<Products>?, response: Response<Products>?
                ) {
                    val productsData = response?.body()
                    productsList.add(productsData!!)
                    showData(productsList)
                    //d("products", "GetAllProducts " + productsData)

                }

                override fun onFailure(call: Call<Products>, t: Throwable?) {
                    t?.printStackTrace()
                }

            })
        }

        //navToTarjMadreDesc()
        return binding.root
    }

    private fun showData(products: MutableList<Products>) {
        recycler_view_laptops.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = ProductAdapter(products)
        }
    }


    /*private fun navToTarjMadreDesc() {
        binding.apply {
            containerProduct1.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_categories_Tarj_Madre_to_descriptionTarjMadreFragment)
            }
            containerProduct2.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_categories_Tarj_Madre_to_descriptionTarjMadreFragment)
            }
            containerProduct3.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_categories_Tarj_Madre_to_descriptionTarjMadreFragment)
            }
            containerProduct4.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_categories_Tarj_Madre_to_descriptionTarjMadreFragment)
            }

        }
    }*/
}
