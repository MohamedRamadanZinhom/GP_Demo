package com.mohamed_ramadan.code.gpdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import code.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_pager_container.view.*


class Pager_container : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pager_container, container, false)

        val fragmentList = arrayListOf<Fragment>(
            app_idea(),
            second_screen(),
            third_screen(),
            fourth_screan()
        )

        val adapter =ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
            fragmentList )

        view.viewpager.adapter=adapter

        return view
    }


}