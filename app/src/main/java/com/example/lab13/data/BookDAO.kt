package com.example.lab13.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lab13.data.mark.Cost
import com.example.room.data.mark.CostType
//import data.CostType

@Dao
interface BookDAO {
    @Query("SELECT * FROM $TYPES_TABLE")
    fun getAllTypes(): LiveData<List<CostType>>

    @Insert
    fun addType(costType: CostType)
    @Update
    fun saveType(costType: CostType)
    @Delete
    fun KillType(costType: CostType)

    @Query("SELECT * FROM $COSTS_TABLE")
    fun getAllCosts(): List<Cost>
   @Query("SELECT * FROM $COSTS_TABLE WHERE _id =:id")
    fun getCost(id:Int): Cost
    @Insert
    fun addCost(cost: Cost)
    @Update
    fun saveCost(cost: Cost)
    @Delete
    fun KillCost(cost: Cost)


}