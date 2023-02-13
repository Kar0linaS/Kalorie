package com.dziubi.calorie.ui.dodane_kalorie

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.dziubi.calorie.MainViewModel
import com.dziubi.calorie.databinding.FragmentDodaneKalorieBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet

import com.github.mikephil.charting.data.PieEntry


class Dodane_kalorie : Fragment() {

    private val viewModel by viewModels<DodaneKalorieViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentDodaneKalorieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDodaneKalorieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sum = mainVm.getSumOfAddedCalories()
        binding.DodaneKalorieWykres.apply {
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            setEntryLabelTextSize(18f)
            setEntryLabelColor(Color.WHITE)
            centerText = "Kalorie dodane $sum"
            setEntryLabelTextSize(24f)
            description.isEnabled = false
            setTransparentCircleAlpha(50)
        }

        val listaDodanych = mainVm.getAddedCalories()

        val entries = ArrayList<PieEntry>()
        for (kalorie in listaDodanych) {
            val pieEntry = PieEntry(kalorie.ilosc.wartosc.toFloat(), "")
            entries.add(pieEntry)
        }

        val pieDataSet = PieDataSet(entries, "Dodane")
        pieDataSet.colors = listOf(
            Color.RED,
            Color.GRAY,
            Color.GREEN,
            Color.BLACK,
            Color.BLUE,
            Color.YELLOW,
        )
        val pieData = PieData(pieDataSet)

        binding.DodaneKalorieWykres.data = pieData

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}