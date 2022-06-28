package com.mohamed_ramadan.code.gpdemo.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohamed_ramadan.code.gpdemo.QuestionTest
import com.mohamed_ramadan.code.gpdemo.R
import kotlinx.android.synthetic.main.fragment_input.*


class InputFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        inputanswer.setOnClickListener{
            QuestionTest.UserAnswer=user_ans.text.toString()
        }

        return view
    }


}