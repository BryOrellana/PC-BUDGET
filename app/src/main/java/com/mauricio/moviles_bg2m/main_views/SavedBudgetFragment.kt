package com.mauricio.moviles_bg2m.main_views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentSavedBudgetBinding

/**
 * A simple [Fragment] subclass.
 */
class SavedBudgetFragment : Fragment() {
    lateinit var binding: FragmentSavedBudgetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_saved_budget, container, false)

        navBtnAdd()
        return binding.root
    }

    private fun navBtnAdd() {
        binding.apply {
            nameProcesador.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_savedBudgetFragment_to_descriptionProductFragment)
            }
            imgProcessorAdd.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_savedBudgetFragment_to_descriptionProductFragment)
            }

            nameRam.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_savedBudgetFragment_to_categories_Memorias)
            }
            btnAddRam.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_savedBudgetFragment_to_categories_Memorias)
            }

            txtAddStorageStatic.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_savedBudgetFragment_to_categories_almacenamiento)
            }
            btnAddStorage.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_savedBudgetFragment_to_categories_almacenamiento)
            }

            txtAddPowerStatic.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_savedBudgetFragment_to_categories_Fuentes_Poder)
            }
            btnAddPower.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_savedBudgetFragment_to_categories_Fuentes_Poder)
            }

            txtAddCaseStatic.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_savedBudgetFragment_to_categories_Gabinetes)
            }
            btnAddCase.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_savedBudgetFragment_to_categories_Gabinetes)
            }
        }
    }

}
