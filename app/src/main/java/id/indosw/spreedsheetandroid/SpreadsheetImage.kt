package id.indosw.spreedsheetandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SpreadsheetImage : AppCompatActivity() {
    private var addUser: Button? = null
    private var viewUser: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spreadsheet_image)
        addUser = findViewById(R.id.btn_add_user)
        viewUser = findViewById(R.id.btn_list_user)
        addUser!!.setOnClickListener {
            val intent = Intent(applicationContext, AddUser::class.java)
            startActivity(intent)
        }
        viewUser!!.setOnClickListener {
            val intent = Intent(applicationContext, UserList::class.java)
            startActivity(intent)
        }
    }
}