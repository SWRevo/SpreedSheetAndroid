package id.indosw.spreedsheetandroid.crud

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi

object InternetConnection {
    /** CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT  */
    @RequiresApi(Build.VERSION_CODES.M)
    fun checkConnection(context: Context): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork !=null
        //activeNetworkInfo != null
    }
}