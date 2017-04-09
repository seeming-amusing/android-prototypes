package com.deliveryhero.android.prototype.tabs

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deliveryhero.android.prototype.R
import com.deliveryhero.android.prototype.model.presentation.Section

/**
 * Adapter implementation used to display the set of subsections. While this is intended as a
 * sample adapter implementation, it can be reused as a basis for production code.
 */
class ContentAdapter(context: Context) : PagerAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val subsections = mutableListOf<Section>()

    fun setSubsections(subsections: List<Section>) {
        this.subsections.clear()
        this.subsections.addAll(subsections)
        notifyDataSetChanged()
    }

    override fun getCount() = subsections.size
    override fun isViewFromObject(view: View, it: Any) = view === it
    override fun getPageTitle(position: Int) = subsections[position].name

    override fun instantiateItem(container: ViewGroup, position: Int) =
            (inflater.inflate(R.layout.content_layout, container, false) as SectionItemsView).apply {
                val subsection = subsections[position]
                tag = subsection
                bindData(subsection)
                container.addView(this)
            }

    override fun getItemPosition(it: Any?): Int {
        var position = PagerAdapter.POSITION_NONE
        if (it is View) {
            subsections.indexOf(it.tag).takeIf { it >= 0 }?.let { position = it }
        }
        return position
    }

    override fun destroyItem(container: ViewGroup, position: Int, it: Any) {
        if (it is View) container.removeView(it)
    }
}