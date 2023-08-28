package com.assignment.assessment.database
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//
//import com.assignment.assessment.model.Bill
//
//
//@Dao
//interface BillDao {
//  @Insert(onConflict = OnConflictStrategy.REPLACE)
//  fun insertBill(bill: Bill)
//}

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.assessment.model.Bill
@Dao
interface BillDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun saveBill(bill: Bill)

  @Query("SELECT * FROM Bills")
  fun getAllBills(): LiveData<List<Bill>>
}