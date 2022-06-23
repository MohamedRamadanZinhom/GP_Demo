package com.mohamed_ramadan.code.gpdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_result.view.*


class Result : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_result, container, false)
        ActionBar.DISPLAY_HOME_AS_UP

        val args by navArgs<ResultArgs>()

        val prog=args.apiResult.toFloat()*100

        view.progress_result.progress=prog.toInt()
        view.txt_result_Prediction.setText(prog.toString())



        return view
    }


}