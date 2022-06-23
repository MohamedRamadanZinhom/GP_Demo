package com.mohamed_ramadan.code.gpdemo

import FireBase.Authentication
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import com.mohamed_ramadan.code.gpdemo.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [app_dash.newInstance] factory method to
 * create an instance of this fragment.
 */
class app_dash : Fragment() {

    lateinit var diseasebtn:CardView
    lateinit var ideabtn:CardView
    lateinit var profilebtn:CardView
    lateinit var infobtn:CardView
    lateinit var statisticsbtn:CardView
    lateinit var LogOutBtn:CardView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_app_dash, container, false)


        diseasebtnAction(view)
        ideabtnAction(view)
        profilebtnAction(view)
        infobtnAction(view)
        statisticsbtnAction(view)
        LogOutBtnAction(view)


        return view
    }



    fun ideabtnAction(view: View){
        ideabtn=view.findViewById(R.id.idea_cardview)
        ideabtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_app_dash_to_pager_container)
        }

    }

    fun profilebtnAction(view: View){
        profilebtn=view.findViewById(R.id.profile_cardview)
        profilebtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_app_dash_to_my_profile)
        }

    }

    fun infobtnAction(view: View){
        infobtn=view.findViewById(R.id.inf_cardview)
        infobtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_app_dash_to_information_and_Data)
        }

    }

    fun statisticsbtnAction(view: View){
        statisticsbtn=view.findViewById(R.id.statistics_cardview)
        statisticsbtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_app_dash_to_statistics)
        }
    }

    fun diseasebtnAction(view: View){
        diseasebtn=view.findViewById(R.id.disease_cardview)
        diseasebtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_app_dash_to_dashBoard)
        }
    }

    fun LogOutBtnAction(view: View){
        LogOutBtn=view.findViewById(R.id.logout)
        LogOutBtn.setOnClickListener{

            val auth=Authentication(this.requireActivity())
            auth.getInstance().signOut()
            Navigation.findNavController(view).navigate(R.id.action_app_dash_to_spalsh)
        }
    }



}