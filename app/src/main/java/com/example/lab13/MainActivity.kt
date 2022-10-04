package com.example.lab13

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.lab13.data.DATABASE_NAME
import com.example.room.data.mark.CostType
import data.MoneyDatabase
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db: MoneyDatabase= Room.databaseBuilder(this, MoneyDatabase::class.java, DATABASE_NAME).build()
        val bookDAO = db.bookDAO()
        val executor = Executors.newSingleThreadExecutor()
        executor.execute{
            bookDAO.addType(CostType(0, "ЕГЭ"))
            bookDAO.addType(CostType(0, "Информационные технология "))
            bookDAO.addType(CostType(0, "Приключения"))
        }
        val types = bookDAO.getAllTypes()
        types.observe(this, androidx.lifecycle.Observer {
            it.forEach{
                Log.d("QWE", "{${it.id}}==={${it.title}}===")
            }
        })
    }
}