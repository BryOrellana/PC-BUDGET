package com.mauricio.moviles_bg2m.main_views

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.text.style.UpdateAppearance
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mauricio.moviles_bg2m.*
import com.mauricio.moviles_bg2m.databinding.FragmentBudgetBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.log

class BudgetFragment : Fragment() {
    lateinit var binding: FragmentBudgetBinding
    private lateinit var viewModel: observable
    private val user = FirebaseAuth.getInstance().currentUser
    private var freeBudgetCard: String = ""
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
        binding = DataBindingUtil.inflate<FragmentBudgetBinding>(
            inflater,
            R.layout.fragment_budget, container, false
        )

        settersInfo()
        navBtnAdd()
        navToProductDesc()
        btnSaveBudget()
        return binding.root
    }

    private fun settersInfo() {
        var pPrice = 0.0
        var tgPrice = 0.0
        var rPrice = 0.0
        var tmPrice = 0.0
        var aPrice = 0.0
        var fpPrice = 0.0
        var cPrice = 0.0

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(observable::class.java)
        } ?: throw Exception("Invalid Activity")
        viewModel.pName.observe(viewLifecycleOwner, Observer {

            if (viewModel.pId.value == "P") {
                binding.apply {
                    priceStatic.visibility = View.VISIBLE
                    priceProduct.visibility = View.VISIBLE
                    txtAddProcessorStatic.text = viewModel.pName.value
                    priceProduct.text = viewModel.pPrice.value
                    Picasso.with(context).load(viewModel.pImage.value).into(imgProcessorAdd)

                    pPrice = priceProduct.text.toString().toDouble()
                    calcTotal(pPrice, tgPrice, rPrice, tmPrice, aPrice, fpPrice, cPrice)
                }
            }

        })
        viewModel.tgName.observe(viewLifecycleOwner, Observer {
            if (viewModel.tgId.value == "G") {
                binding.priceStaticTg.visibility = View.VISIBLE
                binding.priceTxtTg.visibility = View.VISIBLE
                binding.txtAddGraphicStatic.text = viewModel.tgName.value
                binding.priceTxtTg.text = viewModel.tgPrice.value
                Picasso.with(context).load(viewModel.tgImage.value).into(binding.imgGraphicAdded)

                tgPrice = binding.priceTxtTg.text.toString().toDouble()
                calcTotal(pPrice, tgPrice, rPrice, tmPrice, aPrice, fpPrice, cPrice)
            }
        })
        viewModel.rName.observe(viewLifecycleOwner, Observer {
            if (viewModel.rId.value == "R") {
                binding.priceTxtRam.visibility = View.VISIBLE
                binding.priceStaticRam.visibility = View.VISIBLE
                binding.nameRamTxt.text = viewModel.rName.value
                binding.priceTxtRam.text = viewModel.rPrice.value
                Picasso.with(context).load(viewModel.rImage.value).into(binding.btnAddRam)

                rPrice = binding.priceTxtRam.text.toString().toDouble()
                calcTotal(pPrice, tgPrice, rPrice, tmPrice, aPrice, fpPrice, cPrice)
            }
        })
        viewModel.tmName.observe(viewLifecycleOwner, Observer {
            if (viewModel.tmId.value == "T") {
                binding.priceStaticTm.visibility = View.VISIBLE
                binding.priceTxtTm.visibility = View.VISIBLE
                binding.nameMotherboard.text = viewModel.tmName.value
                binding.priceTxtTm.text = viewModel.tmPrice.value
                Picasso.with(context).load(viewModel.tmImage.value)
                    .into(binding.imgMotherboardAdded)

                tmPrice = binding.priceTxtTm.text.toString().toDouble()
                calcTotal(pPrice, tgPrice, rPrice, tmPrice, aPrice, fpPrice, cPrice)
            }
        })
        viewModel.aName.observe(viewLifecycleOwner, Observer {
            if (viewModel.aId.value == "A") {
                binding.priceStaticStorage.visibility = View.VISIBLE
                binding.priceTxtStorage.visibility = View.VISIBLE
                binding.txtAddStorage.text = viewModel.aName.value
                binding.priceTxtStorage.text = viewModel.aPrice.value
                Picasso.with(context).load(viewModel.aImage.value)
                    .into(binding.btnAddStorage)

                aPrice = binding.priceTxtStorage.text.toString().toDouble()
                calcTotal(pPrice, tgPrice, rPrice, tmPrice, aPrice, fpPrice, cPrice)
            }
        })
        viewModel.fpName.observe(viewLifecycleOwner, Observer {
            if (viewModel.fpId.value == "F") {
                binding.priceStaticFp.visibility = View.VISIBLE
                binding.priceTxtFp.visibility = View.VISIBLE
                binding.txtAddPower.text = viewModel.fpName.value
                binding.priceTxtFp.text = viewModel.fpPrice.value
                Picasso.with(context).load(viewModel.fpImage.value)
                    .into(binding.btnAddPower)

                fpPrice = binding.priceTxtFp.text.toString().toDouble()
                calcTotal(pPrice, tgPrice, rPrice, tmPrice, aPrice, fpPrice, cPrice)
            }
        })
        viewModel.cName.observe(viewLifecycleOwner, Observer {
            if (viewModel.cId.value == "C") {
                binding.priceCaseStatic.visibility = View.VISIBLE
                binding.priceTxtCase.visibility = View.VISIBLE
                binding.nameCase.text = viewModel.cName.value
                binding.priceTxtCase.text = viewModel.cPrice.value
                Picasso.with(context).load(viewModel.cImage.value)
                    .into(binding.btnAddCase)


                cPrice = binding.priceTxtCase.text.toString().toDouble()
                calcTotal(pPrice, tgPrice, rPrice, tmPrice, aPrice, fpPrice, cPrice)
            }
        })
    }

    private fun calcTotal(
        priceP: Double, priceTG: Double, priceR: Double, priceTM: Double, priceA: Double,
        priceFP: Double, priceC: Double
    ) {

        val sumTotal = priceP + priceTG + priceR + priceTM + priceA + priceFP + priceC
        val roundTotal = BigDecimal(sumTotal).setScale(2, RoundingMode.HALF_EVEN)
        //d("SUMTOTAL", sumTotal.toString())

        viewModel.totalP.value = roundTotal.toDouble()

        viewModel.totalP.observe(viewLifecycleOwner, Observer {
            binding.txtTotal.text = viewModel.totalP.value.toString()
        })

    }

    private fun navBtnAdd() {
        binding.apply {


            imgProcessorAdd.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_budgetFragment_to_categories_Procesadores)
            }

            imgGraphicAdded.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_budgetFragment_to_categories_Tarj_video)
            }
            btnAddRam.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_budgetFragment_to_categories_Memorias)
            }
            imgMotherboardAdded.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_budgetFragment_to_categories_Tarj_Madre)
            }
            btnAddStorage.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_budgetFragment_to_categories_almacenamiento)
            }
            btnAddPower.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_budgetFragment_to_categories_Fuentes_Poder)
            }
            btnAddCase.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_budgetFragment_to_categories_Gabinetes)
            }
        }
    }

    private fun navToProductDesc() {
        var productToDescViewModel: ProductDescViewModel
        productToDescViewModel = activity?.run {
            ViewModelProviders.of(this).get(ProductDescViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        binding.apply {
            txtAddProcessorStatic.setOnClickListener { view: View ->
                if (txtAddProcessorStatic.text.toString() != "Presiona el boton para añadir Procesador") {
                    productToDescViewModel.pdName.value = viewModel.pName.value
                    productToDescViewModel.pdPrice.value = viewModel.pPrice.value
                    productToDescViewModel.pdImage.value = viewModel.pImage.value
                    productToDescViewModel.pdRank.value = viewModel.pRank.value
                    productToDescViewModel.pdDesc.value = viewModel.pDesc.value
                    view.findNavController().navigate(R.id.action_budgetFragment_to_productInfo)
                }
            }
            txtAddGraphicStatic.setOnClickListener { view: View ->
                if (txtAddGraphicStatic.text.toString() != "Presiona el boton para añadir tarjeta grafica") {
                    productToDescViewModel.pdName.value = viewModel.tgName.value
                    productToDescViewModel.pdPrice.value = viewModel.tgPrice.value
                    productToDescViewModel.pdImage.value = viewModel.tgImage.value
                    productToDescViewModel.pdRank.value = viewModel.tgRank.value
                    productToDescViewModel.pdDesc.value = viewModel.tgDesc.value
                    view.findNavController().navigate(R.id.action_budgetFragment_to_productInfo)
                }
            }
            nameRamTxt.setOnClickListener { view: View ->
                if (nameRamTxt.text.toString() != "Presiona el boton para añadir memoria ram") {
                    productToDescViewModel.pdName.value = viewModel.rName.value
                    productToDescViewModel.pdPrice.value = viewModel.rPrice.value
                    productToDescViewModel.pdImage.value = viewModel.rImage.value
                    productToDescViewModel.pdRank.value = viewModel.rRank.value
                    productToDescViewModel.pdDesc.value = viewModel.rDesc.value
                    view.findNavController().navigate(R.id.action_budgetFragment_to_productInfo)
                }
            }
            nameMotherboard.setOnClickListener { view: View ->
                if (nameMotherboard.text.toString() != "Presiona el boton para añadir tarjeta madre") {
                    productToDescViewModel.pdName.value = viewModel.tmName.value
                    productToDescViewModel.pdPrice.value = viewModel.tmPrice.value
                    productToDescViewModel.pdImage.value = viewModel.tmImage.value
                    productToDescViewModel.pdRank.value = viewModel.tmRank.value
                    productToDescViewModel.pdDesc.value = viewModel.tmDesc.value
                    view.findNavController().navigate(R.id.action_budgetFragment_to_productInfo)
                }
            }
            txtAddStorage.setOnClickListener { view: View ->
                if (txtAddStorage.text.toString() != "Presiona el boton para añadir almacenamiento") {
                    productToDescViewModel.pdName.value = viewModel.aName.value
                    productToDescViewModel.pdPrice.value = viewModel.aPrice.value
                    productToDescViewModel.pdImage.value = viewModel.aImage.value
                    productToDescViewModel.pdRank.value = viewModel.aRank.value
                    productToDescViewModel.pdDesc.value = viewModel.aDesc.value
                    view.findNavController().navigate(R.id.action_budgetFragment_to_productInfo)
                }
            }
            txtAddPower.setOnClickListener { view: View ->
                if (txtAddPower.text.toString() != "Presiona el boton para añadir fuente de poder") {
                    productToDescViewModel.pdName.value = viewModel.fpName.value
                    productToDescViewModel.pdPrice.value = viewModel.fpPrice.value
                    productToDescViewModel.pdImage.value = viewModel.fpImage.value
                    productToDescViewModel.pdRank.value = viewModel.fpRank.value
                    productToDescViewModel.pdDesc.value = viewModel.fpDesc.value
                    view.findNavController().navigate(R.id.action_budgetFragment_to_productInfo)
                }
            }
            nameCase.setOnClickListener { view: View ->
                if (nameCase.text.toString() != "Presiona el boton para añadir gabinete") {
                    productToDescViewModel.pdName.value = viewModel.cName.value
                    productToDescViewModel.pdPrice.value = viewModel.cPrice.value
                    productToDescViewModel.pdImage.value = viewModel.cImage.value
                    productToDescViewModel.pdRank.value = viewModel.cRank.value
                    productToDescViewModel.pdDesc.value = viewModel.cDesc.value
                    view.findNavController().navigate(R.id.action_budgetFragment_to_productInfo)
                }
            }
        }
    }

    private fun btnSaveBudget() {
        var options = arrayOf<CharSequence>()
        if (user != null) {
             options = arrayOf<CharSequence>(
                "Campo 1", "Campo 2", "Campo 3", "Campo 4", "Campo 5",
                "Campo 6", "Campo 7", "Campo 8", "Campo 9", "Campo 10"
            )
        } else {
             options = arrayOf<CharSequence>(
                "Campo 1", "Campo 2"
            )
        }


        binding.saveButton.setOnClickListener {
            var bDialog: AlertDialog
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)

            if(user == null){
                Toast.makeText(context,"Inicia sesión o crea una cuenta para poder usar todos los campos de presupuesto!",Toast.LENGTH_SHORT).show()
            }

            if (binding.etNameBudget.text.isEmpty() || binding.etDescBudget.text.isEmpty()) {
                Toast.makeText(
                    context,
                    "Por favor, ingresa un nombre y descripción para tu presupuesto",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                builder.setTitle("Elige una opcion para guardar o sobreescribir un presupuesto")
                builder.setSingleChoiceItems(
                    options, -1
                ) { dialog, item ->
                    when (item) {
                        0 -> {
                            freeBudgetCard = mBudgetsIds[0]
                            //d("URLAPI", freeBudgetCard)
                        }
                        1 -> {
                            freeBudgetCard = mBudgetsIds[1]
                            //d("URLAPI", freeBudgetCard)
                        }
                        2 -> {
                            freeBudgetCard = mBudgetsIds[2]
                            //d("URLAPI", freeBudgetCard)
                        }
                        3 -> {
                            freeBudgetCard = mBudgetsIds[3]
                            //d("URLAPI", freeBudgetCard)
                        }
                        4 -> {
                            freeBudgetCard = mBudgetsIds[4]
                            //d("URLAPI", freeBudgetCard)
                        }
                        5 -> {
                            freeBudgetCard = mBudgetsIds[5]
                            //d("URLAPI", freeBudgetCard)
                        }
                        6 -> {
                            freeBudgetCard = mBudgetsIds[6]
                            //d("URLAPI", freeBudgetCard)
                        }
                        7 -> {
                            freeBudgetCard = mBudgetsIds[7]
                            // d("URLAPI", freeBudgetCard)
                        }
                        8 -> {
                            freeBudgetCard = mBudgetsIds[8]
                            // d("URLAPI", freeBudgetCard)
                        }
                        9 -> {
                            freeBudgetCard = mBudgetsIds[9]
                            // d("URLAPI", freeBudgetCard)
                        }
                    }
                }
                builder.setPositiveButton("Listo") { dialog, which ->
                    val budget = UploadBudget(
                        freeBudgetCard.trim(),
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

                    val mDatabaseRef =
                        FirebaseDatabase.getInstance().getReference("budgets")
                    //val budgetId = mDatabaseRef.push().key

                    mDatabaseRef.child(freeBudgetCard).setValue(budget)
                        .addOnCompleteListener {
                            Toast.makeText(
                                context,
                                "Presupuesto guardado correctamente! :D.",
                                Toast.LENGTH_SHORT
                            ).show()


                            Handler().postDelayed({
                                view!!.findNavController()!!
                                    .navigate(R.id.action_budgetFragment_to_homeFragment)
                                activity?.viewModelStore?.clear()
                            }, 800)
                        }
                }
                bDialog = builder.create()
                bDialog.show()

                builder.setNegativeButton("Cancelar") { dialog, which ->
                    Toast.makeText(context, "Cancelado.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


