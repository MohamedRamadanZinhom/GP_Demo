package Disease



interface Disease {

    var ID:Int
    var Name :String
    var Feature_Count:Int
    val ModelPath:String
    val Model_Accuracy:Int

    public fun Prediction()

}