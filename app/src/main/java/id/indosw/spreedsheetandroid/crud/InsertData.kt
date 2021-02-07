@file:Suppress("DEPRECATION")

package id.indosw.spreedsheetandroid.crud

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import id.indosw.spreedsheetandroid.R
import org.json.JSONException

class InsertData : AppCompatActivity() {
    var id: String? = null
    var name: String? = null
    private var uid1ET: EditText? = null
    private var nameET: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_data)
        val insert = findViewById<Button>(R.id.insert_btn)
        uid1ET = findViewById(R.id.uid)
        nameET = findViewById(R.id.name)
        insert.setOnClickListener {
            id = uid1ET!!.text.toString()
            name = nameET!!.text.toString()
            InsertDataActivity().execute()
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class InsertDataActivity : AsyncTask<Void, Void, Void>() {
        private var dialog: ProgressDialog? = null
        private var result: String? = null
        override fun onPreExecute() {
            super.onPreExecute()
            dialog = ProgressDialog(this@InsertData)
            dialog!!.setTitle("Hey Wait Please...")
            dialog!!.setMessage("Inserting your values..")
            dialog!!.show()
        }

        override fun doInBackground(vararg params: Void): Void? {
            val jsonObject = Controller.insertData(id, name)
            Log.i(Controller.TAG, "Json obj ")
            try {
                if (jsonObject != null) {
                    result = jsonObject.getString("result")
                }
            } catch (je: JSONException) {
                Log.i(Controller.TAG, "" + je.localizedMessage)
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            dialog!!.dismiss()
            Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
        }
    }
}