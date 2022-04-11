package com.ali.khan.bottombarnavigationview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ali.khan.bottombarnavigationview.R
import com.ali.khan.bottombarnavigationview.viewmodel.ProductsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.navigation)

        supportFragmentManager.beginTransaction().replace(R.id.framelayout, Fragment3()).commit()
        bottomNavigationView.selectedItemId = R.id.menu_item1

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            if(bottomNavigationView.selectedItemId != item.itemId) {
                when (item.itemId) {
                    R.id.menu_item1 -> selectedFragment = Fragment1()
//                    R.id.menu_item2 -> selectedFragment = Fragment2() // no offers for now
                    R.id.menu_item3 -> selectedFragment = Fragment3()
                    R.id.menu_item4 -> selectedFragment = Fragment4()
                }
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.framelayout, selectedFragment!!)
                transaction.commit()
            }
            true
        }
    }
}
