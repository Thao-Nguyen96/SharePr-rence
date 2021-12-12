package com.nxt.sharedpreferencesobject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*

class ExampleAdapter(private var listExample: ArrayList<ExampleItem>) : RecyclerView.Adapter<ExampleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.example_item, parent, false))
    }

    override fun onBindViewHolder(holder: ExampleAdapter.ViewHolder, position: Int) {
        holder.itemView.textview_line1.text = listExample[position].mLine1
        holder.itemView.textview_line_2.text = listExample[position].mLine2
    }

    override fun getItemCount(): Int {
        return listExample.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}