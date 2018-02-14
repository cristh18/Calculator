package com.tolodev.calculator

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class CalculatorAdapter constructor(dataSet: MutableList<String>) : RecyclerView.Adapter<CalculatorAdapter.ViewHolder>() {

    var calculatorOptions: MutableList<String> = dataSet

    lateinit var optionListener: ViewHolder.OptionListener

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val calculatorOption: Button = LayoutInflater.from(parent?.context).inflate(R.layout.calculator_option_view, parent, false) as Button
        return ViewHolder(calculatorOption, optionListener)
    }

    override fun getItemCount(): Int = calculatorOptions.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.button!!.text = calculatorOptions[position]
    }

    class ViewHolder(var button: Button, private var optionListener: OptionListener) : RecyclerView.ViewHolder(button), View.OnClickListener {

        init {
            button.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val button = v as Button
            optionListener.optionSelected(button.text.toString())
        }

        interface OptionListener {
            fun optionSelected(value: String)
        }
    }

}