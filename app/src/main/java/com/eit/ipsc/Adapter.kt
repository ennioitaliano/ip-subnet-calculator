package com.eit.ipsc

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Adapter
//protected WeakReference<ClickListener> listenerRef;

// data is passed into the constructor
(context: Context, private val mData1: List<String>, private val mData2: List<String>, private val mRecyclerView: RecyclerView) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    init {
        this.mInflater = LayoutInflater.from(context)
    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.row, parent, false)
        setClickListener(mClickListener)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = mData1[position]
        holder.titlesTV.text = title

        val result = mData2[position]
        holder.resultsTV.text = result

        if (holder.resultsTV.id == 0)
            holder.resultsTV.textSize = 15f
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData1.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var titlesTV: TextView
        internal var resultsTV: TextView

        init {

            //listenerRef = new WeakReference<>(listener);
            titlesTV = itemView.findViewById(R.id.title)
            resultsTV = itemView.findViewById(R.id.result)
        }

        override fun onClick(view: View) {

            /*if (view.getId() == resultsTV.getId()) {
                //Toast.makeText(view.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
            //listenerRef.get().onPositionClicked(getAdapterPosition());*/
        }
    }

    // convenience method for getting data at click position
    internal fun getItem(id: Int): String {
        return mData1[id]
    }

    // allows clicks events to be caught
    internal fun setClickListener(itemClickListener: ItemClickListener?) {
        this.mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}
