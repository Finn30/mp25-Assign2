package com.example.budgetcalculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budgetcalculator.model.Budget

class BudgetViewModel : ViewModel(){
    private val _budgetData = MutableLiveData<Budget>()
    val budgetData: LiveData<Budget> = _budgetData

    fun calculatorBudget(amount: Double){
        if (amount > 0) {
            val food = amount * 0.50
            val transport = amount * 0.20
            val entertainment = amount * 0.15
            val savings = amount * 0.15

            _budgetData.value = Budget(food, transport, entertainment, savings)
        }
    }
}