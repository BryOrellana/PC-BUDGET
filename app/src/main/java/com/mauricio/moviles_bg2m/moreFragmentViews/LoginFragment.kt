package com.mauricio.moviles_bg2m.moreFragmentViews

import android.app.AlertDialog
import android.os.Bundle
import android.util.Patterns
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mauricio.moviles_bg2m.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mauricio.moviles_bg2m.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_signup.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )

        navs()
        return binding.root
    }

    private fun navs() {
        binding.apply {
            buttonSignup.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            }
            buttonLogin.setOnClickListener { view: View ->
                if (email.text.toString().isEmpty()) {
                    email.error = "Por favor introduzca su email"
                    email.requestFocus()
                } /*else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                    email.error = "Por favor introduzca un email valido"
                    email.requestFocus()
                }*/ else if (password.text.toString().isEmpty()) {
                    password.error = "Por favor introduzca una contraseña"
                    password.requestFocus()
                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }
                        else{
                            showAlert()
                        }
                    }
                }
            }
        }
    }
    private fun showAlert(){
        val builder= AlertDialog.Builder(this.context)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error. Intenta de nuevo en unos minutos.")
        builder.setPositiveButton("Aceptar",null)
        val dialogue: AlertDialog =builder.create()
        dialogue.show()
    }
}




