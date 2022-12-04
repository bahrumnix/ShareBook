package com.example.projectShareBook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.projectShareBook.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {

    val db = Firebase.firestore

    lateinit var binding: ActivityRegisterBinding
    lateinit var auth : FirebaseAuth
    val user = Firebase.auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
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
            val fullname = binding.textFullNameField.text.toString()
            val password = binding.textFieldPassword.text.toString()
            val confpassword = binding.textConfirmationPassword.text.toString()

            //Validasi Email kosong
            if(email.isEmpty()){
                binding.textEmailAddress.error = "Email Harus Diisi"
                binding.textEmailAddress.requestFocus()
                return@setOnClickListener
            }
            //Validasi Username
            if(fullname.isEmpty()){
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
            RegisterAkun(fullname, email,password,null,null,null,null,null)
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

    private fun RegisterAkun(fullname : String, email : String,password: String,
                             alamat : String?, kelamin :String?, nomorHP : String?,
                             status : String?, fotoProfile : String?){
        val user = hashMapOf(
            "fullname" to fullname,
            "email" to email,
            "password" to password,
            "alamat" to alamat,
            "kelamin" to kelamin,
            "nomorHP" to nomorHP,
            "status" to status,
            "fotoProfile" to fotoProfile
        )
        db.collection("profile")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("profile", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("profile", "Error adding document", e)
            }
    }
}

