package com.example.room.data.mark

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.lab13.data.COSTS_TABLE
import com.example.lab13.data.TYPES_TABLE
import java.sql.Date

@Entity(tableName = TYPES_TABLE)
data class CostType (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val id: Int,
    var stule: String,
    var aurhors :String,
    var index: String,
    var count: Int

)
