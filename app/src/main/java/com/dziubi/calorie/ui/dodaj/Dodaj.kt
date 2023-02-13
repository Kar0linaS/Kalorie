package com.dziubi.calorie.ui.dodaj

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dziubi.calorie.MainActivity
import com.dziubi.calorie.MainViewModel
import com.dziubi.calorie.data.Ilosc
import com.dziubi.calorie.data.Kalorie
import com.dziubi.calorie.data.Kategorie
import com.dziubi.calorie.databinding.FragmentDodajBinding

class Dodaj : Fragment() {

    private val viewModel by viewModels<DodajViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentDodajBinding? = null
    private val binding get() = _binding!!

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            (requireActivity() as MainActivity).setBottomNavVisibilty(true)
            isEnabled = false
            requireActivity().onBackPressed()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDodajBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleOnBackPressed()

        val wartosci = Ilosc.values().map { it.wartosc }.toList()
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            wartosci
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIlosc.adapter = adapter
        binding.button.setOnClickListener {
            var kaloria = createCaloria()
            mainVm.insertCalorie(kaloria)
        }
    }
    private fun handleOnBackPressed() {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            onBackPressedCallback)

    }
    private fun createCaloria(): Kalorie {
        setOnClick()

        val dsc = binding.enterText.text.toString()

        val ilosc = when (binding.spinnerIlosc.selectedItem) {
            100 -> Ilosc.sto
            200 -> Ilosc.dwieście
            300 -> Ilosc.trzysta
            400 -> Ilosc.czterysta
            500 -> Ilosc.piecset
            else -> Ilosc.zero
        }
        val type = when (binding.radioGroup.checkedRadioButtonId) {
            binding.radio1.id -> Kategorie.DODANE
            else -> Kategorie.SPALONE

        }
        return Kalorie(ilosc, dsc, type)
    }

    private fun setOnClick() {
        binding.button.setOnClickListener {
            createCaloria()
        }
        requireActivity().onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}