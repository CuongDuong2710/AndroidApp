package youtube.storybebi.cuong.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import youtube.storybebi.cuong.R;
import youtube.storybebi.cuong.adapter.SectionsPageAdapter;
import youtube.storybebi.cuong.fragment.EnglishFragment;
import youtube.storybebi.cuong.fragment.ViNaFragment;

public class TabbedActivity extends AppCompatActivity {
    @BindView(R.id.container) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;

    private SectionsPageAdapter mSectionsPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        ButterKnife.bind(this);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mSectionsPageAdapter.addFragment(new ViNaFragment(), "Tiếng Việt");
        mSectionsPageAdapter.addFragment(new EnglishFragment(), "English");
        viewPager.setAdapter(mSectionsPageAdapter);
    }

}
