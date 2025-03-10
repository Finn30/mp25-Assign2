package com.example.budgetcalculator.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.budgetcalculator.R
import com.example.budgetcalculator.viewmodel.BudgetViewModel
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var budgetViewModel: BudgetViewModel
    private lateinit var resultCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputAmount = findViewById<EditText>(R.id.inputAmount)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val resultText = findViewById<TextView>(R.id.resultText)

        budgetViewModel = ViewModelProvider(this).get(BudgetViewModel::class.java)
        resultCard = findViewById(R.id.resultCard)

        fun formatToRupiah(amount: Double): String {
            val localeID = Locale("id", "ID")
            val formatter = NumberFormat.getCurrencyInstance(localeID)
            return formatter.format(amount)
        }


        btnCalculate.setOnClickListener {
            val amountText = inputAmount.text.toString().trim()
            val amount = amountText.toDoubleOrNull()

            if (amount != null && amount > 0) {
                budgetViewModel.calculatorBudget(amount)
                resultCard.visibility = View.VISIBLE
            } else {
                resultCard.visibility = View.GONE
                resultText.text = "Masukkan Jumlah Uang yang Valid"
            }
        }

        budgetViewModel.budgetData.observe(this) { budget ->
            resultText.text = getString(
                R.string.budget_result,
                formatToRupiah(budget.food),
                formatToRupiah(budget.transport),
                formatToRupiah(budget.entertainment),
                formatToRupiah(budget.savings)
            )


        }

    }
}
