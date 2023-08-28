package com.assignment.assessment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.assessment.model.Bill
import com.assignment.assessment.repository.BillzRepository
import kotlinx.coroutines.launch

class BillsViewModel:ViewModel() {
    val billsRepo =BillzRepository()

            fun saveBill(bill: Bill){
                viewModelScope.launch {
//                    launching a coroutine
                    billsRepo.saveBill(bill)
                }
            }

 fun getAllBills():LiveData<List<Bill>>{
     return billsRepo.getAllBills()
 }
}