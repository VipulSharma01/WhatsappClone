package com.whatsapp.whatsapppclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.whatsapp.whatsapppclone.activity.NumberActivity
import com.whatsapp.whatsapppclone.adapter.ViewPagerAdapter
import com.whatsapp.whatsapppclone.databinding.ActivityMainBinding
import com.whatsapp.whatsapppclone.fragments.CallFragment
import com.whatsapp.whatsapppclone.fragments.ChatFragment
import com.whatsapp.whatsapppclone.fragments.StatusFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentArrayList = ArrayList<Fragment>()
        fragmentArrayList.add(ChatFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null){
            startActivity(Intent(this, NumberActivity::class.java))
            finish()
        }

        val adapter= ViewPagerAdapter(this,supportFragmentManager,fragmentArrayList)

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}