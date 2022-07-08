package com.whatsapp.whatsapppclone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.whatsapp.whatsapppclone.MainActivity
import com.whatsapp.whatsapppclone.databinding.ActivityNumberBinding

class NumberActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNumberBinding
    private lateinit var auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.button.setOnClickListener {

            if (binding.phoneNumber.text!!.isEmpty()){
                Toast.makeText(this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show()
            }
            else{
                val  intent =Intent(this, OtpActivity::class.java)
                intent.putExtra("number",binding.phoneNumber.text!!.toString())
                startActivity(intent)
            }
        }
    }
}