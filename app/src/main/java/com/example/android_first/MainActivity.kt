package com.example.android_first

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val themeTitleList = arrayOf("Dark", "Light")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        val spinnerView: Spinner = findViewById(R.id.modeSpinner)

        // Create the instance of ArrayAdapter having the list of courses
        val ad: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_expandable_list_item_1,
            themeTitleList
        )

        spinnerView.onItemSelectedListener = this

       // Set simple layout resource file for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the Spinner which binds data to spinner
        spinnerView.setAdapter(ad);

        val buttonToggleTheme: Button = findViewById(R.id.signIn)

        buttonToggleTheme.setOnClickListener {
            // Check current night mode and toggle
            val intent = Intent(this, UserDataActivity::class.java)
            startActivity(intent)
        }

        val logInTheme: Button = findViewById(R.id.logIn)

        logInTheme.setOnClickListener {
            // Check current night mode and toggle
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        Toast.makeText(this, "Seleted $position ", Toast.LENGTH_SHORT).show()
         if (position == 0) {
           // Set to Light Mode
           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

       } else {
           // Set to Dark Mode
           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
       }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
