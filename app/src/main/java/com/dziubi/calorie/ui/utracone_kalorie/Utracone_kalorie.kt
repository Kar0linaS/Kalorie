package com.dziubi.calorie.ui.utracone_kalorie

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dziubi.calorie.MainViewModel
import com.dziubi.calorie.R
import com.dziubi.calorie.databinding.FragmentUtraconeKalorieBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class Utracone_kalorie : Fragment() {

    private val viewModel by viewModels<UtraconeKalorieViewModel>()
    private val mainVm by viewModels<MainViewModel>()
    private var _binding: FragmentUtraconeKalorieBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentUtraconeKalorieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sum = mainVm.getSumOfAddedCalories()

        binding.utraconeKalorieWykres.apply {
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            setEntryLabelTextSize(18f)
            setEntryLabelColor(Color.WHITE)
            centerText = "Utracone kalorie $sum"
            setEntryLabelTextSize(24f)
            description.isEnabled = false
            setTransparentCircleAlpha(50)
        }


        val listaUtraconych = mainVm.getLostCalories()

        val entries = ArrayList<PieEntry>()
        for (kalorie in listaUtraconych ) {
            val pieEntry = PieEntry(kalorie.ilosc.wartosc.toFloat(), "")
            entries.add(pieEntry)
        }

        val pieDataSet = PieDataSet(entries, "Utracone")
        pieDataSet.colors = listOf(
            Color.RED,
            Color.GRAY,
            Color.GREEN,
            Color.BLACK,
            Color.BLUE,
            Color.YELLOW,
        )
        val pieData = PieData(pieDataSet)

        binding.utraconeKalorieWykres.data = pieData

    }
        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
    }
