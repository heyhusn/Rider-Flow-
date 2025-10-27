package com.example.rideflowappp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

data class Country(
    val name: String,
    val code: String,
    val flagResId: Int
)

class Screen2 : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var phonenumber: EditText
    private lateinit var password: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var againpassword: EditText
    private lateinit var errorMessage: TextView
    private val logintext: TextView by lazy { findViewById(R.id.logintext) }
    private val createAccountbtn: AppCompatButton by lazy { findViewById(R.id.createAccountbtn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)

        setupCountryCodeSpinner()
        setupUserTypeSpinner()
        setupVariables()

        createAccountbtn.setOnClickListener {
            val usernameText = username.text.toString().trim()
            val emailText = email.text.toString().trim()
            val phonenumberText = phonenumber.text.toString().trim()
            val passwordText = password.text.toString()
            val againPasswordText = againpassword.text.toString()

            // Check if passwords match
            if (passwordText != againPasswordText) {
                errorMessage.text = "❗ Password fields should match each other"
                return@setOnClickListener
            }

            // Validate password pattern
            val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>]).{8,}\$")
            if (!passwordText.matches(passwordRegex)) {
                errorMessage.text = "❗ Password must contain 1 capital letter, 1 special character, and be at least 8 characters long"
                return@setOnClickListener
            }


            val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
            if (!emailText.matches(emailRegex)) {
                errorMessage.text = "❗ Invalid email format"
                return@setOnClickListener
            }
            //check is there any empty field left?
            if (usernameText.isEmpty() || emailText.isEmpty() || phonenumberText.isEmpty() ||
                passwordText.isEmpty() || againPasswordText.isEmpty()) {
                errorMessage.text = "❗ All fields must be filled"
                return@setOnClickListener
            }


        }
    }

    private fun setupVariables() {
        username = findViewById(R.id.username)
        email = findViewById(R.id.emailaddress)
        auth = FirebaseAuth.getInstance()
        phonenumber = findViewById(R.id.phonenumber)
        errorMessage = findViewById(R.id.errorMessage)
        password = findViewById(R.id.password)
        againpassword = findViewById(R.id.againpassword)
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

        val adapter = CountryCodeAdapter(this, countryList)
        countryCodeSpinner.adapter = adapter
        countryCodeSpinner.setSelection(0)

        countryCodeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                val selectedCountry = countryList[position]
                flagIcon.setImageResource(selectedCountry.flagResId)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
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
    }
}
