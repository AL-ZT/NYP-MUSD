package com.nyp.stud172026H.pracmocktest.q1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun register(v : View) {
        var name : String = findViewById<TextView>(R.id.editName).text.toString()
        var phone : String = findViewById<TextView>(R.id.editPhone).text.toString()
        var gender : String = findViewById<RadioButton>(selectGender.checkedRadioButtonId).text.toString()

        Toast.makeText(this, "Name : $name \nPhone : $phone \nGender : $gender", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.saveBtn) {
            register(findViewById(R.id.saveBtn))
        }

        return super.onOptionsItemSelected(item)
    }
}
