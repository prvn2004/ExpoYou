package com.example.expoyou

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expoyou.databinding.ItemViewBinding


class MyAdapter(val mList: ArrayList<DataFile>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() { //class  which will take prameter(list of strings)
// ------------------------------------------------------------------------------------------------------------------------------------------

    private lateinit var binding: ItemViewBinding
//-------------------------------------------------------------------------------------------------------------------------------------------------

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {  //function take parameter()

        binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding) //returning MyViewHolder class with a view inside it
    }


//--------------------------------------------------------------------------------------------------------------------------------------------------


    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {

        val Link = mList[position]
        holder.friction(Link)
    }

//--------------------------------------------------------------------------------------------------------------------------------------------

    override fun getItemCount(): Int {  // this function is counting the size of list
        return mList.size // returning the size of list
    }

//--------------------------------------------------------------------------------------------------------------------------------------------

    class MyViewHolder(ItemViewBinding: ItemViewBinding) :
        RecyclerView.ViewHolder(ItemViewBinding.root) {   // a class which takes a view as a parameter and

        private val binding = ItemViewBinding
        fun friction(Link: DataFile) {
            // this is setting title its text accroding to its position
            binding.Url.text =
                Link.getUrl()// this is setting title its text accroding to its position
        }
    }


//--------------------------------------------------------------------------------------------------------------------------------------------


//--------------------------------------------------------------------------------------------------------------------------------------------

    interface OnItemClickListener {
        fun onItemClicked(Link: DataFile)
    }

}
//--------------------------------------------------------------------------------------------------------------------------------------------



