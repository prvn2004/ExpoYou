package com.example.expoyou

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expoyou.databinding.FragmentVideoBinding

class VideoFragment : Fragment(R.layout.fragment_video), MyAdapter.OnItemClickListener {
    private lateinit var binding: FragmentVideoBinding
//    private lateinit var myAdapter: MyAdapter
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var data: ArrayList<DataFile>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = ArrayList<DataFile>()
        val obj1 = DataFile("This is value")
        data.add(obj1)
        val obj2 = DataFile("this is value 2")
        data.add(obj2)
        val obj3 = DataFile("this is vakue 3")
        data.add(obj3)
        val obj4 = DataFile("This is value 4")
        data.add(obj4)

        val  myAdapter = MyAdapter(data)
        myAdapter.notifyDataSetChanged()
        binding = FragmentVideoBinding.inflate(layoutInflater)
        layoutManager = LinearLayoutManager(context)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = myAdapter


    }



    override fun onItemClicked(Link: DataFile) {
        TODO("Not yet implemented")
    }


}

