@file:Suppress("PrivatePropertyName", "DEPRECATION", "SpellCheckingInspection")

package id.indosw.spreedsheetandroid.crud

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import id.indosw.spreedsheetandroid.R
import org.json.JSONException

class ReadSingleData : AppCompatActivity() {
    var id: String? = null
    var name: String? = null
    private var uid1ET: EditText? = null
    private var id_l: TextView? = null
    private var name_l: TextView? = null
    private var id_v: TextView? = null
    private var name_v: TextView? = null
    var view: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_data)
        val read = findViewById<Button>(R.id.insert_btn)
        uid1ET = findViewById(R.id.uid)
        id_l = findViewById(R.id.id_l)
        name_l = findViewById(R.id.name_l)
        id_v = findViewById(R.id.id_v)
        name_v = findViewById(R.id.name_v)
        view = this.currentFocus
        read.setOnClickListener {
            id = uid1ET!!.text.toString()
            ReadDataActivity().execute()
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class ReadDataActivity : AsyncTask<Void, Void, Void>() {
        private var dialog: ProgressDialog? = null
        override fun onPreExecute() {
            super.onPreExecute()
            dialog = ProgressDialog(this@ReadSingleData)
            dialog!!.setTitle("Hey Wait Please...")
            dialog!!.setMessage("Fetching your values")
            dialog!!.show()
        }

        override fun doInBackground(vararg params: Void): Void? {
            Log.i(Controller.TAG, "IDVALUE$id")
            val jsonObject = Controller.readData(id)
            Log.i(Controller.TAG, "Json obj $jsonObject")
            try {
                if (jsonObject != null) {
                    val user = jsonObject.getJSONObject("user")
                    name = user.getString("name")
                }
            } catch (je: JSONException) {
                Log.i(Controller.TAG, "" + je.localizedMessage)
            }
            return null
        }

        @SuppressLint("SetTextI18n")
        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            dialog!!.dismiss()
            if (view != null) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view!!.windowToken, 0)
            }
            if (name != null) {
                id_l!!.text = "ID"
                name_l!!.text = "NAME"
                id_v!!.text = id
                name_v!!.text = name
            } else Toast.makeText(applicationContext, "ID not found", Toast.LENGTH_LONG).show()
        }
    }
}