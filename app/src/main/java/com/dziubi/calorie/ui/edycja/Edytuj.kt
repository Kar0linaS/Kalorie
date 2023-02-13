package com.dziubi.calorie.ui.edycja

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
import com.dziubi.calorie.R
import com.dziubi.calorie.data.Ilosc
import com.dziubi.calorie.data.Kalorie
import com.dziubi.calorie.data.Kategorie
import com.dziubi.calorie.databinding.FragmentEdytujBinding

class Edytuj : Fragment() {

    private val viewModel by viewModels<EdytujViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentEdytujBinding? = null
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
        _binding = FragmentEdytujBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleOnBackPressed()
        setCurrentCalories(mainVm.getSelectedCalory())
    }

    private fun setCurrentCalories(kalorie: Kalorie) {
        setCurrentCategory(kalorie.Kategoria)
        setCurrentAmountCalories(kalorie.ilosc)
        setCurrentDesc(kalorie.opis)
        setOnClicks()
    }

    private fun setCurrentCategory(kategorie: Kategorie) {
        val checkId = when (kategorie) {
            Kategorie.SPALONE -> binding.radio2.id
            Kategorie.DODANE -> binding.radio1.id
        }
        binding.radioGroup.check(checkId)
    }

    private fun setCurrentDesc(opis: String) {
        binding.enterText.setText(opis.toString())
    }

    private fun setCurrentAmountCalories(ilosc: Ilosc) {
        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item,
            Ilosc.values().map { enum -> enum.wartosc })

        binding.spinnerIlosc.adapter = adapter
        val position = adapter.getPosition(ilosc.wartosc)
        binding.spinnerIlosc.setSelection(position)
    }

    private fun setOnClicks() {
        binding.button.setOnClickListener {
            updateCalorie()
        }
        binding.deleteButton.setOnClickListener {
            mainVm.getSelectedCalory()?.let { calory ->
                mainVm.deleteCalorie(listOf(calory))
                mainVm.unselectedCalory()

            }
            requireActivity().onBackPressed()
        }
    }

    private fun updateCalorie() {
        val updateCalory = createCalory()
        mainVm.updateCalorie(updateCalory)
        requireActivity().onBackPressed()
    }

    private fun createCalory(): Kalorie {

        var desc = binding.enterText.text.toString()


        val ilosc = when (binding.spinnerIlosc.selectedItem) {
            100 -> Ilosc.sto
            200 -> Ilosc.dwieście
            300 -> Ilosc.trzysta
            400 -> Ilosc.czterysta
            500 -> Ilosc.piecset
            else -> Ilosc.piecset
        }


        val type = when (binding.radioGroup.checkedRadioButtonId) {
            binding.radio1.id -> Kategorie.DODANE
            else -> Kategorie.SPALONE

        }
        return Kalorie(ilosc, desc, type)
    }

    private fun handleOnBackPressed() {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            onBackPressedCallback)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}