package com.mohamed_ramadan.code.gpdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_app_idea.view.*
import kotlinx.android.synthetic.main.fragment_second_screen.view.*
import kotlinx.android.synthetic.main.fragment_third_screen.view.*


class third_screen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)

        val viewpager= activity?.findViewById<ViewPager2>(R.id.viewpager)
        view.next3.setOnClickListener{
            viewpager?.currentItem=3
        }

        return view
    }

}