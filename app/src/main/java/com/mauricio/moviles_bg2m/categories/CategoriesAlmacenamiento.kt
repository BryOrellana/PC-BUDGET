package com.mauricio.moviles_bg2m.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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


class CategoriesAlmacenamiento : Fragment() {
    lateinit var binding: FragmentCategoriesInsideBinding
    private val mStoragesIds = listOf<String>(
        "-MBM9ZrgNCv08p_r0_Ly",
        "-MBMA2Sqx18qmQTn85Uz",
        "-MBMAVEkaVuxvRlC3vko",
        "-MBMB1M_q744LKJrTojy",
        "-MBMBGLNzUA3RpadNR79",
        "-MBMBUdHmW4ucUx-tuR9",
        "-MBMBnwyzzmqzsw6UuRD",
        "-MBMCABQi5-LBs8Yo05c",
        "-MBMCKN7FjRYSje5FhQZ",
        "-MBMCYTzaWhCPpvg4PMi"
    )
    val fReference = "storage"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_categories_inside, container, false
        )

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pc-budget.firebaseio.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        var productsList: MutableList<Products> = ArrayList()

        mStoragesIds.forEach() {
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

        return binding.root
    }

    private fun showData(products: MutableList<Products>) {
        recycler_view_laptops.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductAdapter(products, fReference)
        }
    }

}
