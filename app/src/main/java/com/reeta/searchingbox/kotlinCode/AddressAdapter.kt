package com.reeta.searchingbox.kotlinCode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reeta.searchingbox.R
import com.reeta.searchingbox.response.Address

class AddressAdapter(var list:ArrayList<Address>):RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view:View= LayoutInflater.from(parent.context).inflate(R.layout.show_layout,parent,false)
        return AddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        var addList=list[position]
        holder.setData(addList)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class AddressViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        var address:TextView=itemView.findViewById(R.id.tvAddress)
        var city:TextView=itemView.findViewById(R.id.tvCity)
        var pinCode:TextView=itemView.findViewById(R.id.tvPinCode)
        fun setData(add:Address){
            address.text=add.addressString
            city.text=add.city
            pinCode.text=add.pinCode
        }

    }
}