package com.example.dropdowntextfieldautocomplete

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //AutoCompleteTextView
        var textField = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        textField.isFocusable = true
        textField.isFocusableInTouchMode = true
        textField.requestFocus()

        var customers = arrayListOf("Rahim","Karim","Rahul","Rubel Sikder","Shakil Ahmed Shakil Ahmed \nShakil Ahmed Shakil Ahmed \nShakil Ahmed Shakil Ahmed \nShakil Ahmed")
        var adapter = ArrayAdapter<String>(this,R.layout.spinner_text_view,customers)
        textField.setAdapter(adapter)

        textField.setOnTouchListener { _, _ ->
            textField.showDropDown()
            false
        }

        textField.setOnItemClickListener { parent, view, position, id ->
            var data = parent.getItemAtPosition(position)
            println(parent.getItemAtPosition(position))
            Toast.makeText(applicationContext, "$data", Toast.LENGTH_SHORT).show()
        }
        //End of AutoCompleteTextView



        //Multi Select Chip Group
        var selectedList : ArrayList<String> = ArrayList()
        var textField2 = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView2)
        var button = findViewById<Button>(R.id.button)
        var chipGroupData = findViewById<ChipGroup>(R.id.chipGroup)

        textField2.isFocusable = true
        textField2.isFocusableInTouchMode = true
        textField2.requestFocus()

        var brands = arrayListOf("Finix","Esotid","Vasco")
        var adapterAnother = ArrayAdapter<String>(this,R.layout.spinner_text_view,brands)
        textField2.setAdapter(adapterAnother)

        textField2.setOnTouchListener { _, _ ->
            textField2.showDropDown()
            false
        }

        textField2.setOnItemClickListener { parent, view, position, id ->
            var data = parent.getItemAtPosition(position)
            println(parent.getItemAtPosition(position))

            if(textField2.text.isNotEmpty()){
                val chip = Chip(this)
                chip.text = textField2.text.toString()
                chip.isCloseIconVisible = true

                chip.setOnCloseIconClickListener {
                    chipGroupData.removeView(chip)
                    selectedList.remove(chip.text.toString())

                    brands.add(chip.text.toString())
                    var adapterAnother = ArrayAdapter<String>(this,R.layout.spinner_text_view,brands)
                    textField2.setAdapter(adapterAnother)
                }

                chipGroupData.addView(chip)
                selectedList.add(chip.text.toString())

                brands.remove(chip.text.toString())
                var adapterAnother = ArrayAdapter<String>(this,R.layout.spinner_text_view,brands)
                textField2.setAdapter(adapterAnother)
            }
            Toast.makeText(applicationContext, "$data", Toast.LENGTH_SHORT).show()
            textField2.text.clear()
        }

        button.setOnClickListener {
            println("nnnnnnnnnnnnnnnnnnnnnnn")
            println(selectedList)
        }
        // End of Multi Select Chip Group



    }
}