package LocalDB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionViewModel(application : Application ):AndroidViewModel(application) {

    private val read_all_data :LiveData<List<Question>>
    private val repository: Repository

    init {

        val questiondao= RoomDB.getDataBase(application).questionDao()
        repository= Repository(questiondao)
        read_all_data=repository.readAllData


    }
    fun addquestion(question: Question){
        viewModelScope.launch(Dispatchers.IO){
            repository.AddQuestion(question)
        }
    }

}