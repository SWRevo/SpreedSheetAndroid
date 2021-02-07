@file:Suppress("SpellCheckingInspection")

package id.indosw.spreedsheetandroid.crud

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object Controller {
    const val TAG = "TAG"
    private const val WAURL =
        "https://script.google.com/macros/s/AKfycbwK_nN91d5lG997VCFrcAksn4QsLgjnGko8JnJM3vCQ3DeqlKk/exec?"
    private var response: Response? = null
    fun readAllData(): JSONObject? {
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(WAURL + "action=readAll")
                .build()
            response = client.newCall(request).execute()
            return JSONObject(response!!.body()!!.string())
        } catch (e: IOException) {
            Log.e(TAG, "" + e.localizedMessage)
        } catch (e: JSONException) {
            Log.e(TAG, "" + e.localizedMessage)
        }
        return null
    }

    fun insertData(id: String?, name: String?): JSONObject? {
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(WAURL + "action=insert&id=" + id + "&name=" + name)
                .build()
            response = client.newCall(request).execute()
            //Log.e(TAG,"response from gs"+response.body().string());
            return JSONObject(response!!.body()!!.string())
        } catch (e: IOException) {
            Log.e(TAG, "recieving null " + e.localizedMessage)
        } catch (e: JSONException) {
            Log.e(TAG, "recieving null " + e.localizedMessage)
        }
        return null
    }

    fun updateData(id: String?, name: String?): JSONObject? {
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(WAURL + "action=update&id=" + id + "&name=" + name)
                .build()
            response = client.newCall(request).execute()
            //Log.e(TAG,"response from gs"+response.body().string());
            return JSONObject(response!!.body()!!.string())
        } catch (e: IOException) {
            Log.e(TAG, "recieving null " + e.localizedMessage)
        } catch (e: JSONException) {
            Log.e(TAG, "recieving null " + e.localizedMessage)
        }
        return null
    }

    fun readData(id: String?): JSONObject? {
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(WAURL + "action=read&id=" + id)
                .build()
            response = client.newCall(request).execute()
            // Log.e(TAG,"response from gs"+response.body().string());
            return JSONObject(response!!.body()!!.string())
        } catch (e: IOException) {
            Log.e(TAG, "recieving null " + e.localizedMessage)
        } catch (e: JSONException) {
            Log.e(TAG, "recieving null " + e.localizedMessage)
        }
        return null
    }

    fun deleteData(id: String?): JSONObject? {
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(WAURL + "action=delete&id=" + id)
                .build()
            response = client.newCall(request).execute()
            // Log.e(TAG,"response from gs"+response.body().string());
            return JSONObject(response!!.body()!!.string())
        } catch (e: IOException) {
            Log.e(TAG, "recieving null " + e.localizedMessage)
        } catch (e: JSONException) {
            Log.e(TAG, "recieving null " + e.localizedMessage)
        }
        return null
    }
}