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

class CategoriesTarjVideo : Fragment() {
    lateinit var binding: FragmentCategoriesInsideBinding
    private val mVideoCardsIds = listOf<String>(
        "-MBJ2WEZ88J10oSBLk2b",
        "-MBJ2enLaXiAxaCwlhtL",
        "-MBJ2ndbmQoIXabY6i8Z",
        "-MBJ2wmmS6TTY50ppI0L",
        "-MBJ370gWRow2K5Bknkr",
        "-MBJ3Etlss_nwld3YOIA",
        "-MBJ3NT0q6OIwKJ0Bh3F",
        "-MBJ3UiE3W8SENIHEbnz",
        "-MBJ3dd8CYBy_7LzjmJM",
        "-MBJ3nuwclWVnG1TYp_D",
        "-MBJ3wWMO7XeAjWnFa2y"
    )

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

        mVideoCardsIds.forEach() {
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

        //navToTarjVideoDesc()
        return binding.root
    }

    private fun showData(products: MutableList<Products>) {
        recycler_view_laptops.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductAdapter(products)
        }
    }

    /* private fun navToTarjVideoDesc(){
         binding.apply {
             containerProduct1.setOnClickListener { view : View ->
                 view.findNavController().navigate(R.id.action_categories_Tarj_video_to_descriptionTarjVideo)
             }
             containerProduct2.setOnClickListener { view : View ->
                 view.findNavController().navigate(R.id.action_categories_Tarj_video_to_descriptionTarjVideo)
             }
             containerProduct3.setOnClickListener { view : View ->
                 view.findNavController().navigate(R.id.action_categories_Tarj_video_to_descriptionTarjVideo)
             }
             containerProduct4.setOnClickListener { view : View ->
                 view.findNavController().navigate(R.id.action_categories_Tarj_video_to_descriptionTarjVideo)
             }

         }
     }*/
}
