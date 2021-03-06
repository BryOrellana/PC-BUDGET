package com.mauricio.moviles_bg2m.main_views

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.FirebaseDatabase
import com.mauricio.moviles_bg2m.*
import com.mauricio.moviles_bg2m.databinding.FragmentProductInfoBinding
import com.mauricio.moviles_bg2m.databinding.FragmentSavedBudgetBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal
import java.math.RoundingMode

class SavedBudgetFragment : Fragment() {
    lateinit var binding: FragmentSavedBudgetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_saved_budget, container, false
            )

        arguments?.let {
            val safeArgs = SavedBudgetFragmentArgs.fromBundle(it)
            val bid = safeArgs.bid

            val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pc-budget.firebaseio.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ApiService::class.java)
            var BudgetList: MutableList<UploadBudget> = ArrayList()

            service.getBudgets(bid).enqueue(object : Callback<UploadBudget> {
                override fun onResponse(
                    call: Call<UploadBudget>?, response: Response<UploadBudget>?
                ) {
                    var productToDescViewModel: ProductDescViewModel
                    productToDescViewModel = activity?.run {
                        ViewModelProviders.of(this).get(ProductDescViewModel::class.java)
                    } ?: throw Exception("Invalid Fragment")

                    val BudgetData = response?.body()
                    BudgetList.add(BudgetData!!)
                    d("BUDGETS", "" + BudgetData)

                    binding.apply {
                        txtAddProcessorStatic.setOnClickListener { view: View ->
                            if (BudgetData.pid == "P") {
                                productToDescViewModel.pdName.value = BudgetData.pname
                                productToDescViewModel.pdPrice.value = BudgetData.pprice
                                productToDescViewModel.pdImage.value = BudgetData.pimage
                                productToDescViewModel.pdRank.value = BudgetData.prank
                                productToDescViewModel.pdDesc.value = BudgetData.pdesc
                                view.findNavController().navigate(R.id.action_savedBudgetFragment_to_productInfo)
                            }
                        }
                        txtAddGraphicStatic.setOnClickListener { view: View ->
                            if (BudgetData.tgId == "G") {
                                productToDescViewModel.pdName.value = BudgetData.tgName
                                productToDescViewModel.pdPrice.value = BudgetData.tgPrice
                                productToDescViewModel.pdImage.value = BudgetData.tgImage
                                productToDescViewModel.pdRank.value = BudgetData.tgrank
                                productToDescViewModel.pdDesc.value = BudgetData.tgdesc
                                view.findNavController().navigate(R.id.action_savedBudgetFragment_to_productInfo)
                            }
                        }
                        nameRamTxt.setOnClickListener { view: View ->
                            if (BudgetData.rid == "R") {
                                productToDescViewModel.pdName.value = BudgetData.rname
                                productToDescViewModel.pdPrice.value = BudgetData.rprice
                                productToDescViewModel.pdImage.value = BudgetData.rimage
                                productToDescViewModel.pdRank.value = BudgetData.rrank
                                productToDescViewModel.pdDesc.value = BudgetData.rdesc
                                view.findNavController().navigate(R.id.action_savedBudgetFragment_to_productInfo)
                            }
                        }
                        nameMotherboard.setOnClickListener { view: View ->
                            if (BudgetData.tmId == "T") {
                                productToDescViewModel.pdName.value = BudgetData.tmName
                                productToDescViewModel.pdPrice.value = BudgetData.tmPrice
                                productToDescViewModel.pdImage.value = BudgetData.tmImage
                                productToDescViewModel.pdRank.value = BudgetData.tmrank
                                productToDescViewModel.pdDesc.value = BudgetData.tmdesc
                                view.findNavController().navigate(R.id.action_savedBudgetFragment_to_productInfo)
                            }
                        }
                        txtAddStorage.setOnClickListener { view: View ->
                            if (BudgetData.aid == "A") {
                                productToDescViewModel.pdName.value = BudgetData.aname
                                productToDescViewModel.pdPrice.value = BudgetData.aprice
                                productToDescViewModel.pdImage.value = BudgetData.aimage
                                productToDescViewModel.pdRank.value = BudgetData.arank
                                productToDescViewModel.pdDesc.value = BudgetData.adesc
                                view.findNavController().navigate(R.id.action_savedBudgetFragment_to_productInfo)
                            }
                        }
                        txtAddPower.setOnClickListener { view: View ->
                            if (BudgetData.fpId == "F") {
                                productToDescViewModel.pdName.value = BudgetData.fpName
                                productToDescViewModel.pdPrice.value = BudgetData.fpPrice
                                productToDescViewModel.pdImage.value = BudgetData.fpImage
                                productToDescViewModel.pdRank.value = BudgetData.fprank
                                productToDescViewModel.pdDesc.value = BudgetData.fpdesc
                                view.findNavController().navigate(R.id.action_savedBudgetFragment_to_productInfo)
                            }
                        }
                        nameCase.setOnClickListener { view: View ->
                            if (BudgetData.cid == "C") {
                                productToDescViewModel.pdName.value = BudgetData.cname
                                productToDescViewModel.pdPrice.value = BudgetData.cprice
                                productToDescViewModel.pdImage.value = BudgetData.cimage
                                productToDescViewModel.pdRank.value = BudgetData.crank
                                productToDescViewModel.pdDesc.value = BudgetData.cdesc
                                view.findNavController().navigate(R.id.action_savedBudgetFragment_to_productInfo)
                            }
                        }
                    }

                    binding.etNameBudget.setText(BudgetData.bname)
                    binding.etDescBudget.setText(BudgetData.budgetDesc)


                    if (BudgetData.pimage == "") {
                        Picasso.with(context).load(R.drawable.ic_add_circle)
                            .placeholder(R.drawable.ic_add_circle).into(binding.imgProcessorAdd)
                    } else {
                        Picasso.with(context).load(BudgetData.pimage).into(binding.imgProcessorAdd)
                    }
                    binding.priceStatic.visibility = View.VISIBLE
                    binding.priceProduct.visibility = View.VISIBLE

                    if (BudgetData.pname == "") {

                    } else {
                        binding.txtAddProcessorStatic.text = BudgetData.pname
                        binding.priceProduct.text = BudgetData.pprice
                    }

                    if (BudgetData.tgImage == "") {
                        Picasso.with(context).load(R.drawable.ic_add_circle)
                            .placeholder(R.drawable.ic_add_circle).into(binding.imgGraphicAdded)
                    } else {
                        Picasso.with(context).load(BudgetData.tgImage).into(binding.imgGraphicAdded)
                    }
                    binding.priceStaticTg.visibility = View.VISIBLE
                    binding.priceTxtTg.visibility = View.VISIBLE

                    if (BudgetData.tgName == "") {

                    } else {
                        binding.txtAddGraphicStatic.text = BudgetData.tgName
                        binding.priceTxtTg.text = BudgetData.tgPrice
                    }


                    if (BudgetData.rimage == "") {
                        Picasso.with(context).load(R.drawable.ic_add_circle)
                            .placeholder(R.drawable.ic_add_circle).into(binding.btnAddRam)
                    } else {
                        Picasso.with(context).load(BudgetData.rimage).into(binding.btnAddRam)
                    }

                    binding.priceStaticRam.visibility = View.VISIBLE
                    binding.priceTxtRam.visibility = View.VISIBLE

                    if (BudgetData.rname == "") {

                    } else {
                        binding.nameRamTxt.text = BudgetData.rname
                        binding.priceTxtRam.text = BudgetData.rprice
                    }

                    if (BudgetData.tmImage == "") {
                        Picasso.with(context).load(R.drawable.ic_add_circle)
                            .placeholder(R.drawable.ic_add_circle).into(binding.imgMotherboardAdded)
                    } else {
                        Picasso.with(context).load(BudgetData.tmImage)
                            .into(binding.imgMotherboardAdded)
                    }
                    binding.priceStaticTm.visibility = View.VISIBLE
                    binding.priceTxtTm.visibility = View.VISIBLE
                    if (BudgetData.tmName == "") {

                    } else {
                        binding.nameMotherboard.text = BudgetData.tmName
                        binding.priceTxtTm.text = BudgetData.tmPrice
                    }


                    if (BudgetData.aimage == "") {
                        Picasso.with(context).load(R.drawable.ic_add_circle)
                            .placeholder(R.drawable.ic_add_circle).into(binding.btnAddStorage)
                    } else {
                        Picasso.with(context).load(BudgetData.aimage).into(binding.btnAddStorage)
                    }
                    binding.priceStaticStorage.visibility = View.VISIBLE
                    binding.priceTxtStorage.visibility = View.VISIBLE

                    if (BudgetData.aname == "") {

                    } else {
                        binding.txtAddStorage.text = BudgetData.aname
                        binding.priceTxtStorage.text = BudgetData.aprice
                    }

                    if (BudgetData.fpImage == "") {
                        Picasso.with(context).load(R.drawable.ic_add_circle)
                            .placeholder(R.drawable.ic_add_circle).into(binding.btnAddPower)
                    } else {
                        Picasso.with(context).load(BudgetData.fpImage).into(binding.btnAddPower)
                    }
                    binding.priceStaticFp.visibility = View.VISIBLE
                    binding.priceTxtFp.visibility = View.VISIBLE
                    if (BudgetData.fpName == "") {

                    } else {
                        binding.txtAddPower.text = BudgetData.fpName
                        binding.priceTxtFp.text = BudgetData.fpPrice
                    }


                    if (BudgetData.cimage == "") {
                        Picasso.with(context).load(R.drawable.ic_add_circle)
                            .placeholder(R.drawable.ic_add_circle).into(binding.btnAddCase)
                    } else {
                        Picasso.with(context).load(BudgetData.cimage).into(binding.btnAddCase)
                    }
                    binding.priceCaseStatic.visibility = View.VISIBLE
                    binding.priceTxtCase.visibility = View.VISIBLE
                    if (BudgetData.cimage == "") {

                    } else {
                        binding.nameCase.text = BudgetData.cname
                        binding.priceTxtCase.text = BudgetData.cprice
                    }

                    if (BudgetData.totalP == "null") {
                        binding.txtTotal.text = "0.0"
                    } else {
                        binding.txtTotal.text = BudgetData.totalP
                    }
                }

                override fun onFailure(call: Call<UploadBudget>, t: Throwable?) {
                    t?.printStackTrace()
                }
            })

        }

        btnAddCircleAlert()
        btnSaveBudget()
        btnDeleteBudget()
        return binding.root
    }

    private fun btnAddCircleAlert() {
        arguments?.let {
            val safeArgs = SavedBudgetFragmentArgs.fromBundle(it)
            val bid = safeArgs.bid

            binding.txtAddProcessorStatic.setOnClickListener {view : View ->
                Navigation.findNavController(view).navigate(SavedBudgetFragmentDirections.actionSavedBudgetFragmentToProductInfo(bid))
            }

            binding.txtAddGraphicStatic.setOnClickListener {view : View ->
                Navigation.findNavController(view).navigate(SavedBudgetFragmentDirections.actionSavedBudgetFragmentToProductInfo(bid))
            }
            binding.nameRamTxt.setOnClickListener {view : View ->
                Navigation.findNavController(view).navigate(SavedBudgetFragmentDirections.actionSavedBudgetFragmentToProductInfo(bid))
            }
            binding.nameMotherboard.setOnClickListener {view : View ->
                Navigation.findNavController(view).navigate(SavedBudgetFragmentDirections.actionSavedBudgetFragmentToProductInfo(bid))
            }

            binding.txtAddStorage.setOnClickListener {view : View ->
                Navigation.findNavController(view).navigate(SavedBudgetFragmentDirections.actionSavedBudgetFragmentToProductInfo(bid))
            }
            binding.txtAddPower.setOnClickListener {view : View ->
                Navigation.findNavController(view).navigate(SavedBudgetFragmentDirections.actionSavedBudgetFragmentToProductInfo(bid))
            }

            binding.nameCase.setOnClickListener {view : View ->
                Navigation.findNavController(view).navigate(SavedBudgetFragmentDirections.actionSavedBudgetFragmentToProductInfo(bid))
            }
        }
    }

    private fun btnDeleteBudget() {
        binding.deleteButton.setOnClickListener { view: View ->

            arguments?.let {
                val safeArgs = SavedBudgetFragmentArgs.fromBundle(it)
                val bid = safeArgs.bid

                val budget = UploadBudget(
                    bid.trim(),
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                )

                val mDatabaseRef = FirebaseDatabase.getInstance().getReference("budgets")
                //val budgetId = mDatabaseRef.push().key

                mDatabaseRef.child(bid).setValue(budget).addOnCompleteListener {
                    Toast.makeText(
                        context,
                        "Presupuesto eliminado correctamente! :D.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            Handler().postDelayed({
                view!!.findNavController()!!
                    .navigate(R.id.action_savedBudgetFragment_to_homeFragment)
            }, 1000)
        }
    }

    private fun btnSaveBudget() {

        binding.saveButton.setOnClickListener {
            if (binding.etNameBudget.text.isEmpty() || binding.etDescBudget.text.isEmpty()) {
                Toast.makeText(
                    context,
                    "Por favor, ingresa un nombre y descripción para tu presupuesto",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                arguments?.let {
                    val safeArgs = SavedBudgetFragmentArgs.fromBundle(it)
                    val bid = safeArgs.bid


                    var viewModel = activity?.run {
                        ViewModelProviders.of(this).get(observable::class.java)
                    } ?: throw Exception("Invalid Activity")

                    val budget = UploadBudget(
                        bid.trim(),
                        binding.etNameBudget.text.toString().trim(),
                        binding.etDescBudget.text.toString().trim(),
                        viewModel.totalP.value.toString()?.trim(),

                        viewModel.pId.value?.trim() ?: "",
                        viewModel.pImage.value?.trim() ?: "",
                        viewModel.pName.value?.trim() ?: "",
                        viewModel.pDesc.value?.trim() ?: "",
                        viewModel.pRank.value?.trim() ?: "",
                        viewModel.pPrice.value?.trim() ?: "",

                        viewModel.tgId.value?.trim() ?: "",
                        viewModel.tgImage.value?.trim() ?: "",
                        viewModel.tgName.value?.trim() ?: "",
                        viewModel.tgDesc.value?.trim() ?: "",
                        viewModel.tgRank.value?.trim() ?: "",
                        viewModel.tgPrice.value?.trim() ?: "",

                        viewModel.rId.value?.trim() ?: "",
                        viewModel.rImage.value?.trim() ?: "",
                        viewModel.rName.value?.trim() ?: "",
                        viewModel.rDesc.value?.trim() ?: "",
                        viewModel.rRank.value?.trim() ?: "",
                        viewModel.rPrice.value?.trim() ?: "",

                        viewModel.tmId.value?.trim() ?: "",
                        viewModel.tmImage.value?.trim() ?: "",
                        viewModel.tmName.value?.trim() ?: "",
                        viewModel.tmDesc.value?.trim() ?: "",
                        viewModel.tmRank.value?.trim() ?: "",
                        viewModel.tmPrice.value?.trim() ?: "",

                        viewModel.aId.value?.trim() ?: "",
                        viewModel.aImage.value?.trim() ?: "",
                        viewModel.aName.value?.trim() ?: "",
                        viewModel.aDesc.value?.trim() ?: "",
                        viewModel.aRank.value?.trim() ?: "",
                        viewModel.aPrice.value?.trim() ?: "",

                        viewModel.fpId.value?.trim() ?: "",
                        viewModel.fpImage.value?.trim() ?: "",
                        viewModel.fpName.value?.trim() ?: "",
                        viewModel.fpDesc.value?.trim() ?: "",
                        viewModel.fpRank.value?.trim() ?: "",
                        viewModel.fpPrice.value?.trim() ?: "",

                        viewModel.cId.value?.trim() ?: "",
                        viewModel.cImage.value?.trim() ?: "",
                        viewModel.cName.value?.trim() ?: "",
                        viewModel.cDesc.value?.trim() ?: "",
                        viewModel.cRank.value?.trim() ?: "",
                        viewModel.cPrice.value?.trim() ?: ""
                    )

                    val mDatabaseRef = FirebaseDatabase.getInstance().getReference("budgets")
                    //val budgetId = mDatabaseRef.push().key

                    mDatabaseRef.child(bid).setValue(budget).addOnCompleteListener {
                        Toast.makeText(
                            context,
                            "Presupuesto guardado correctamente! :D.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            Handler().postDelayed({
                view!!.findNavController()!!
                    .navigate(R.id.action_savedBudgetFragment_to_homeFragment)
            }, 1000)
        }
    }
}
