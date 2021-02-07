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

class DeleteData : AppCompatActivity() {
    var id: String? = null
    private var uid1ET: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_data)
        val delete = findViewById<Button>(R.id.delete_btn)
        uid1ET = findViewById(R.id.uid)
        delete.setOnClickListener {
            id = uid1ET!!.text.toString()
            DeleteDataActivity().execute()
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class DeleteDataActivity : AsyncTask<Void, Void, Void>() {
        private var dialog: ProgressDialog? = null
        private var result: String? = null
        override fun onPreExecute() {
            super.onPreExecute()
            dialog = ProgressDialog(this@DeleteData)
            dialog!!.setTitle("Hey Wait Please...")
            dialog!!.setMessage("Deleting... ")
            dialog!!.show()
        }

        override fun doInBackground(vararg params: Void): Void? {
            @Suppress("SpellCheckingInspection")
            Log.i(Controller.TAG, "IDVALUE$id")
            val jsonObject = Controller.deleteData(id)
            Log.i(Controller.TAG, "Json obj $jsonObject")
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