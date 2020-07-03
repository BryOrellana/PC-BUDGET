package com.mauricio.moviles_bg2m.moreFragmentViews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mauricio.moviles_bg2m.R
import com.mauricio.moviles_bg2m.databinding.FragmentProductDescriptionBinding

/**
 * A simple [Fragment] subclass.
 */
class DescriptionProductFragment : Fragment() {
    private lateinit var binding : FragmentProductDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_product_description,container,false)

        return binding.root
    }
}
