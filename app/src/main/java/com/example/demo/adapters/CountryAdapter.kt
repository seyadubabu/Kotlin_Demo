package com.example.demo.adapters

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.models.Country
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class CountryAdapter(val context: Context): RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    private val clickHandler: ClickEventHandler = context as ClickEventHandler
    var countryList : ArrayList<Country> = arrayListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.card_view)
        val imageView: ImageView = itemView.findViewById(R.id.iv_country)
        val tv_name: TextView= itemView.findViewById(R.id.tv_name)
        val tv_region: TextView = itemView.findViewById(R.id.tv_region)
        val tv_population: TextView = itemView.findViewById(R.id.tv_population)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        GlideToVectorYou
            .init()
            .with(this.context)
            //.setPlaceHolder(R.drawable.loading, R.drawable.actual)
            .load(Uri.parse(countryList[position].getFlag()), holder.imageView)
        holder.tv_name.text = "Country Name: " + countryList[position].getName()
        holder.tv_region.text = "Rigion: " + getRegionValue(position)
        holder.tv_population.text = "Population: " + (countryList[position].getPopulation()).toString()

        holder.cardView.setOnClickListener {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#b70505"))
            clickHandler.forwardClick(countryList[position])
        }
    }

    fun getRegionValue(position: Int): Any? {
        if(countryList[position].getRegion().equals("")) return "NIL" else return countryList[position].getRegion()
    }

    fun setCountryListItems(countryList: ArrayList<Country>){
        this.countryList = countryList;
        notifyDataSetChanged()
    }

    fun filterList(filterCountries: java.util.ArrayList<Country>) {
        this.countryList = filterCountries;
        notifyDataSetChanged()
    }

    interface ClickEventHandler {
        fun forwardClick(country: Country)
    }
}