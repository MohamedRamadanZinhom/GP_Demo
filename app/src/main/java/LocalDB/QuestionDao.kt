package LocalDB

import LocalDB.Question
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionDao {



    @Insert
    fun AddQuestion(question: Question)
    @Update
    fun Update(question: Question)
    @Delete
    fun Delete(question: Question)
    @Query("Delete From Questions")
    fun DeleteAll()

    @Query("Select * From user")
    fun  ReadAll( ): LiveData<List<Question>>

    @Query("Select * From Questions where ID=id")
    fun getQuestion(id:Int)







}