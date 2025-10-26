package com.example.rideflowappp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Country(
    val name: String,
    val code: String,
    val flagResId: Int
)

class Screen2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)

        setupCountryCodeSpinner()
        setupUserTypeSpinner()
    }

    private fun setupCountryCodeSpinner() {
        val countryCodeSpinner: Spinner = findViewById(R.id.countryCodeSpinner)
        val flagIcon: ImageView = findViewById(R.id.flagIcon)

        val countryList = listOf(
            Country("Pakistan", "+92", R.drawable.pakistanflag),
            Country("USA", "+1", R.drawable.usaflag),
            Country("Canada", "+2", R.drawable.canada),
            Country("India", "+91", R.drawable.indiaflag),
            Country("UK", "+44", R.drawable.uk)
        )

        // Use custom adapter
        val adapter = CountryCodeAdapter(this, countryList)
        countryCodeSpinner.adapter = adapter

        // Set default selection to Pakistan
        countryCodeSpinner.setSelection(0)

        countryCodeSpinner.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedCountry = countryList[position]
                flagIcon.setImageResource(selectedCountry.flagResId)
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun setupUserTypeSpinner() {
        val userTypeSpinner: Spinner = findViewById(R.id.userTypeSpinner)

        val userTypes = resources.getStringArray(R.array.user_types)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            userTypes
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userTypeSpinner.adapter = adapter

        userTypeSpinner.setSelection(0)

        userTypeSpinner.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedUserType = userTypes[position]

            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {

            }
        }
    }
}