package LocalDB

import LocalDB.Question
import LocalDB.QuestionDao
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val db: Question_Database) :LocalRepository {

    override suspend fun ReadAllData()= withContext(Dispatchers.IO){
        db.Question_Dao().ReadAllData()
    }


    override suspend fun InsertOrUPdate(question: Question) {
        withContext(Dispatchers.IO){
            db.Question_Dao().InsertOrUPdate(question)
        }
    }

    override suspend fun Delete(question: Question) {
        withContext(Dispatchers.IO){
            db.Question_Dao().Delete(question)
        }
    }

    override suspend fun DeleteAll() {
        withContext(Dispatchers.IO){
            db.Question_Dao().DeleteAll()
        }
    }




}