package com.example.mysample.ui.homeactivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysample.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeActivityFragment.newInstance())
                .commitNow()
        }
    }

}
