package com.cs407.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstNumber = findViewById<EditText>(R.id.value1)
        val secondNumber = findViewById<EditText>(R.id.value2)
        val add = findViewById<Button>(R.id.add)
        val subtract = findViewById<Button>(R.id.subtract)
        val multiply = findViewById<Button>(R.id.multiply)
        val divide = findViewById<Button>(R.id.divide)

        add.setOnClickListener { performOperation(firstNumber, secondNumber, "+") }
        subtract.setOnClickListener { performOperation(firstNumber, secondNumber, "-") }
        multiply.setOnClickListener { performOperation(firstNumber, secondNumber, "*") }
        divide.setOnClickListener { performOperation(firstNumber, secondNumber, "/") }
    }

    private fun performOperation(firstNumber: EditText, secondNumber: EditText, operation: String) {
        try {
            val num1 = firstNumber.text.toString().toInt()
            val num2 = secondNumber.text.toString().toInt()
            val result = when (operation) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0) num1 / num2 else throw ArithmeticException("Divide by zero")
                else -> 0
            }
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("result", result.toString())
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
