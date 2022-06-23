package LocalDB

import LocalDB.Question
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionDao {

    @Query(" SELECT * FROM Questions ORDER BY ID DESC ")
    suspend fun ReadAllData():List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertOrUPdate(question: Question)

    @Delete
    suspend fun Delete(question:Question)

    @Query("Delete From Questions ")
    suspend fun DeleteAll()

}