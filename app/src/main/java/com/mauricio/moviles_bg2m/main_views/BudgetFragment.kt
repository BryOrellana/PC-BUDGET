package com.mauricio.moviles_bg2m.main_views

import android.graphics.Paint
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.mauricio.moviles_bg2m.ProductDescViewModel
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentBudgetBinding
import com.mauricio.moviles_bg2m.observable
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode

class BudgetFragment : Fragment() {
    lateinit var binding: FragmentBudgetBinding
    private lateinit var viewModel: observable


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
}


