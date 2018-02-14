package com.tolodev.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import java.util.regex.Pattern

class MainActivity : AppCompatActivity(), CalculatorAdapter.ViewHolder.OptionListener {

    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CalculatorAdapter

    private var currentOperation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setupView()
    }

    private fun initView() {
        editText = findViewById(R.id.editText_field)
        recyclerView = findViewById(R.id.recyclerView_options)
    }

    private fun setupView() {
        adapter = CalculatorAdapter(dataSet())
        adapter.optionListener = this
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 4)
        recyclerView.adapter = adapter
    }

    private fun dataSet(): MutableList<String> {
        val options = mutableListOf<String>()
        val fooArray = resources.getStringArray(R.array.options)
        var i = 0
        while (i < fooArray.size) {
            options.add(fooArray[i])
            i++
        }
        return options
    }

    override fun optionSelected(value: String) {
        currentOperation += value
        editText.setText(currentOperation)
//        if (getOperators().contains(value) && value == getString(R.string.option_equals)) {

        if (getOperators().contains(value)) {


            val pattern = Pattern.compile("([\\/\\+\\-\\*])")
            val matcher = pattern.matcher(currentOperation)
            if (matcher.find()) {
                Log.e(this::javaClass.name, "Numbers: ".plus(currentOperation))
                val operator = matcher.group(1)
                Log.e(this::javaClass.name, "Operator: ".plus(operator))
                val numbers = currentOperation.split(operator)

//                if (numbers.size < 3 && !TextUtils.isEmpty(numbers[0]) && !TextUtils.isEmpty(numbers[1])) {
//                    currentOperation = getResult(operator, numbers).toString()
//                } else if (numbers.size >= 3) {
//                    currentOperation += (numbers[2])
//                }

                if (numbers.size == 2) {
                    if (!TextUtils.isEmpty(numbers[0]) && !TextUtils.isEmpty(numbers[1])) {
                        currentOperation = getResult(operator, numbers).toString()
                    }
                } else {
//                    if (!TextUtils.isEmpty(numbers[0]) && !TextUtils.isEmpty(numbers[1]) && TextUtils.isEmpty(numbers[2])) {
//                        val lastWord = currentOperation.substring(currentOperation.length - 1, currentOperation.length)
//                        optionSelected(lastWord)
//                    }
                }

                editText.setText(currentOperation)
            }
        } else if (value == getString(R.string.option_clear)) {
            currentOperation = ""
            editText.setText(currentOperation)
        }

    }

    private fun getResult(operator: String, numbers: List<String>): Double {
        var res = 0.0
        when (operator) {
            getString(R.string.option_plus) -> res = sum(numbers[0].toDouble(), numbers[1].toDouble())
            getString(R.string.option_minus) -> res = subtract(numbers[0].toDouble(), numbers[1].toDouble())
            getString(R.string.option_product) -> res = multiply(numbers[0].toDouble(), numbers[1].toDouble())
            getString(R.string.option_division) -> res = divide(numbers[0].toDouble(), numbers[1].toDouble())
        }
        return res
    }

    private fun getOperators(): MutableList<String> {
        val operators = mutableListOf<String>()
        operators.add(getString(R.string.option_plus))
        operators.add(getString(R.string.option_minus))
        operators.add(getString(R.string.option_product))
        operators.add(getString(R.string.option_division))
        operators.add(getString(R.string.option_percentage))
        operators.add(getString(R.string.option_equals))
        return operators
    }

    private fun sum(numberA: Double, numberB: Double) = numberA + numberB

    private fun subtract(numberA: Double, numberB: Double) = numberA - numberB

    private fun multiply(numberA: Double, numberB: Double) = numberA * numberB

    private fun divide(numberA: Double, numberB: Double) = numberA / numberB
}
