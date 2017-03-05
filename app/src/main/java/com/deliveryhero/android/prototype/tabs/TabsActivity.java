package com.deliveryhero.android.prototype.tabs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.deliveryhero.android.prototype.R;
import com.deliveryhero.android.prototype.model.presentation.Section;
import java.util.ArrayList;
import java.util.List;

import static com.deliveryhero.android.prototype.model.presentation.util.SectionsGenerator.INSTANCE;

/**
 * View(-ish) implementation used to handle the UI logic connecting each view component. The core
 * concepts (and possibly even parts of the code) can be adapter to match the needs of production
 * code.
 */
public class TabsActivity extends AppCompatActivity {

  @BindView(R.id.menu_sections) Spinner mSectionsSpinner;
  @BindView(R.id.subsection_tabs) TabLayout mSubsectionTabs;
  @BindView(R.id.content_menu_items) ViewPager mContent;

  private final List<Section> mSections = new ArrayList<>();
  private ContentAdapter mContentAdapter;
  private String[] mBackgroundColors;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeScreenViews();
  }

  private void initializeScreenViews() {
    setContentView(R.layout.screen_tabs);
    ButterKnife.bind(this);
    initializeValues();
    initializeSectionsSpinner();
    initializeSubsectionTabs();
    initializeContent();
  }

  private void initializeValues() {
    mBackgroundColors = getResources().getStringArray(R.array.colors);
  }

  private void initializeSectionsSpinner() {
    mSectionsSpinner.setAdapter(new ArrayAdapter<>(this, R.layout.section_item, getSections()));
    mSectionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        displaySubsectionContentsFor(position);
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {
      }
    });
  }

  private void initializeSubsectionTabs() {
    mSubsectionTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
    mSubsectionTabs.setupWithViewPager(mContent, true);
  }

  private void initializeContent() {
    mContentAdapter = new ContentAdapter(this);
    mContent.setAdapter(mContentAdapter);
    mContent.setOffscreenPageLimit(2);
  }

  private void displaySubsectionContentsFor(int position) {
    List<Section> subsections = getSections().get(position).getSubsections();
    mContentAdapter.setSubsections(subsections);
    mContent.setBackgroundColor(getBackgroundColorAt(position));
    displaySubsectionTabsFor(subsections);
  }

  private void displaySubsectionTabsFor(List<Section> subsections) {
    if (subsections.size() > 1) {
      mSubsectionTabs.setVisibility(View.VISIBLE);
      mContent.setCurrentItem(0);
    } else {
      mSubsectionTabs.setVisibility(View.GONE);
    }
  }

  private int getBackgroundColorAt(int position) {
    position = position % mBackgroundColors.length;
    return Color.parseColor(mBackgroundColors[position]);
  }

  private List<Section> getSections() {
    if (mSections.isEmpty()) {
      mSections.addAll(INSTANCE.generateSections(6));
    }
    return mSections;
  }
}