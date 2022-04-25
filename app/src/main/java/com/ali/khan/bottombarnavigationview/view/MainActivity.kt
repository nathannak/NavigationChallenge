package com.ali.khan.bottombarnavigationview.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ali.khan.bottombarnavigationview.R
import androidx.databinding.DataBindingUtil
import com.ali.khan.bottombarnavigationview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.framelayout, Fragment3()).commit()
        binding.navigation.selectedItemId = R.id.menu_item3
        binding.navigation.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            if(binding.navigation.selectedItemId != item.itemId) {
                when (item.itemId) {
                    R.id.menu_item1 -> selectedFragment = Fragment1()
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
