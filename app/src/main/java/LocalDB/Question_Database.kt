package LocalDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Question::class], version = 1, exportSchema = false)
abstract class Question_Database : RoomDatabase() {

    abstract fun Question_Dao():QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: Question_Database?= null

        fun getAppDatabase(context: Context): Question_Database {
            return INSTANCE?: synchronized(Any()){
                INSTANCE?:buildDatabase(context).also{ INSTANCE  =it}
            }
        }

        private fun buildDatabase(context: Context): Question_Database {

            return Room.databaseBuilder(context.applicationContext
                ,Question_Database::class.java,
                "Question_database").build()

        }


    }


}