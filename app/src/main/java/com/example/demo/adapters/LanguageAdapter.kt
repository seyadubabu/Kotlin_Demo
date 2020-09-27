package com.example.demo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.models.Language

class LanguageAdapter(context: Context) : RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    var languages: List<Language>? = listOf()
    private val context: Context? = context

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv_lname = itemView.findViewById<TextView?>(R.id.tv_lname)
        val tv_ntname = itemView.findViewById<TextView?>(R.id.tv_ntname)
        val tv_iso639_1 = itemView.findViewById<TextView?>(R.id.tv_iso639_1)
        val tv_iso639_2 = itemView.findViewById<TextView?>(R.id.tv_iso639_2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.language_card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return languages?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_lname!!.text = "Name: " + languages!![position].getName()
        holder.tv_ntname!!.text = "Native Name: " + languages!![position].getNativeName()
        holder.tv_iso639_1!!.text = "ISO639_1: " + languages!![position].getIso6391()
        holder.tv_iso639_2!!.text = "ISO639_2: " + languages!![position].getIso6392()
    }

    fun setListItems(languages: List<Language?>?) {
        this.languages = languages as List<Language>?
        notifyDataSetChanged()
    }
}