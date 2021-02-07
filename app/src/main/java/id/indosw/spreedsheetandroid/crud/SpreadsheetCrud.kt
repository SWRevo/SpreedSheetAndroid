package id.indosw.spreedsheetandroid.crud

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import id.indosw.spreedsheetandroid.R

class SpreadsheetCrud : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spreadsheet_crud)
        val read = findViewById<Button>(R.id.read_btn)
        val readAll = findViewById<Button>(R.id.read_all_btn)
        val insert = findViewById<Button>(R.id.insert_btn)
        val update = findViewById<Button>(R.id.update_btn)
        val delete = findViewById<Button>(R.id.delete_btn)
        readAll.setOnClickListener {
            if (InternetConnection.checkConnection(applicationContext)) {
                val intent = Intent(applicationContext, ReadAllData::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Check your internet connection",
                    Toast.LENGTH_LONG).show()
            }
        }
        insert.setOnClickListener {
            if (InternetConnection.checkConnection(applicationContext)) {
                val intent = Intent(applicationContext, InsertData::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Check your internet connection",
                    Toast.LENGTH_LONG).show()
            }
        }
        update.setOnClickListener {
            if (InternetConnection.checkConnection(applicationContext)) {
                val intent = Intent(applicationContext, UpdateData::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Check your internet connection",
                    Toast.LENGTH_LONG).show()
            }
        }
        read.setOnClickListener {
            if (InternetConnection.checkConnection(
                    applicationContext)) {
                val intent = Intent(applicationContext, ReadSingleData::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Check your internet connection",
                    Toast.LENGTH_LONG).show()
            }
        }
        delete.setOnClickListener {
            if (InternetConnection.checkConnection(
                    applicationContext)) {
                val intent = Intent(applicationContext, DeleteData::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Check your internet connection",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}