package com.assignment.assessment.repository


import androidx.lifecycle.LiveData
import com.assignment.assessment.BillzApp
import com.assignment.assessment.database.BillDao
import com.assignment.assessment.database.BillzDb
import com.assignment.assessment.model.Bill
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class BillzRepository{
  val db=BillzDb.getDataBase(BillzApp.appContext)
  val billDao=db.billDao()

  suspend fun saveBill(bill: Bill){
    withContext(Dispatchers.IO){
      billDao.saveBill(bill)
    }
  }
  fun getAllBills(): LiveData<List<Bill>> {
    return billDao.getAllBills()
  }
  
}