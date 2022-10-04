package data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lab13.data.BookDAO
import com.example.lab13.data.convert.convert
import com.example.lab13.data.mark.Cost
import com.example.room.data.mark.CostType

@Database(entities = [CostType::class, Cost::class], version = 1)
@TypeConverters(convert::class)
abstract class MoneyDatabase: RoomDatabase() {

    abstract fun bookDAO(): BookDAO

}