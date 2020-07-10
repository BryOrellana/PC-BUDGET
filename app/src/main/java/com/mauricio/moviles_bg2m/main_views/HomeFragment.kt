package com.mauricio.moviles_bg2m.main_views

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mauricio.moviles_bg2m.*
import com.mauricio.moviles_bg2m.databinding.FragmentHomeBinding
import com.mauricio.moviles_bg2m.main_views.HomeFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_categories_inside.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val mBudgetsIds = listOf<String>(
        "-MBrzw4tCpaV6BLD3tPd",
        "-MBs-09OJXf89UFgjuXY",
        "-MBryh6a5kYtNXmLMH5_",
        "-MBryk6DNnAj9O60L6iX",
        "-MBs-7_X-hmQt8AXTMzA",
        "-MBs-9MMDmCBfnLOPOZd",
        "-MBs-BHLIn7Po3PrGwYO",
        "-MBs-CvZ8pqBpyVPuKNi",
        "-MBs-EybhRyJUCij6tyw",
        "-MBs-H8k_ajSCOd4erG1"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pc-budget.firebaseio.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        var BudgetList : MutableList<UploadBudget> = ArrayList()

        mBudgetsIds.forEach() {code : String ->
            service.getBudgets(code).enqueue(object : Callback<UploadBudget> {
                override fun onResponse(
                    call: Call<UploadBudget>?, response: Response<UploadBudget>?
                ) {
                    val BudgetData = response?.body()
                    BudgetList.add(BudgetData!!)
                    showData(BudgetList)
                    //d("BUDGETS", "" + BudgetData)

                }

                override fun onFailure(call: Call<UploadBudget>, t: Throwable?) {
                    t?.printStackTrace()
                }
            })
        }

        return binding.root
    }

    private fun showData(Budgets: MutableList<UploadBudget>) {
        recycler_view_budgets.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = BudgetAdapter(Budgets)
        }
    }
}
