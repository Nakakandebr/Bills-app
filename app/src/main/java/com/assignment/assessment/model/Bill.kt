package com.assignment.assessment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Bills")

data class Bill(
    @PrimaryKey var billId :String,
    //uui universal unique id
    var name: String,
    var amount : Double,
    var frequency: String,
    var durDate : String,
    var userId : String


)
