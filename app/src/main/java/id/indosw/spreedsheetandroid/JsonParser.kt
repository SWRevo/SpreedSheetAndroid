package id.indosw.spreedsheetandroid

import org.json.JSONException
import org.json.JSONObject

class JsonParser(private val json: String) {
    fun parseJSON() {
        val jsonObject: JSONObject
        try {
            jsonObject = JSONObject(json)
            val users = jsonObject.getJSONArray(Configuration.KEY_USERS)
            uIds = arrayOfNulls(users.length())
            uNames = arrayOfNulls(users.length())
            uImages = arrayOfNulls(users.length())
            for (i in 0 until users.length()) {
                val jo = users.getJSONObject(i)
                uIds[i] = jo.getString(Configuration.KEY_ID)
                uNames[i] = jo.getString(Configuration.KEY_NAME)
                uImages[i] = jo.getString(Configuration.KEY_IMAGE)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    companion object {
        lateinit var uIds: Array<String?>
        lateinit var uNames: Array<String?>
        lateinit var uImages: Array<String?>
    }
}