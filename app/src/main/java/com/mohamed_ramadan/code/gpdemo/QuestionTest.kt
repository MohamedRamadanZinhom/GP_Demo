package com.mohamed_ramadan.code.gpdemo

import LocalDB.Question
import LocalDB.Question_Database
import LocalDB.Repository
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.room.Delete
import androidx.room.Update
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mohamed_ramadan.code.gpdemo.UI.InputFragment
import com.mohamed_ramadan.code.gpdemo.UI.Start_Check
import com.mohamed_ramadan.code.gpdemo.UI.YesNoFragment
import kotlinx.android.synthetic.main.fragment_input.*
import kotlinx.android.synthetic.main.fragment_question_test.*
import kotlinx.android.synthetic.main.fragment_question_test.view.*
import kotlinx.android.synthetic.main.fragment_yes_no.*
import kotlinx.coroutines.*
import org.json.JSONObject
import kotlin.concurrent.thread


class QuestionTest : Fragment() {




    companion object {

        var UserAnswer:String="1"

    }

    //API Variable
    var dialog: ProgressDialog? = null
    var volleyRequestQueue: RequestQueue? = null
    val serverAPIURL: String = "https://heart-deteact-fci.herokuapp.com/predict"



    //APP Variable

    var FIndex:Int=1



    lateinit var fragmentlist: Array<Fragment>
    lateinit var Repo: Repository
    lateinit var all_Question:ArrayList<Question>
    lateinit var list: List<Question>
    lateinit var all_Answer:ArrayList<String>
    var Position:Int=-1
    lateinit var button:Button
    lateinit var Previousbtn:Button

    private var IS_Backed:Boolean=false
    private lateinit var APIResult:String



    //


