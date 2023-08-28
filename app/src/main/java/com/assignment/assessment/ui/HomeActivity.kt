package com.assignment.assessment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.assessment.R
import com.assignment.assessment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setUpBottomNav()
    }


    fun setUpBottomNav(){
        binding.bnvHome.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.summary->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, SummaryFragment())
                        .commit()
                    true


                }
                R.id.upcoming->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, UpComingBillsFragment())
                        .commit()
                    true

                }
                R.id.paid->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome, PaidBillsFragment())
                        .commit()
                    true

                }
                R.id.settings->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, SettingsBillsFragment())
                        .commit()
                    true

                }
                else->{
                    false

                }
            }


        }

    }
}