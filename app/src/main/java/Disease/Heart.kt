package Disease

import Disease.Disease

class Heart(): Disease {

    override var ID: Int=1
    override var Name: String = "Heart Disease"
    override val ModelPath: String=""
    override var Feature_Count: Int=13
    override val Model_Accuracy: Int=95



    override fun Prediction() {
        TODO("Not yet implemented")
    }


}