package com.example.qoutes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN

        )
        val an = AnimationUtils.loadAnimation(this, R.anim.splashanim)
        mainTitle.startAnimation(an)
        Handler().postDelayed({
                              var intent = Intent(this,Qoutes::class.java)
                              startActivity(intent)
                              finish()
        },3000)
    }
}