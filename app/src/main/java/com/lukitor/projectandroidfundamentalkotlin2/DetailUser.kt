package com.lukitor.projectandroidfundamentalkotlin2

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lukitor.projectandroidfundamentalkotlin2.databinding.ActivityDetailUserBinding

class DetailUser : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.progressBar2.visibility = View.INVISIBLE
        var fragment: Fragment
        val dataobj = intent.getParcelableExtra<User>("data") as User
        binding.txtDusername.text = dataobj.username
        binding.txtDusername.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.txtDusername.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(dataobj.repository)
            startActivity(intent)
        }
        binding.txtDName.text = dataobj.nama
        binding.txtDusername.text = dataobj.username
        binding.txtDLocation.text = dataobj.location
        binding.txtDcompany.text = dataobj.company
        Glide.with(this).load(dataobj.foto).into(binding.imgDProfile)
        Glide.with(this).load(dataobj.foto).into(binding.imageViewDetail)
        binding.imgback.setOnClickListener{ view ->finish()}
        val user: String = dataobj.username.toString()
        binding.NavView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.menu_follower -> {
                    fragment = Follower.newInstance(user)
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_following -> {
                    fragment = Following.newInstance(user)
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
        if (savedInstanceState == null) {
            binding.NavView.selectedItemId = R.id.menu_following
        }
    }
}