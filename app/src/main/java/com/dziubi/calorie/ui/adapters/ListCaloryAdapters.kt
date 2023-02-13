package com.dziubi.calorie.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dziubi.calorie.R
import com.dziubi.calorie.data.Kalorie
import com.dziubi.calorie.data.Kategorie
import com.dziubi.calorie.databinding.ListRowBinding

class ListCaloryAdapters(
    private val calorie: List<Kalorie>,
    private val onClick: (Kalorie) -> Unit,
) : RecyclerView.Adapter<ListCaloryAdapters.CaloryViewHolder>() {

    inner class CaloryViewHolder(binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {


        val opis = binding.opis
        val kategoria = binding.category
        val obrazek = binding.image
        val ilosc = binding.ilosc

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaloryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListRowBinding.inflate(inflater, parent, false)
        return CaloryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CaloryViewHolder, position: Int) {

        val typeObrazek = when (calorie[position].Kategoria) {
            Kategorie.DODANE -> R.drawable.ic_baseline_arrow_upward_24
            Kategorie.SPALONE -> R.drawable.ic_baseline_arrow_downward_24
        }

        val color = when (calorie[position].Kategoria) {
            Kategorie.SPALONE -> Color.GREEN
            Kategorie.DODANE -> Color.RED
        }

        holder.opis.text = calorie[position].opis
        holder.ilosc.text = calorie[position].ilosc.toString()
        holder.kategoria.text = calorie[position].Kategoria.toString()
        holder.obrazek.setImageResource(typeObrazek)
        holder.obrazek.setBackgroundColor(color)
        holder.itemView.setOnClickListener {
            onClick(calorie[position])
        }
    }




override fun getItemCount(): Int {
    return calorie.size
}

}