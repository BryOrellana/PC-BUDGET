package com.mauricio.moviles_bg2m.main_views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mauricio.moviles_bg2m.databinding.FragmentHomeBinding
import com.mauricio.moviles_bg2m.main_views.HomeFragmentDirections
import com.mauricio.moviles_bg2m.R


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)

        cardListeners()
        return binding.root
    }

    private fun cardListeners() {
        binding.apply {
            presu1card.setOnClickListener { view: View ->
                //view.findNavController().navigate(R.id.action_homeFragment_to_savedBudgetFragment)
                view.findNavController()
                    .navigate(
                        HomeFragmentDirections.actionHomeFragmentToSavedBudgetFragment(
                            "COMPUTADORA GAMING 1"
                        )
                    )
            }
            presu2card.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(
                        HomeFragmentDirections.actionHomeFragmentToSavedBudgetFragment(
                            "COMPUTADORA GAMING 2"
                        )
                    )
            }
        }
    }
}
