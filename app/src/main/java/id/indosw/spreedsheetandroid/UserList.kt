@file:Suppress("DEPRECATION")

package id.indosw.spreedsheetandroid

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class UserList : AppCompatActivity() {
    private var listView: ListView? = null
    private var userListAdapter: UserListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_list)
        listView = findViewById(R.id.list_view)
        sendRequest()
    }

    private fun sendRequest() {
        val loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false)
        val stringRequest = StringRequest(
            Configuration.LIST_USER_URL,
            { response: String ->
                //Log.e("null","ser image"+response);
                showJSON(response)
                loading.dismiss()
            }
        ) { error: VolleyError ->
            Toast.makeText(this@UserList, error.message, Toast.LENGTH_LONG).show()
        }
        val socketTimeout = 30000 // 30 seconds. You can change it
        val policy: RetryPolicy = DefaultRetryPolicy(
            socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        stringRequest.retryPolicy = policy
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    private fun showJSON(json: String) {
        val pj = JsonParser(json)
        pj.parseJSON()
        //Log.e("uImage","ser image"+JsonParserKt.uImages[1]);
        userListAdapter = UserListAdapter(
            this,
            JsonParser.uIds,
            JsonParser.uNames,
            JsonParser.uImages
        )
        listView!!.adapter = userListAdapter
    }
}