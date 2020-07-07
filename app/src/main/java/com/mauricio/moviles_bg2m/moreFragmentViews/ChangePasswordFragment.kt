package com.mauricio.moviles_bg2m.moreFragmentViews

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentChangepasswordBinding
import kotlinx.android.synthetic.main.fragment_signup.*
import java.net.PasswordAuthentication

/**
 * A simple [Fragment] subclass.
 */
class ChangePasswordFragment : Fragment() {
    lateinit var binding: FragmentChangepasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_changepassword, container, false
        )

        btnListener()
        return binding.root
    }

    private fun btnListener() {
        val user = FirebaseAuth.getInstance().currentUser

        binding.apply {
            buttonChange.setOnClickListener { view: View ->

                if (editTextOldpassword.text.toString().isEmpty()) {
                    editTextOldpassword.error = "Por favor introduzca su antigua contraseña"
                    editTextOldpassword.requestFocus()
                } else if (editTextNewpassword.text.toString() == editTextOldpassword.text.toString()) {
                    editTextNewpassword.error = "Por favor introduzca una contraseña nueva"
                    editTextNewpassword.requestFocus()
                } else if (editTextNewpassword.text.toString().isEmpty()) {
                    editTextNewpassword.error = "Por favor introduzca una contraseña"
                    editTextNewpassword.requestFocus()
                } else if (editTextNewpassword.text.toString() != editTextConfirmenewpassword.text.toString()) {
                    editTextNewpassword.error = "Las contraseñas no coinciden"
                    editTextNewpassword.requestFocus()
                } else {
                    user?.updatePassword(editTextNewpassword.text.toString())?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            FirebaseAuth.getInstance().signOut()
                            Toast.makeText(context, "Contraseña cambiada correctamente, por favor inicia sesión de nuevo.", Toast.LENGTH_SHORT).show()
                            view.findNavController()
                                .navigate(R.id.action_changePasswordFragment2_to_moreFragment)
                            //d("CHANGE", "contraseña actualizada")
                        }
                    }

                }
            }
        }
    }
}
