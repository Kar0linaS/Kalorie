package com.dziubi.calorie

import androidx.lifecycle.ViewModel
import com.dziubi.calorie.data.Kalorie
import com.dziubi.calorie.data.Repo

class MainViewModel : ViewModel() {
    var isBottomNavVisible = true
    private var selectedCalory: Kalorie? = null
    val repo = Repo()

    fun insertCalorie(kalorie: Kalorie) {
        repo.insertCalorie(kalorie)
    }

    fun getCalorie(): List<Kalorie> = repo.getCalorie()

    fun deleteCalorie(kalorie: List<Kalorie>) {
        repo.deleteCalorie(kalorie)
    }

    fun updateCalorie(kalorie: Kalorie) {
        repo.updateCalorie(kalorie)
    }

    fun getSumOfAddedCalories(): Int {
        return repo.getSumOfAddedCalories()
    }

    fun getAddedCalories(): List<Kalorie> {
        return repo.getAddedCalories()
    }

    fun getLostCalories(): List<Kalorie> {
        return repo.getLostCalories()
    }
    fun selectedCalory(kalorie: Kalorie) {
        selectedCalory = kalorie
    }
    fun unselectedCalory(){
        selectedCalory = null
    }
    fun getSelectedCalory() = selectedCalory!!

}


