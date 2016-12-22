package com.deliveryhero.android.prototype.tabs;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.deliveryhero.android.prototype.model.presentation.Section;

/**
 * View implementation intended to represent a simple list view. While this is intended as a sample
 * implementation, this can be easily expanded upon further to meet the needs of production code.
 */
public class SectionItemsView extends RecyclerView {

  public SectionItemsView(Context context) {
    super(context);
    initializeView();
  }

  private void initializeView() {
    setLayoutManager(new LinearLayoutManager(getContext()));
  }

  public SectionItemsView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    initializeView();
  }

  public SectionItemsView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initializeView();
  }

  public void bindData(Section section) {
    setAdapter(new SectionItemsAdapter(getContext(), section));
  }
}