package id.indosw.spreedsheetandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import id.indosw.spreedsheetandroid.crud.SpreadsheetCrud

class HomeActivity : AppCompatActivity() {
    private var spreadSheetWithImage: Button? = null
    private var spreadSheetCRUD: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        spreadSheetWithImage = findViewById(R.id.btn_spreadsheet_img)
        spreadSheetCRUD = findViewById(R.id.btn_crud_spreadsheet)
        spreadSheetWithImage!!.setOnClickListener {
            val intent = Intent(applicationContext, SpreadsheetImage::class.java)
            startActivity(intent)
        }
        spreadSheetCRUD!!.setOnClickListener {
            val intent = Intent(applicationContext, SpreadsheetCrud::class.java)
            startActivity(intent)
        }
    }
}