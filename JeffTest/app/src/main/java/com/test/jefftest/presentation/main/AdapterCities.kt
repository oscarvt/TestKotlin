package com.test.jefftest.presentation.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.test.jefftest.R
import com.test.jefftest.data.model.Cities
import com.test.jefftest.data.model.City
import com.test.jefftest.inflate
import kotlinx.android.synthetic.main.item_name_city.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class AdapterCities(val listCities: Cities, val listener: (City) -> Unit) : RecyclerView.Adapter<AdapterCities.ViewHolderMembers>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMembers = ViewHolderMembers(parent.inflate(R.layout.item_name_city))


    override fun onBindViewHolder(holder: ViewHolderMembers, position: Int) {
        return holder.bind(listCities.geonames[position], listener)!!

    }

    override fun getItemCount(): Int = listCities.geonames.size



    class ViewHolderMembers(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: City?, listener: (City) -> Unit) = with(itemView) {

            item?.let { city ->
                text_name_city.text = "${item.name} , ${item.countryName}"
                this.onClick {
                    listener.invoke(city)
                }
            }

        }
    }

}