package com.mauricio.moviles_bg2m.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class CategoriesMemorias : Fragment() {
    private lateinit var binding: FragmentCategoriesInsideBinding
    private val mMemoriesIds = listOf<String>(
        "-MBJ0PxC0_isNUI__n4O",
        "-MBJ0PxC0_isNUI__n4O",
        "-MBJ0cXxdCCMhyAF1LCh",
        "-MBJ0z2aK2lyH73M0qyE",
        "-MBJ19CATUPOqFqJIfGx",
        "-MBJ1NlKnwPJJE2AQwbk",
        "-MBJ1WYgz3u_DgwFbN5j",
        "-MBJ1sIN4D0RxrKSZdGG",
        "-MBJ2-rNNI-78sl8dn5a",
        "-MBJ2BEUjcuMTsghl-in"
    )
    val fReference = "memories"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pc-budget.firebaseio.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        var productsList: MutableList<Products> = ArrayList()

        mMemoriesIds.forEach() {
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

        return inflater.inflate(R.layout.fragment_categories_inside, container, false)
    }

    private fun showData(products: MutableList<Products>) {
        recycler_view_laptops.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductAdapter(products,fReference)
        }
    }

}
