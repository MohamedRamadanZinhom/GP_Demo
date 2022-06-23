package com.mohamed_ramadan.code.gpdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_app_idea.view.*


class app_idea : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_app_idea, container, false)

        val viewpager= activity?.findViewById<ViewPager2>(R.id.viewpager)

        view.next1.setOnClickListener{
            viewpager?.currentItem=1
        }

        return view
    }


}