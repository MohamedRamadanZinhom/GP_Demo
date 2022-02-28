package LocalDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[Question::class],version = 1,exportSchema = false  )
abstract class RoomDB :RoomDatabase()  {

    abstract fun questionDao(): QuestionDao

    companion object{

        @Volatile
        private var INSTANCE: RoomDB?=null

        fun getDataBase(ctx:Context): RoomDB {
            val tempinstance = INSTANCE
            if(tempinstance!=null){
                return tempinstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    RoomDB::class.java,
                    "Question_DataBase"
                ).build()
                INSTANCE =instance
                return instance
            }
        }



    }





}