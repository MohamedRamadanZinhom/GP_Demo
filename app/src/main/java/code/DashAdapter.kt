package code

import Disease.Disease
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohamed_ramadan.code.gpdemo.R

class DashAdapter(private val list : ArrayList<Disease> ) : RecyclerView.Adapter<DashAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.dashitem,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val disease=list.get(position)
        holder.id.text =disease.ID.toString()
        holder.name.text=disease.Name
        holder.accuracy.text =disease.Model_Accuracy.toString()


    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(item :View) : RecyclerView.ViewHolder(item) ,View.OnClickListener{

        val id: TextView =item.findViewById<TextView>(R.id.itemid)
        val name: TextView =item.findViewById<TextView>(R.id.itemname)
        val accuracy: TextView =item.findViewById<TextView>(R.id.itemaccuracy)

        override fun onClick(p0: View?) {

            if (p0 != null) {
               // Navigation.findNavController(p0).navigate(R.id.action_dashBoard_to_questionTest)
            }

        }


    }

    public interface adapterclick{

        fun onclick( position:Int)

    }



}