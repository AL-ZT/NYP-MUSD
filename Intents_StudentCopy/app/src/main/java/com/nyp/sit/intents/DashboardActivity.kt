package com.nyp.sit.intents

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.SmsMessage
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity :updateUI , AppCompatActivity() {

    var permissionGranted = false
    val smsReceiver = SmsReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        //TODO 2 : retrieve the extra placed in the received intent using the "loginid" key


        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECEIVE_SMS), 10001)

        }
        else
        {
            permissionGranted = true

        }



    }


    fun callLecturerBtn(v : View){

        //TODO 3 : Create and Initialize an Implicit Intent to dial the number.


    }

    override fun onStart() {
        super.onStart()

        if(permissionGranted) {
            val filter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")

            registerReceiver(smsReceiver, filter)

            smsReceiver.bindListener(object:updateUI.receiverListener{

                override fun onReceiveSMS(msg: String) {
                    smsView.text = msg
                }
            })
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){

            10001->{
                if(grantResults.isNotEmpty() && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){

                    permissionGranted = true

                }
            }
        }
    }


    class SmsReceiver() : BroadcastReceiver(){

        var receiverListener: updateUI.receiverListener ?= null


        fun bindListener(listener: updateUI.receiverListener)
        {
            if(listener != null)
            {

                receiverListener = listener
            }

        }
        override fun onReceive(p0: Context?, p1: Intent?) {


            val data = p1!!.extras
            val pdusObj = data!!.get("pdus") as Array<*>
            for (i in pdusObj.indices) {
                val currentMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                    Telephony.Sms.Intents.getMessagesFromIntent(p1)[0]
                else SmsMessage.createFromPdu(pdusObj[0] as ByteArray)

                val senderPhoneNumber = currentMessage.displayOriginatingAddress // Sender Phone Number
                Log.e("senderPhoneNumber", senderPhoneNumber)

                val smsContent = currentMessage.displayMessageBody // SMS body content



                if(receiverListener!=null)
                {


                    receiverListener!!.onReceiveSMS(smsContent)
                }
            }


        }

    }

}


interface updateUI{

    interface receiverListener{

        fun onReceiveSMS(msg: String)
    }


}
