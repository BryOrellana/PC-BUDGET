package com.mauricio.moviles_bg2m.moreFragmentViews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentChangepasswordBinding

/**
 * A simple [Fragment] subclass.
 */
class ChangePasswordFragment : Fragment() {
    lateinit var binding : FragmentChangepasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_changepassword,container,false)

        btnListener()
        return binding.root
    }

    private fun btnListener(){
        binding.buttonChange.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_changePasswordFragment2_to_moreFragment)
        }
    }
}
