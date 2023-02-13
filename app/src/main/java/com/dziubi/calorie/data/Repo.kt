package com.dziubi.calorie.data

import android.content.Context
import java.util.Currency.getInstance

class Repo() {



    private var Caloriee: MutableList<Kalorie> = mutableListOf(
        Kalorie(Ilosc.sto, "bieganie", Kategorie.SPALONE),
        Kalorie(Ilosc.piecset, "spacer", Kategorie.SPALONE),
        Kalorie(Ilosc.dwieście, "kebab", Kategorie.DODANE),
        Kalorie(Ilosc.czterysta, "pizza", Kategorie.DODANE)
    )


    fun insertCalorie(kalorie: Kalorie) {
        Caloriee.add(kalorie)
    }

    fun deleteCalorie(kalorie: List<Kalorie>) {
        Caloriee.removeAll(kalorie)
    }

    fun getCalorie(): List<Kalorie> = Caloriee

    fun updateCalorie(kalorie: Kalorie) {
        Caloriee.removeIf { it.opis == kalorie.opis }
        Caloriee.add(kalorie)
    }

    fun getSumOfAddedCalories(): Int {
        return Caloriee
            .filter { it.Kategoria == Kategorie.DODANE }
            .sumOf { it.ilosc.wartosc}
    }

    fun getSumOfLostCalories(): Int {
        return Caloriee
            .filter { it.Kategoria == Kategorie.SPALONE }
            .sumOf { it.ilosc.wartosc }
    }
    fun getAddedCalories(): List<Kalorie> {
        return Caloriee
            .filter { it.Kategoria == Kategorie.DODANE }
    }
    fun getLostCalories() : List<Kalorie>{
        return Caloriee
            .filter { it.Kategoria == Kategorie.SPALONE}
    }
}







