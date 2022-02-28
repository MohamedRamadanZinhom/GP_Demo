package LocalDB

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "Questions")
class Question(

    @PrimaryKey(autoGenerate = true)
    var ID:Int,
    var QuestionString :String,
    var Answer:Boolean


) {

    public  fun _getAnswer():Boolean{
        return this.Answer
    }

    public  fun _getQuestion():String{
        return this.QuestionString
    }

    public  fun _getID():Int{
        return this.ID
    }

    public fun _setAnswer(answer:Boolean){
        this.Answer=answer
    }

    public fun _setQuestion(question:String){
        this.QuestionString=question
    }



}