package com.mauricio.moviles_bg2m.main_views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    lateinit var binding: FragmentMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_more, container, false
        )

        btnListeners()
        return binding.root
    }

    private fun btnListeners() {
        binding.apply {
            btnSesion.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_moreFragment_to_loginFragment)
            }
            cambiarContra.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_moreFragment_to_changePasswordFragment2)
            }
            btnSubirProducto.setOnClickListener { view: View ->
                view.findNavController()
                    .navigate(R.id.action_moreFragment_to_fragment_subirproducto2)
            }
        }
    }
}


