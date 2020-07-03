package com.mauricio.moviles_bg2m.moreFragmentViews

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentSignupBinding
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {
    lateinit var binding: FragmentSignupBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentSignupBinding>(
            inflater,
            R.layout.fragment_signup, container, false
        )

        btnSignUpListener()
        return binding.root
    }

    private fun btnSignUpListener() {
        binding.buttonCreate.setOnClickListener { view: View ->
            if (email.text.toString().isEmpty()) {
                email.error = "Por favor introduzca su email"
                email.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                email.error = "Por favor introduzca un email valido"
                email.requestFocus()
            } else if (password.text.toString().isEmpty()) {
                password.error = "Por favor introduzca una contraseña"
                password.requestFocus()
            } else if (password.text.toString() != conf_password.text.toString()) {
                password.error = "Las contraseñas no coinciden"
                password.requestFocus()
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                    }
                    else{
                        showAlert()
                    }
                }

            }
        }
    }

    private fun showAlert(){
        val builder=AlertDialog.Builder(this.context)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error registrando al usuario. Intenta de nuevo en unos minutos.")
        builder.setPositiveButton("Aceptar",null)
        val dialogue: AlertDialog=builder.create()
        dialogue.show()
    }

}