package com.example.sharebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.sharebook.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.backToLoginButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegist.setOnClickListener{
            val email = binding.textEmailAddress.text.toString()
            val username = binding.textFullNameField.text.toString()
            val password = binding.textFieldPassword.text.toString()
            val confpassword = binding.textConfirmationPassword.text.toString()

            //Validasi Email kosong
            if(email.isEmpty()){
                binding.textEmailAddress.error = "Email Harus Diisi"
                binding.textEmailAddress.requestFocus()
                return@setOnClickListener
            }
            //Validasi Username
            if(username.isEmpty()){
                binding.textFullNameField.error = "Nama Harus Diisi"
                binding.textFullNameField.requestFocus()
                return@setOnClickListener
            }
            //Validasi Email tidak sesuai
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.textEmailAddress.error = "Email Tidak Valid"
                binding.textEmailAddress.requestFocus()
                return@setOnClickListener
            }
            //Validasi Password
            if(password.isEmpty()){
                binding.textFieldPassword.error = "Password Harus Diisi"
                binding.textFieldPassword.requestFocus()
                return@setOnClickListener
            }
            //Validasi panjang password
            if(password.length < 8){
                binding.textFieldPassword.error = "Password Harus Minimal 8 Karakter"
                binding.textFieldPassword.requestFocus()
                return@setOnClickListener
            }
            //Validasi confirmasi password
            if(password != confpassword){
                binding.textConfirmationPassword.error = "Password Tidak Sesuai!"
                binding.textConfirmationPassword.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email,password)
        }
    }

    private fun RegisterFirebase(email : String,password : String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Register Berhasil",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
    }
}

