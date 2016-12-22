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

  private final List<Section> mSections = new ArrayList<>();
  private final LayoutInflater mInflater;

  ContentAdapter(Context context, List<Section> sections) {
    mSections.addAll(sections);
    mInflater = LayoutInflater.from(context);
  }

  @Override public int getCount() {
    return mSections.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    SectionItemsView contentView =
        (SectionItemsView) mInflater.inflate(R.layout.content_layout, container, false);
    contentView.bindData(mSections.get(position));
    container.addView(contentView);
    return contentView;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    if (object instanceof View) {
      container.removeView((View) object);
    }
  }

  @Override public CharSequence getPageTitle(int position) {
    return mSections.get(position).name;
  }
}