    lateinit var q1:TextView
    lateinit var q2:TextView
    lateinit var q3:TextView
    lateinit var q4:TextView
    lateinit var q5:TextView
    lateinit var q6:TextView
    lateinit var q7:TextView
    lateinit var q8:TextView
    lateinit var q9:TextView
    lateinit var q10:TextView
    lateinit var Q11:TextView
    lateinit var Q12:TextView
    lateinit var Q13:TextView
    lateinit var Q14:TextView
    lateinit var Q15:TextView
    lateinit var Q16:TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        ActionBar.DISPLAY_HOME_AS_UP
        UserAnswer=""

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_question_test, container, false)

        //Initialize Questions Fragments

        //Back Button Pressed is False
        IS_Backed=false


        //DECLARATION

 /*       val q1=view.findViewById<TextView>(R.id.Question_1)
        val q2=view.findViewById<TextView>(R.id.Question_2)
        val q3=view.findViewById<TextView>(R.id.Question_3)
        val q4=view.findViewById<TextView>(R.id.Question_4)
        val q5=view.findViewById<TextView>(R.id.Question_5)
        val q6=view.findViewById<TextView>(R.id.Question_6)
        val q7=view.findViewById<TextView>(R.id.Question_7)
        val q8=view.findViewById<TextView>(R.id.Question_8)
        val q9=view.findViewById<TextView>(R.id.Question_9)
        val q10=view.findViewById<TextView>(R.id.Question_10)
        val Q11=view.findViewById<TextView>(R.id.Question_11)
        val Q12=view.findViewById<TextView>(R.id.Question_12)
        val Q13=view.findViewById<TextView>(R.id.Question_13)
        val Q14=view.findViewById<TextView>(R.id.Question_14)
        val Q15=view.findViewById<TextView>(R.id.Question_15)
        val Q16=view.findViewById<TextView>(R.id.Question_16)*/

        q1=view.findViewById<TextView>(R.id.Question_1)
        q2=view.findViewById<TextView>(R.id.Question_2)
        q3=view.findViewById<TextView>(R.id.Question_3)
        q4=view.findViewById<TextView>(R.id.Question_4)
        q5=view.findViewById<TextView>(R.id.Question_5)
        q6=view.findViewById<TextView>(R.id.Question_6)
        q7=view.findViewById<TextView>(R.id.Question_7)
        q8=view.findViewById<TextView>(R.id.Question_8)
        q9=view.findViewById<TextView>(R.id.Question_9)
        q10=view.findViewById<TextView>(R.id.Question_10)
        Q11=view.findViewById<TextView>(R.id.Question_11)
        Q12=view.findViewById<TextView>(R.id.Question_12)
        Q13=view.findViewById<TextView>(R.id.Question_13)
        Q14=view.findViewById<TextView>(R.id.Question_14)
        Q15=view.findViewById<TextView>(R.id.Question_15)
        Q16=view.findViewById<TextView>(R.id.Question_16)



        all_Question= ArrayList<Question>()
        all_Answer= ArrayList()


        list= emptyList()
        APIResult="0"

        //GreateQuestion() //FOR CREATE LIST OF QUESTION USED FOR ONE TIME

        //PREPARE THE DATABASE
        val db =Question_Database.getAppDatabase(this.requireActivity())
        Repo= Repository(db)

        //GET QUESTIONS FROM DATABASE
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                Get_All_Question()

            }
        }








        val btn =view.findViewById<Button>(R.id.submitbtn)

        btn.setOnClickListener{
            Run()
        }




        return view
    }





    private fun FillQuestionFields(){

        val q1=this.requireView().findViewById<TextView>(R.id.Question_1)
        q1.setText(all_Question[0].toString())
        val q2=this.requireView().findViewById<TextView>(R.id.Question_2)
        q2.setText(all_Question[1].toString())
        val q3=this.requireView().findViewById<TextView>(R.id.Question_3)
        q3.setText(all_Question[2].toString())
        val q4=this.requireView().findViewById<TextView>(R.id.Question_4)
        q4.setText(all_Question[3].toString())
        val q5=this.requireView().findViewById<TextView>(R.id.Question_5)
        q5.setText(all_Question[4].toString())
        val q6=this.requireView().findViewById<TextView>(R.id.Question_6)
        q6.setText(all_Question[5].toString())
        val q7=this.requireView().findViewById<TextView>(R.id.Question_7)
        q7.setText(all_Question[6].toString())
        val q8=this.requireView().findViewById<TextView>(R.id.Question_8)
        q8.setText(all_Question[7].toString())
        val q9=this.requireView().findViewById<TextView>(R.id.Question_9)
        q9.setText(all_Question[8].toString())
        val q10=this.requireView().findViewById<TextView>(R.id.Question_10)
        q10.setText(all_Question[9].toString())
        val Q11=this.requireView().findViewById<TextView>(R.id.Question_11)
        Q11.setText(all_Question[10].toString())
        val Q12=this.requireView().findViewById<TextView>(R.id.Question_12)
        Q12.setText(all_Question[11].toString())
        val Q13=this.requireView().findViewById<TextView>(R.id.Question_13)
        Q13.setText(all_Question[12].toString())
        val Q14=this.requireView().findViewById<TextView>(R.id.Question_14)
        Q14.setText(all_Question[13].toString())
        val Q15=this.requireView().findViewById<TextView>(R.id.Question_15)
        Q15.setText(all_Question[14].toString())
        val Q16=this.requireView().findViewById<TextView>(R.id.Question_16)
        Q16.setText(all_Question[15].toString())


    }


    private fun Run(){

        for (i in 1..16){
            all_Answer.add("0")
        }

        if(Check()){

            all_Answer[0]=Gr1().toString()
            all_Answer[1]=Gr2().toString()
            all_Answer[2]=Gr3().toString()
            all_Answer[3]=Gr4().toString()
            all_Answer[4]=Gr5().toString()
            all_Answer[5]=Gr6().toString()
            all_Answer[6]=Gr7().toString()
            all_Answer[7]=Gr8().toString()
            all_Answer[8]=Gr9().toString()


            all_Answer[9]=Gr_10_Q10.text.toString()
            all_Answer[10]=Gr_11_Q11.text.toString()
            all_Answer[11]=Gr_12_Q12.text.toString()
            all_Answer[12]=AgeCategory(Gr_13_Q13.text.toString().toInt()).toString()
            all_Answer[13]=GeneralHealthValue( Gr_14_Q14.text.toString().toInt()).toString()
            all_Answer[14]=Gr_15_Q15.text.toString()

            all_Answer[15]=Gr16().toString()



            Submit()


        }
    }

    private fun Gr1():Int{

        val id=GR_1_Q1.checkedRadioButtonId

        val rbtn=this.activity?.findViewById<RadioButton>(id)

        if (rbtn?.text=="Yes"){return 1}
        else {return 0}

    }

    private fun Gr2():Int{

        val id=GR_2_Q2.checkedRadioButtonId

        val rbtn=this.activity?.findViewById<RadioButton>(id)

        if (rbtn?.text=="Yes"){return 1}
        else {return 0}

    }

    private fun Gr3():Int{

        val id=GR_3_Q3.checkedRadioButtonId

        val rbtn=this.activity?.findViewById<RadioButton>(id)

        if (rbtn?.text=="Yes"){return 1}
        else {return 0}

    }

    private fun Gr4():Int{

        val id=GR_4_Q4.checkedRadioButtonId

        val rbtn=this.activity?.findViewById<RadioButton>(id)

        if (rbtn?.text=="Yes"){return 1}
        else {return 0}

    }

    private fun Gr5():Int{

        val id=GR_5_Q5.checkedRadioButtonId

        val rbtn=this.activity?.findViewById<RadioButton>(id)

        if (rbtn?.text=="Yes"){return 1}
        else {return 0}

    }

    private fun Gr6():Int{

        val id=GR_6_Q6.checkedRadioButtonId

        val rbtn=this.activity?.findViewById<RadioButton>(id)

        if (rbtn?.text=="Yes"){return 1}
        else {return 0}

    }

    private fun Gr7():Int{

        val id=GR_7_Q7.checkedRadioButtonId

        val rbtn=this.activity?.findViewById<RadioButton>(id)

        if (rbtn?.text=="Yes"){return 1}
        else {return 0}

    }

    private fun Gr8():Int{

        val id=GR_8_Q8.checkedRadioButtonId

        val rbtn=this.activity?.findViewById<RadioButton>(id)

        if (rbtn?.text=="Yes"){return 1}
        else {return 0}

    }

    private fun Gr9():Int{

        val id=GR_9_Q9.checkedRadioButtonId

        val rbtn=this.activity?.findViewById<RadioButton>(id)

        if (rbtn?.text=="Yes"){return 1}
        else {return 0}

    }





    private fun Gr16():Int{

        val id=GR_16_Q16.checkedRadioButtonId

        val rbtn=this.activity?.findViewById<RadioButton>(id)

        if (rbtn?.text=="Male"){return 1}
        else {return 0}

    }

    private fun Check():Boolean{


        return ! Gr_10_Q10.text.toString().isEmpty() && !Gr_10_Q10.text.toString().isBlank()
        return ! Gr_11_Q11.text.toString().isBlank() && !Gr_11_Q11.text.toString().isEmpty()
        return ! Gr_12_Q12.text.toString().isEmpty() && !Gr_12_Q12.text.toString().isBlank()
        return ! Gr_13_Q13.text.toString().isBlank() && !Gr_13_Q13.text.toString().isEmpty()
        return ! Gr_14_Q14.text.toString().isEmpty() && !Gr_14_Q14.text.toString().isBlank()
        return !Gr_15_Q15.text.toString().isBlank() && ! Gr_15_Q15.text.toString().isEmpty()


    }











    private fun Submit(){

        val Answerd_List =PreparTheAnwser(all_Answer)

        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                SendSignUpDataToServer(Answerd_List)
            }

        }



        //Navigation.findNavController(this.requireView()).navigate(R.id.action_questionTest_to_result)
    }



    private fun RunTest(view: View){
        when(Position) {

            13->{
                val value=UserAnswer
                UserAnswer=GeneralHealthValue(value.toInt()).toString() //General Health case

                AddAnswer()
                //Toast.makeText(this.requireContext(),"general health  = ${all_Answer[Position]} ",Toast.LENGTH_SHORT).show()
                button.setText("Next")
            }
            12->{
                val value=UserAnswer
                UserAnswer=AgeCategory(value.toInt()).toString()   // age category case

                AddAnswer()
               // Toast.makeText(this.requireContext(),"Age category  = ${all_Answer[Position]} ",Toast.LENGTH_SHORT).show()
                button.setText("Next")
            }
            15->{

                AddAnswer()
                button.setText("Predict")
            }
            else -> { // Note the block
                AddAnswer()
                button.setText("Next")
            }

        }


    }

    private fun RunBackTest (view: View){


        when(Position) {

            13->{
                val value=user_ans.text.toString()
                UserAnswer=GeneralHealthValue(value.toInt()).toString() //General Health case
               // Toast.makeText(this.requireContext(),"general health v = $v ",Toast.LENGTH_SHORT).show()
                UpdateAnswer()
                button.setText("Next")
            }
            12->{
                val value=user_ans.text.toString()
                UserAnswer=AgeCategory(value.toInt()).toString()  // age category case
                //Toast.makeText(this.requireContext(),"Age category v = $v ",Toast.LENGTH_SHORT).show()
                UpdateAnswer()
                button.setText("Next")
            }
            15->{

                UpdateAnswer()
                button.setText("Predict")
            }
            else -> { // Note the block

                UpdateAnswer()
                button.setText("Next")
            }

        }


    }








    //Get Question From DataBase
    private fun Get_All_Question(){

        GlobalScope.launch(Dispatchers.IO) {
             val l=async {  Repo.ReadAllData() }

            withContext(Dispatchers.Main  ){
                list=  l.await()



                q1.setText(l.await()[0].QuestionString.toString())
                q2.setText(l.await()[1].QuestionString.toString())
                q3.setText(l.await()[2].QuestionString.toString())
                q4.setText(l.await()[3].QuestionString.toString())
                q5.setText(l.await()[4].QuestionString.toString())
                q6.setText(l.await()[5].QuestionString.toString())
                q7.setText(l.await()[6].QuestionString.toString())
                q8.setText(l.await()[7].QuestionString.toString())
                q9.setText(l.await()[8].QuestionString.toString())
                q10.setText(l.await()[9].QuestionString.toString())
                Q11.setText(l.await()[10].QuestionString.toString())
                Q12.setText(l.await()[11].QuestionString.toString())
                Q13.setText(l.await()[12].QuestionString.toString())
                Q14.setText(l.await()[13].QuestionString.toString())
                Q15.setText(l.await()[14].QuestionString.toString())
                Q16.setText(l.await()[15].QuestionString.toString())

            }



        }


    }


    //Add Answer To The List
    private fun AddAnswer(){
        all_Answer.add(UserAnswer)
    }

    //Update Answer
    private fun UpdateAnswer(){
        all_Answer[Position]=UserAnswer
    }



    //choose General Health Value
    private fun GeneralHealthValue(No:Int):Double{
        var value:Double=0.0
        when(No){
            1->{value=0.05596944676044216} //Very good
            2->{value=0.11198116307085752} // Good
            3->{value=0.025081094833252063} // Excellent
            4->{value=0.21482479784366576} // Fair
            5->{value=0.34430379746835443} // Poor
        }
        return value
    }


    //choose Age category Value

    private fun AgeCategory(No:Int):Double{

        var value:Double=0.0
        when(No){

            1->{value=0.0056137012369172215} //18-24
            2->{value=0.005007789895392833}  //25-29
            3->{value=0.008095191651224031}  //30-34
            4->{value=0.00970958712386284}   //35-39
            5->{value=0.012181894034209429}  //40-44
            6->{value=0.02146474797009406}   //45-49
            7->{value=0.03525020735416091}   //50-54
            8->{value=0.05216254752851711}   //55-59
            9->{value=0.07676974188602095}   //60-64
            10->{value=0.10118678804632764}  //65-69
            11->{value=0.1371674765793297}   //70-74
            12->{value=0.16367666412689616}  //75-79
            13->{value=0.2024899304284145}  //80 or older
        }
        return value
    }


    //Prepare Api Answer list
    private fun PreparTheAnwser(Answer_list: List<String>): ArrayList<String> {
        val ArrangedList = arrayListOf<String>()
        //val size=Answer_list.size
        ArrangedList.add(Answer_list[14]) //BMI
        ArrangedList.add(Answer_list[7])      //SMOKING
        ArrangedList.add(Answer_list[6])      //ALCOHOL DRINKING
        ArrangedList.add(Answer_list[5])     //STROKE
        ArrangedList.add(Answer_list[10])     //PHYSICAL HEALTH
        ArrangedList.add(Answer_list[9]) //MENTAL HEALTH
        ArrangedList.add(Answer_list[4]) //DIFF WALKING
        ArrangedList.add(Answer_list[15]) //SEX
        ArrangedList.add(Answer_list[12]) //AGE CATEGORY
        ArrangedList.add(Answer_list[3]) //DIABETIC
        ArrangedList.add(Answer_list[8]) //PHYSICAL ACTIVITY
        ArrangedList.add(Answer_list[13]) //GENERAL HEALTH
        ArrangedList.add(Answer_list[11]) //SLEEP TIME
        ArrangedList.add(Answer_list[2]) //ASTHMA
        ArrangedList.add(Answer_list[1]) //KIDNEY DISEASE
        ArrangedList.add(Answer_list[0]) //SKIN CANCER

        return ArrangedList
    }


    //API Function Thanks EBRAHIM MOSSAD
    private fun SendSignUpDataToServer(all_Answer:ArrayList<String>) {


        volleyRequestQueue = Volley.newRequestQueue(context)
        dialog = ProgressDialog.show(context, "", "Please wait...", true);
        val parameters: MutableMap<String, String> = HashMap()
        // Add your parameters in HashMap
        parameters.put("bmi",all_Answer[0])
        parameters.put("smoking",all_Answer[1])
        parameters.put("alcoholDrinking",all_Answer[2])
        parameters.put("stroke",all_Answer[3])
        parameters.put("physicalHealth",all_Answer[4])
        parameters.put("mentalHealth",all_Answer[5])
        parameters.put("diffWalking",all_Answer[6])
        parameters.put("sex",all_Answer[7])
        parameters.put("ageCategory",all_Answer[8])
        parameters.put("diabetic",all_Answer[9])
        parameters.put("physicalActivity",all_Answer[10])
        parameters.put("genHealth",all_Answer[11])
        parameters.put("sleepTime",all_Answer[12])
        parameters.put("asthma",all_Answer[13])
        parameters.put("kidneyDisease",all_Answer[14])
        parameters.put("skinCancer",all_Answer[15])

        val strReq: StringRequest = object : StringRequest(
            Method.POST,serverAPIURL,
            Response.Listener { response ->
                Log.e(TAG, "response: " + response)
                dialog?.dismiss()

                // Handle Server response here
                try {
                    val responseObj = JSONObject(response)
//                    val isSuccess = responseObj.getBoolean("isSuccess")
//                    val code = responseObj.getInt("code")
                    val result= responseObj.getString("Placement")
                    if (responseObj.has("data")) {
                        val data = responseObj.getJSONObject("data")
                        // Handle your server response data here
                    }
                    Toast.makeText(context,result,Toast.LENGTH_LONG).show()

                    GlobalScope.launch(Dispatchers.IO ) {

                        withContext(Dispatchers.Main){
                            val action:NavDirections = QuestionTestDirections.actionQuestionTestToResult(result)// action_questionTest_to_result// QuestionTestDirection.action_questiontest_to_result()
                            findNavController().navigate(action)
                        }
                    }

                } catch (e: Exception) { // caught while parsing the response
                    Log.e(TAG, "problem occurred")
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError -> // error occurred
                Log.e(TAG, "problem occurred, volley error: " + volleyError.message)
            }) {

            override fun getParams(): MutableMap<String, String> {
                return parameters;
            }

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {

                val headers: MutableMap<String, String> = HashMap()
                // Add your Header paramters here
                return headers
            }
        }
        // Adding request to request queue
        volleyRequestQueue?.add(strReq)
    }


    //use for one time
    private fun GreateQuestion(){


        val Q1=Question(0,"what is your Gender \n femal= 1 \n  male= 0",1)
        all_Question.add(Q1)
        val Q2=Question(0,"what is your Body Mass Index BMI \n BMI = kg/m2 where \n kg is a person's weight in kilograms and \n m2 is their height in metres squared.",1)
        all_Question.add(Q2)
        val Q3=Question(0,"How can You Define Your general Health \n Very Good =1 \n Good =2 \n Excellent =3 \n Fair =4 \n Poor =5 \n    ",1)
        all_Question.add(Q3)
        val Q4=Question(0,"choose your Age Category \n 18-24 =1 \n 25-29 =2 \n 30-34 =3 \n 35-39 =4 \n 40-44 =5 \n 45-49 =6 \n 50-54 =7 \n 55-59 =8 \n 60-64 =9 \n 65-69 =10 \n 70-74 =11 \n 75-79 =12 \n 80-older =13 \n ",1)
        all_Question.add(Q4)
        val Q5=Question(0,"How Many hours on average do you Sleep \n (0-24) Default 7",1)
        all_Question.add(Q5)
        val Q6=Question(0,"For how many days during the past 30 days was your physical health not good? \n value between (0-30)",1)
        all_Question.add(Q6)
        val Q7=Question(0,"For how many days during the past 30 days was your mental health not good? \n value between (0-30)",1)
        all_Question.add(Q7)
        val Q8=Question(0,"Have you played any sports (running, biking, etc.) in the past month? \n Yes =1 \n No =0 \n ",1)
        all_Question.add(Q8)
        val Q9=Question(0,"Have you smoked at least 100 cigarettes in your entire life (approx. 5 packs)?) \n Yes =1 \n No =0 \n ",1)
        all_Question.add(Q9)
        val Q10=Question(0,"Do you have more than 14 drinks of alcohol (men) or more than 7 (women) in a week? \n Yes =1 \n No =0 \n ",1)
        all_Question.add(Q10)
        val Q11=Question(0,"Did you have a stroke? \n Yes =1 \n No =0 \n ",1)
        all_Question.add(Q11)
        val Q12=Question(0,"Do you have serious difficulty walking or climbing stairs? \n Yes =1 \n No =0 \n ",1)
        all_Question.add(Q12)
        val Q13=Question(0,"Have you ever had diabetes? \n Yes =1 \n No =0 \n ",1)
        all_Question.add(Q13)
        val Q14=Question(0,"Do you have asthma? \n Yes =1 \n No =0",1)
        all_Question.add(Q14)
        val Q15=Question(0,"Do you have kidney disease? \n Yes =1 \n No =0 \n ",1)
        all_Question.add(Q15)
        val Q16=Question(0,"Do you have skin cancer? \n Yes =1 \n No =0 \n ",1)
        all_Question.add(Q16)


    }
    private fun Delete_Questions(){
        GlobalScope.launch(Dispatchers.IO) {
            Repo.DeleteAll()
        }
    }
    private fun AddToDataBase(){
        GlobalScope.launch(Dispatchers.IO) {

            for ( x in all_Question){
                Repo.InsertOrUPdate(x)
            }

        }
    }




}