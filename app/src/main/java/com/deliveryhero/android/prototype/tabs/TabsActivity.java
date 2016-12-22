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

import static com.deliveryhero.android.prototype.model.presentation.util.SectionsGenerator.generateSections;

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
        displaySubsectionTabsFor(position);
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
    mContent.setOffscreenPageLimit(2);
  }

  private void displaySubsectionTabsFor(int position) {
    mSubsectionTabs.removeAllTabs();
    List<Section> subsections = getSections().get(position).subsections;
    if (subsections.size() > 1) {
      mSubsectionTabs.setVisibility(View.VISIBLE);
      for (Section subsection : subsections) {
        TabLayout.Tab tab = mSubsectionTabs.newTab();
        tab.setText(subsection.name);
        mSubsectionTabs.addTab(tab);
      }
    } else {
      mSubsectionTabs.setVisibility(View.GONE);
    }
  }

  private void displaySubsectionContentsFor(int position) {
    List<Section> subsections = getSections().get(position).subsections;
    mContent.setAdapter(new ContentAdapter(this, subsections));
    mContent.setBackgroundColor(getBackgroundColorAt(position));
  }

  private int getBackgroundColorAt(int position) {
    position = position % mBackgroundColors.length;
    return Color.parseColor(mBackgroundColors[position]);
  }

  private List<Section> getSections() {
    if (mSections.isEmpty()) {
      mSections.addAll(generateSections(6));
    }
    return mSections;
  }
}