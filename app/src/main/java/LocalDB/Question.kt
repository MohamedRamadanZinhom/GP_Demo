package LocalDB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "Questions")
class Question(

    @PrimaryKey(autoGenerate = true)
    var ID:Int,
    var QuestionString :String,
    var Disease_Id:Int


) {



    public  fun _getQuestion():String{
        return this.QuestionString
    }

    public  fun _getID():Int{
        return this.ID
    }

    public  fun _get_DiseaseID():Int{
        return this.Disease_Id
    }


    public fun _setQuestion(question:String){
        this.QuestionString=question
    }

    public fun _setDiseaseId(id:Int){
        this.Disease_Id=id
    }



}