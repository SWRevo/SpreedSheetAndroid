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

class UpdateData : AppCompatActivity() {
    var id: String? = null
    var name: String? = null
    private var uid1ET: EditText? = null
    private var nameET: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_data)
        val update = findViewById<Button>(R.id.update_btn1)
        uid1ET = findViewById(R.id.uid)
        nameET = findViewById(R.id.name)
        update.setOnClickListener {
            id = uid1ET!!.text.toString()
            name = nameET!!.text.toString()
            UpdateDataActivity().execute()
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class UpdateDataActivity : AsyncTask<Void, Void, Void>() {
        private var dialog: ProgressDialog? = null
        private var x = 0
        private var result: String? = null
        override fun onPreExecute() {
            super.onPreExecute()
            dialog = ProgressDialog(this@UpdateData)
            dialog!!.setTitle("Hey Wait Please...$x")
            dialog!!.setMessage("I am getting your JSON")
            dialog!!.show()
        }

        override fun doInBackground(vararg params: Void): Void? {
            val jsonObject = Controller.updateData(id, name)
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