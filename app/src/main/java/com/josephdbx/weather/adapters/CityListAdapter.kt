package com.josephdbx.weather.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.josephdbx.weather.R

class CityListAdapter(var context: Context, items: ArrayList<CityItem>) : BaseAdapter() {
    var items: ArrayList<CityItem>? = null

    init {
        this.items = items
    }

    override fun getCount(): Int {
        return this.items?.count()!!
    }

    override fun getItem(position: Int): Any {
        return items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder? = null
        var view: View? = convertView

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.template_city_list, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as? ViewHolder
        }

        val item = getItem(position) as CityItem
        holder?.tvCityName?.text = item.name
        holder?.tvCityCode?.text = item.code

        return view!!
    }

    private class ViewHolder(view: View) {
        var tvCityName: TextView? = null
        var tvCityCode: TextView? = null

        init {
            tvCityName = view.findViewById(R.id.tvCityName)
            tvCityCode = view.findViewById(R.id.tvCityCode)
        }
    }
}