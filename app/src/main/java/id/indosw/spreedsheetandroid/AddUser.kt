@file:Suppress("DEPRECATION", "PrivatePropertyName")

package id.indosw.spreedsheetandroid

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

@Suppress("SameParameterValue")
class AddUser : AppCompatActivity(), View.OnClickListener {
    private var editTextUserName: EditText? = null
    private var editTextUserId: EditText? = null
    private var imageViewUserImage: ImageView? = null
    private var buttonAddUser: Button? = null
    private var buttonAddImage: Button? = null
    var userImage: String? = null
    private val PICK_IMAGE_REQUEST = 1
    private var rBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user)
        editTextUserId = findViewById(R.id.et_uid)
        editTextUserName = findViewById(R.id.et_uname)
        imageViewUserImage = findViewById(R.id.iv_uphoto)
        buttonAddUser = findViewById(R.id.btn_add_user)
        buttonAddImage = findViewById(R.id.btn_image)
        buttonAddImage!!.setOnClickListener(this)
        buttonAddUser!!.setOnClickListener(this)
    }

    private fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap {
        var width = image.width
        var height = image.height
        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    private fun getStringImage(bmp: Bitmap?): String {
        val baos = ByteArrayOutputStream()
        bmp!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    private fun addUser() {
        val loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false)
        val userId = editTextUserId!!.text.toString().trim { it <= ' ' }
        val userName = editTextUserName!!.text.toString().trim { it <= ' ' }
        //Bitmap  rbitmap = getResizedBitmap(bitmap,500);
        Log.e("null", "values$userImage")
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, Configuration.ADD_USER_URL,
            Response.Listener { response: String? ->
                loading.dismiss()
                Toast.makeText(this@AddUser, response, Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error: VolleyError ->
                Toast.makeText(
                    this@AddUser,
                    error.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params[Configuration.KEY_ACTION] = "insert"
                params[Configuration.KEY_ID] = userId
                params[Configuration.KEY_NAME] = userName
                params[Configuration.KEY_IMAGE] = userImage!!
                return params
            }
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

    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val filePath = data.data
            try {
                //Getting the Bitmap from Gallery
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                rBitmap = getResizedBitmap(bitmap, 250) //Setting the Bitmap to ImageView
                userImage = getStringImage(rBitmap)
                imageViewUserImage!!.setImageBitmap(rBitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onClick(v: View) {
        if (v === buttonAddUser) {
            addUser()
        }
        if (v === buttonAddImage) {
            showFileChooser()
        }
    }
}