package LocalDB

import LocalDB.Question
import LocalDB.QuestionDao
import androidx.lifecycle.LiveData

class Repository(private val questiondao: QuestionDao) {
    val readAllData:LiveData<List<Question>> = questiondao.ReadAll()

   suspend fun AddQuestion(question: Question){
        questiondao.AddQuestion(question)
    }


}