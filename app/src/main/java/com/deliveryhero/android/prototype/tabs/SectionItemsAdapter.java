package com.deliveryhero.android.prototype.tabs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.deliveryhero.android.prototype.R;
import com.deliveryhero.android.prototype.model.presentation.Section;

/**
 * Adapter implementation used to display the list of items under a section. This is intended as a
 * sample adapter implementation and should not be treated as production-ready.
 */
class SectionItemsAdapter extends RecyclerView.Adapter<SectionItemsAdapter.Holder> {

  private static final int DESCRIPTION = 0xf00;
  private static final int ITEM = 0xba0;

  private final LayoutInflater mInflater;
  private final Section mSection;

  SectionItemsAdapter(Context context, Section section) {
    mInflater = LayoutInflater.from(context);
    mSection = section;
  }

  @Override public int getItemViewType(int position) {
    return position == 0 ? DESCRIPTION : ITEM;
  }

  @Override public SectionItemsAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    int layout = viewType == DESCRIPTION ? R.layout.content_description : R.layout.content_item;
    return new SectionItemsAdapter.Holder(mInflater.inflate(layout, parent, false));
  }

  @Override public void onBindViewHolder(SectionItemsAdapter.Holder holder, int position) {
    holder.text.setText(getTextFor(position));
  }

  private String getTextFor(int position) {
    if (position == 0) {
      return mSection.getDescription();
    } else {
      return mSection.getItems().get(position - 1);
    }
  }

  @Override public int getItemCount() {
    return mSection.getItems().size() + 1;
  }

  /**
   * Simple view holder for the adapter
   */
  static class Holder extends RecyclerView.ViewHolder {

    @BindView(R.id.content_view) TextView text;

    Holder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}