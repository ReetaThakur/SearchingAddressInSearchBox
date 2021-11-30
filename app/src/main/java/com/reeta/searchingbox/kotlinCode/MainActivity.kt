package com.reeta.searchingbox.kotlinCode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reeta.searchingbox.R
import com.reeta.searchingbox.response.Address
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AddViewModel
    private var list =ArrayList<Address>()
    private var tempList =ArrayList<Address>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel=ViewModelProvider(this).get(AddViewModel::class.java)
        viewModel.callApi()
        viewModel.liveData.observe(this,{
            it.let {
                when(it){
                    is MainUIModel.onSuccess ->{
                        list=it.responseDTO.data.addressList as ArrayList<Address>
                        setRecyclerView()
                    }
                    is MainUIModel.onFailure ->{
                        Toast.makeText(this,it.error,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.searchMenu)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                tempList.clear()
                val searchText =s!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    list.forEach {
                        if (it.addressString.toLowerCase(Locale.getDefault()).contains(s)){
                            tempList.add(it)
                        }
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                }else {
                    tempList.clear()
                    tempList.addAll(list)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun setRecyclerView() {
        val addAdapter=AddressAdapter(tempList)
        tempList.addAll(list)
        val linearLayoutManager=LinearLayoutManager(this)
        recyclerView.apply {
            adapter=addAdapter
            layoutManager=linearLayoutManager
        }
    }



}