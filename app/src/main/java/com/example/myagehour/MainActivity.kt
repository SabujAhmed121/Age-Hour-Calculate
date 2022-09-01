package com.example.myagehour

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var setda: TextView? = null
    var minuda: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonClick : Button = findViewById(R.id.BottonClick)

        setda = findViewById(R.id.Selectddate)
        minuda = findViewById(R.id.minutesin)

        buttonClick.setOnClickListener {

            setdateinhour()

        }
    }
    private fun setdateinhour(){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dp = DatePickerDialog(this,
            {view, selecteyear, selmonth,selecDay ->

                Toast.makeText(this, "Hello everythink working", Toast.LENGTH_LONG).show()

                val selectDate = "$selecDay/${selmonth+1}/$selecteyear"
                setda?.text = selectDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val thedate = sdf.parse(selectDate)

                thedate?.let {
                    val selecteddateinHour = thedate.time / 3600000

                    val currentime = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentime?.let {
                        val currentimeinhour = currentime.time / 3600000

                        val timedifferent = currentimeinhour - selecteddateinHour

                        minuda?.text = timedifferent.toString()
                    }
                }

            },
            year,
            month,
            day
        )
        dp.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dp.show()
    }
}