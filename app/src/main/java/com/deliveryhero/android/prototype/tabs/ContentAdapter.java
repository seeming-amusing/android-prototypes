package com.deliveryhero.android.prototype.tabs;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.deliveryhero.android.prototype.R;
import com.deliveryhero.android.prototype.model.presentation.Section;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter implementation used to display the set of subsections. While this is intended as a
 * sample adapter implementation, it can be reused as a basis for production code.
 */
class ContentAdapter extends PagerAdapter {

  private final List<Section> mSubsections = new ArrayList<>();
  private final LayoutInflater mInflater;

  ContentAdapter(Context context) {
    mInflater = LayoutInflater.from(context);
  }

  void setSubsections(List<Section> subsections) {
    mSubsections.clear();
    mSubsections.addAll(subsections);
    notifyDataSetChanged();
  }

  @Override public int getCount() {
    return mSubsections.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    SectionItemsView contentView =
        (SectionItemsView) mInflater.inflate(R.layout.content_layout, container, false);
    Section subsection = mSubsections.get(position);
    contentView.bindData(subsection);
    contentView.setTag(subsection);
    container.addView(contentView);
    return contentView;
  }

  @Override public int getItemPosition(Object object) {
    int position = POSITION_NONE;
    if (object instanceof View) {
      View view = (View) object;
      Section subsection = (Section) view.getTag();
      int index = mSubsections.indexOf(subsection);
      position = index >= 0 ? index : POSITION_NONE;
    }
    return position;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    if (object instanceof View) {
      container.removeView((View) object);
    }
  }

  @Override public CharSequence getPageTitle(int position) {
    return mSubsections.get(position).name;
  }
}