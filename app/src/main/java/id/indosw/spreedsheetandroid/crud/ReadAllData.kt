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
import java.util.*

class ReadAllData : AppCompatActivity() {
    private var list: ArrayList<MyDataModel>? = null
    private var adapter: MyArrayAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.read_all)
        val readAll = findViewById<Button>(R.id.readAll_btn1)
        list = ArrayList()
        adapter = MyArrayAdapter(this, list!!)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        readAll.setOnClickListener { ReadData1().execute() }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class ReadData1 : AsyncTask<Void, Void, Void>() {
        private var dialog: ProgressDialog? = null
        private var jIndex = 0
        private var x = 0
        override fun onPreExecute() {
            super.onPreExecute()
            x = list!!.size
            jIndex = if (x == 0) 0 else x
            dialog = ProgressDialog(this@ReadAllData)
            dialog!!.setTitle("Hey Wait Please...$x")
            dialog!!.setMessage("Fetching all the Values")
            dialog!!.show()
        }

        override fun doInBackground(vararg params: Void): Void? {
            val jsonObject = Controller.readAllData()
            try {
                if (jsonObject != null) {
                    if (jsonObject.length() > 0) {
                        val array = jsonObject.getJSONArray("records")
                        val lenArray = array.length()
                        if (lenArray > 0) {
                            while (jIndex < lenArray) {
                                val model = MyDataModel()
                                val innerObject = array.getJSONObject(jIndex)
                                val id = innerObject.getString("id")
                                val name = innerObject.getString("username")
                                //  String image = innerObject.getString(Keys.KEY_IMAGE);
                                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                //String phone = phoneObject.getString(Keys.KEY_MOBILE);
                                model.name = name
                                model.setId(id)
                                //model.setImage(image);
                                list!!.add(model)
                                jIndex++
                            }
                        }
                    }
                }
            } catch (je: JSONException) {
                Log.i(Controller.TAG, "" + je.localizedMessage)
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            dialog!!.dismiss()
            if (list!!.size > 0) {
                adapter!!.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No data found", Toast.LENGTH_LONG).show()
            }
        }
    }
}