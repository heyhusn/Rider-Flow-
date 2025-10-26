package com.example.rideflowappp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CountryCodeAdapter(
    context: Context,
    private val countries: List<Country>
) : ArrayAdapter<Country>(context, 0, countries) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // For the selected item (spinner closed), show only code
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_item, parent, false)

        val country = getItem(position)
        val textView = view.findViewById<TextView>(android.R.id.text1)

        country?.let {
            textView.text = it.code
            textView.setTextColor(context.getColor(android.R.color.white))
        }

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        // For dropdown items, show flag + name + code
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.custom_spinner_item, parent, false)

        val country = getItem(position)
        val flagImageView = view.findViewById<ImageView>(R.id.flagImageView)
        val countryTextView = view.findViewById<TextView>(R.id.countryTextTextView)

        country?.let {
            flagImageView.setImageResource(it.flagResId)
            countryTextView.text = "${it.name} ${it.code}"
        }

        return view
    }
}