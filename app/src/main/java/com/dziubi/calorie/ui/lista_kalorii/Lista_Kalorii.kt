package com.dziubi.calorie.ui.lista_kalorii

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dziubi.calorie.MainActivity
import com.dziubi.calorie.MainViewModel
import com.dziubi.calorie.R
import com.dziubi.calorie.data.Kalorie
import com.dziubi.calorie.databinding.FragmentListaKaloriiBinding
import com.dziubi.calorie.ui.adapters.ListCaloryAdapters

class Lista_Kalorii : Fragment() {

    private val viewModel by viewModels<ListaKaloriiViewModel> ()
    private val mainVm by activityViewModels<MainViewModel> ()
    private var _binding : FragmentListaKaloriiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentListaKaloriiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val caloryList = mainVm.getCalorie()
        binding.recyclerView.adapter = ListCaloryAdapters(caloryList, {
            mainVm.selectedCalory(it)

            (requireActivity() as MainActivity).navigateToEdit()
        })




    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}