package com.deliveryhero.android.prototype.tabs

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.deliveryhero.android.prototype.R
import com.deliveryhero.android.prototype.model.presentation.Section
import com.deliveryhero.android.prototype.tabs.SectionItemsAdapter.Holder
import kotlinx.android.synthetic.main.content_description.view.*

/**
 * Adapter implementation used to display the list of items under a section. This is intended as a
 * sample adapter implementation and should not be treated as production-ready.
 */
class SectionItemsAdapter(context: Context, private val section: Section) :
        RecyclerView.Adapter<Holder>() {

    private val inflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int) = if (position == 0) DESCRIPTION else ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            Holder(inflater.inflate(layoutForType(viewType), parent, false))

    private fun layoutForType(viewType: Int) = when (viewType) {
        DESCRIPTION -> R.layout.content_description
        else -> R.layout.content_item
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.content.text = getTextFor(position)
    }

    private fun getTextFor(position: Int) = when (position) {
        0 -> section.description
        else -> section.items[position - 1]
    }

    override fun getItemCount() = section.items.size + 1

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val content: TextView = itemView.content_view
    }

    companion object {
        private val DESCRIPTION = 0xf00
        private val ITEM = 0xba0
    }
}