package com.example.lab13

import android.os.Bundle
import android.util.Log
import android.R
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.lab13.data.DATABASE_NAME
import com.example.lab13.databinding.ActivityMainBinding
import com.example.room.data.mark.CostType
import data.MoneyDatabase
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var costTypes: MutableList<CostType> = mutableListOf()
    private var titleString: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)





        var db: MoneyDatabase= Room.databaseBuilder(this, MoneyDatabase::class.java, DATABASE_NAME).build()
        val bookDAO = db.bookDAO()
        val executor = Executors.newSingleThreadExecutor()
        val types = bookDAO.getAllTypes()


        var spinnerAdapter: ArrayAdapter<String>

        types.observe(this) { it ->
            costTypes.addAll(it)
            titleString.clear()
            it.forEach {
                titleString.add(it.stule)
            }

            spinnerAdapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, titleString)
            // spinnerAdapter.setDropDownViewResource(R.layout.dropdown_spinner_item)
            binding.spinner.adapter = spinnerAdapter

        }
        types.observe(this, Observer {
            it.forEach{
                Log.d("QWE", "{${it.id}}==={${it.stule}}===")
            }
        })

        binding.button.setOnClickListener{
            if (!binding.editTextTextPerson.text.isEmpty()) {
                executor.execute {
                    bookDAO.addType(CostType(0,binding.spinner.toString(), binding.editTextTextPerson.text.toString(),binding.editTextTextPersonIndex.text.toString(),binding.editTextNumberPageCount.text.toString().toInt()))
                }
                val types = bookDAO.getAllTypes()
                types.observe(this) { it ->
                    titleString.clear()
                    it.forEach {
                        titleString.add(it.stule)
                    }
                }
                binding.editTextTextPerson.text.clear()
            } else {
                Toast.makeText(this,"Данные введены некорректно", Toast.LENGTH_SHORT).show()
            }
        }
    }
}