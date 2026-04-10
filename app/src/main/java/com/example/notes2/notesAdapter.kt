package com.example.notes2

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class notesAdapter(var items: List<nates>,var context: Context,) : RecyclerView.Adapter<notesAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val text: TextView = view.findViewById(R.id.name)
        val data: TextView = view.findViewById(R.id.desk)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_notes,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.text = items[position].text
        holder.data.text = items[position].data

        holder.itemView.setOnClickListener {
            val intent = Intent(context, itemActivity::class.java)

            intent.putExtra("itemTitle",items[position].text)
            intent.putExtra("itemMainTitle",items[position].mainText)
            intent.putExtra("DOC_ID", items[position].documentId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }



}