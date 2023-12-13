package com.infinite.massiveprojectmobilezwh.ui
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.infinite.massiveprojectmobilezwh.R
import com.infinite.massiveprojectmobilezwh.Retro
import com.infinite.massiveprojectmobilezwh.UserApi
import com.infinite.massiveprojectmobilezwh.UserRequest
import com.infinite.massiveprojectmobilezwh.UserResponse
import com.infinite.massiveprojectmobilezwh.beranda.BerandaListActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MasukActivity : AppCompatActivity() {

    private var isPasswordVisible = false
    private var user: UserResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        // Pengaturan OnClickListener untuk teks "Daftar"
        val btnRegis = findViewById<TextView>(R.id.tv_to_register)
        btnRegis.setOnClickListener {
            Intent(this, DaftarActivity::class.java).also {
                startActivity(it)
            }
        }

        // Mengaktifkan fungsi hide dan unhide password
        val editTextPassword = findViewById<EditText>(R.id.et_password)
        val imageViewToggle = findViewById<ImageView>(R.id.ic_password)
        imageViewToggle.setOnClickListener {
            // Saat ImageView diklik, ubah tipe input teks
            isPasswordVisible = !isPasswordVisible
            togglePasswordVisibility(editTextPassword, isPasswordVisible)
        }

        initAction()
    }

    // Function untuk fitur hide/unhide password
    private fun togglePasswordVisibility(editText: EditText, isVisible: Boolean) {
        if (isVisible) {
            // Jika terlihat, ubah ke tipe teks biasa
            editText.transformationMethod = null
            editText.setSelection(editText.text.length) // Agar kursor tetap di akhir teks
        } else {
            // Jika tidak terlihat, ubah ke tipe password
            editText.transformationMethod = PasswordTransformationMethod()
            editText.setSelection(editText.text.length) // Agar kursor tetap di akhir teks
        }
    }

    fun initAction() {
        val btnMasuk: Button = findViewById(R.id.bt_confirmation)
        btnMasuk.setOnClickListener {
            login()
        }
    }

    fun login() {
        val etEmail: EditText = findViewById(R.id.et_phone)
        val etPassword: EditText = findViewById(R.id.et_password)
        val request = UserRequest()
        request.email = etEmail.text.toString().trim()
        request.password = etPassword.text.toString().trim()

        val retro = Retro().getRetroCLientInstance().create(UserApi::class.java)
        retro.signin(request).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    user = response.body()
                    val accessToken = user?.access_token
                    accessToken?.let { saveBearerTokenToSharedPreferences(it) }
                    accessToken?.let { requestUserData(it) }
                } else {
                    Log.e("Error", "Unsuccessful response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Error", t.message ?: "Unknown error")
            }
        })
    }

    private fun requestUserData(bearerToken: String) {
        val retro = Retro().getRetroCLientInstance().create(UserApi::class.java)
        val authorizationHeader = "Bearer $bearerToken"

        retro.user(authorizationHeader).enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    val userProfile = response.body()
                    if (userProfile != null) {
                        val username = userProfile.user?.name
                        toHome(username)
                    } else {
                        Log.e("Error", "No Username")
                    }
                } else {
                    Log.e("Error", "Unsuccessful response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Error", t.message ?: "Unknown error")
            }
        })
    }

    private fun toHome(username: String?) {
        Log.d("UserToHome", "User: $username")
        val intent = Intent(this, BerandaListActivity::class.java)
        username.let {
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }
    }
    private fun saveBearerTokenToSharedPreferences(token: String) {
        val sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("bearerToken", token)
        editor.apply()
    }
}