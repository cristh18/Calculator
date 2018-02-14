package com.tolodev.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(), CalculatorAdapter.ViewHolder.OptionListener, TextWatcherListener {

    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CalculatorAdapter

    private var currentOperation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initListeners()
        setupView()
    }

    private fun initView() {
        editText = findViewById(R.id.editText_field)
        recyclerView = findViewById(R.id.recyclerView_options)
    }

    private fun initListeners(){
        editText.addTextChangedListener(this)
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

    /**
     *
     */

    override fun optionSelected(value: String) {
//        if (value != getString(R.string.option_equals)) {
//            currentOperation = currentOperation.plus(value)
//            editText.setText(currentOperation)
//        } else {
////            val operators = currentOperation.split("([\\/\\+\\-\\*])")
////            for (s in operators){
////
////            }
//
//            if (currentOperation.contains(getString(R.string.option_plus))) {
//                val operators = currentOperation.split(getString(R.string.option_plus))
//                val result = operators.sumByDouble { (it).toDouble() }
//                currentOperation = result.toString()
//                editText.setText(currentOperation)
//            } else if () {
//
//            } else if () {
//
//            } else if () {
//
//            }
//        }

        if (value != getString(R.string.option_equals)) {
            currentOperation = currentOperation.plus(value)
            editText.setText(currentOperation)
        }
    }

    override fun afterTextChanged(s: Editable?) {
        super.afterTextChanged(s)
        Toast.makeText(this, "Element: ".plus(s), Toast.LENGTH_SHORT).show()

        if (s.toString().contains("([\\/\\+\\-\\*])")){
            val operators = currentOperation.split("([\\/\\+\\-\\*])")

            for (operator in operators){
                Log.e(this::javaClass.name, "Operator: ".plus(operator))
            }
        }
    }
}
