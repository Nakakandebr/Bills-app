package com.assignment.assessment.ui
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.ArrayAdapter
//import androidx.activity.viewModels
//import com.assignment.assessment.R
//import com.assignment.assessment.databinding.ActivityAddBillBinding
//import com.assignment.assessment.viewmodel.BillsViewModel
//import java.util.UUID
//
//class AddBillActivity : AppCompatActivity() {
//    lateinit var binding :ActivityAddBillBinding
//    val billsViewModel :BillsViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding= ActivityAddBillBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        setUpFrequencySpinner()
//        setUpDueDateSpinner()
//    }
//    fun setUpFrequencySpinner(){
//        val adapter = ArrayAdapter
//            .createFromResource(this, R.array.frequencies,android.R.layout.simple_spinner_item)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spFrequency.adapter = adapter
//    }
//    fun setUpDueDateSpinner(){
//        val weeklyAdapter = ArrayAdapter(this , android.R.layout.simple_spinner_item , arrayOf(1,2,3,4,5,6,7))
//        weeklyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spDueDate.adapter = weeklyAdapter
//
//        val billId = UUID.randomUUID().toString()
//    }
//
//}

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.assignment.assessment.R
import com.assignment.assessment.databinding.ActivityAddBillBinding
import com.assignment.assessment.model.Bill
import com.assignment.assessment.viewmodel.BillsViewModel
import java.util.UUID
class AddBillActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBillBinding
    val billsViewModel: BillsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFreqSpinner()
        setupDueDateSpinner()
        binding.btnsavebill.setOnClickListener {
            val selectedFrequency = binding.spFrequency.selectedItem.toString()
            val selectedDueDate = binding.spDueDate.selectedItem.toString()
            val billName = binding.etName.text.toString()
            val billAmount = binding.etAmount.text.toString().toDouble()
            val bill = Bill(
                billId = UUID.randomUUID().toString(),
                name = billName,
                amount = billAmount,
                frequency = selectedFrequency,
                durDate = selectedDueDate,
                userId = "USER_ID"
            )
            // Save the bill to your database using your ViewModel and Repository
            billsViewModel.saveBill(bill)
            finish()
            // Navigate to the SummaryFragment
            navigateToSummaryFragment()
        }
    }
    private fun setupFreqSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.frequencies, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spFrequency.adapter = adapter
    }
    private fun setupDueDateSpinner() {
        binding.spFrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedFrequency = binding.spFrequency.selectedItem.toString()
                val dueDateAdapter = when (selectedFrequency) {
                    "Monthly" -> {
                        val daysInMonth = 1..31
                        ArrayAdapter(this@AddBillActivity, android.R.layout.simple_spinner_item, daysInMonth.toList())
                    }
                    "Quarterly" -> {
                        val daysInQuarter = 1..90
                        ArrayAdapter(this@AddBillActivity, android.R.layout.simple_spinner_item, daysInQuarter.toList())
                    }
                    "Annual" -> {
                        val daysInYear = 1..365
                        ArrayAdapter(this@AddBillActivity, android.R.layout.simple_spinner_item, daysInYear.toList())
                    }
                    else -> {
                        ArrayAdapter(this@AddBillActivity, android.R.layout.simple_spinner_item, arrayOf(1, 2, 3, 4, 5, 6, 7))
                    }
                }
                dueDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spDueDate.adapter = dueDateAdapter
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }
    private fun navigateToSummaryFragment() {
        val fragment = SummaryFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fcvHome, fragment) // Replace "fragment_container" with your container ID
        transaction.addToBackStack(null)
        transaction.commit()
    }
}