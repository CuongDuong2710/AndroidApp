package youtube.storybebi.cuong;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        mSectionsPageAdapter.addFragment(new ViNaFragment(), "VietNamese");
        mSectionsPageAdapter.addFragment(new EnglishFragment(), "English");
        viewPager.setAdapter(mSectionsPageAdapter);
    }

}
