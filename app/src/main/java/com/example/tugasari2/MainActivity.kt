package com.example.tugasari2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var vResult: TextView
    private lateinit var vInput: TextView
    private lateinit var switchDarkMode: Switch
    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vResult = findViewById(R.id.text_result)
        vInput = findViewById(R.id.text_input)
        switchDarkMode = findViewById(R.id.switchDarkMode)

        // Inisialisasi Switch Dark Mode
        switchDarkMode.setOnClickListener {
            if (switchDarkMode.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            recreate()
        }

        val buttons = arrayOf(
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7,
            R.id.button_8, R.id.button_9, R.id.button_dot, R.id.button_open_kurung,
            R.id.button_close_kurung, R.id.button_tambah, R.id.button_kurang,
            R.id.button_kali, R.id.button_bagi
        )

        for (buttonId in buttons) {
            findViewById<Button>(buttonId).setOnClickListener { onButtonClick(it) }
        }

        findViewById<Button>(R.id.button_clear).setOnClickListener { clear() }
        findViewById<Button>(R.id.button_back).setOnClickListener { backspace() }
        findViewById<Button>(R.id.button_samadengan).setOnClickListener { calculate() }
    }

    private fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()
        expression += buttonText
        vInput.text = expression
    }

    private fun clear() {
        expression = ""
        vInput.text = ""
        vResult.text = "0"
    }

    private fun backspace() {
        if (expression.isNotEmpty()) {
            expression = expression.substring(0, expression.length - 1)
            vInput.text = expression
        }
    }

    private fun calculate() {
        try {
            val result = ExpressionBuilder(expression).build().evaluate()
            vResult.text = if (result % 1 == 0.0) {
                result.toInt().toString()
            } else {
                result.toString()
            }
        } catch (e: Exception) {
            vResult.text = "Error"
        }
    }
}
