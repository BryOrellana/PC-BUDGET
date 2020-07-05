package com.mauricio.moviles_bg2m.main_views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentMoreBinding
import kotlinx.android.synthetic.main.fragment_more.*

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

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            binding.btnSesion.visibility = View.INVISIBLE
            binding.btnCerrar.visibility = View.VISIBLE
        } else {
            binding.btnSesion.visibility = View.VISIBLE
            binding.btnCerrar.visibility = View.INVISIBLE
        }
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
            btnCerrar.setOnClickListener { view : View ->
                FirebaseAuth.getInstance().signOut()
                view.findNavController().navigate(R.id.moreFragment)
                Toast.makeText(context, "Sesión cerrada correctamente",Toast.LENGTH_SHORT).show()
            }
        }
    }
}


