package com.mauricio.moviles_bg2m.main_views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentBudgetBinding

class BudgetFragment : Fragment() {
        lateinit var binding: FragmentBudgetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentBudgetBinding>(inflater,
            R.layout.fragment_budget,container,false)
        navBtnAdd()
        return binding.root
    }

    private fun navBtnAdd(){
        binding.apply {
            txtAddProcessor.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_budgetFragment_to_categories_Procesadores)
            }
            txtAddGraphic.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_budgetFragment_to_categories_Tarj_video)
            }
            nameRam.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_budgetFragment_to_categories_Memorias)
            }
            nameMotherboard.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_budgetFragment_to_categories_Tarj_Madre)
            }
            txtAddStorage.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_budgetFragment_to_categories_almacenamiento)
            }
            txtAddPower.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_budgetFragment_to_categories_Fuentes_Poder)
            }
            txtAddCase.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_budgetFragment_to_categories_Gabinetes)
            }
        }
    }
}
