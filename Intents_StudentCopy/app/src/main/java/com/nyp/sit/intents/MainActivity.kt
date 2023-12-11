package com.nyp.sit.intents

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_basic.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_basic)


        btnSubmit.setOnClickListener {


            if(passwordET.text.toString() == "password")
            {

                //TODO 1 : Create and Initialize an Explicit Intent for DashboardActivity.
                // - Put an extra called "loginid", it should hold the text value of loginid
                // - Start an Activity using the intent


            }
            else{

                displayToast("Error")

            }

        }
    }

    fun displayToast(message:String){

        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
