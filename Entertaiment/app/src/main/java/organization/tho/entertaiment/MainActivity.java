package organization.tho.entertaiment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import organization.tho.entertaiment.Listener.ItemClickListener;
import organization.tho.entertaiment.Model.Category;
import organization.tho.entertaiment.ViewHolder.CategoryViewHolder;

/**
 * Loading category menu screen
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view_category) RecyclerView mRecyclerViewCategory;
    @BindView(R.id.appbar) AppBarLayout mAppBarLayout;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar) Toolbar mToolBar;
    @BindView(R.id.backdrop) ImageView mBackDrop;

    // Declaring Firebase variables
    FirebaseDatabase database = null;
    DatabaseReference category = null;

    // Declaring adapter to binding data for category menu
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_category_coordinator);
        ButterKnife.bind(this);

        setSupportActionBar(mToolBar);

        // init collapsing toolbar
        initCollapsingToolbar();

        // setting recycler view layout
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerViewCategory.setLayoutManager(mLayoutManager);
        mRecyclerViewCategory.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        mRecyclerViewCategory.setItemAnimator(new DefaultItemAnimator());

        // init firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        // loading category
        loadCategory();

        // loading image Back drop
        loadBackDrop();
    }

    /**
     * Loading image Back drop
     */
    private void loadBackDrop() {
        try {
            Picasso.with(getBaseContext()).load(R.drawable.comic).into(mBackDrop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        mCollapsingToolbarLayout.setTitle(" ");
        mAppBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = mAppBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    // collapsed toolbar and showing title
                    mCollapsingToolbarLayout.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    // expanded toolbar
                    mCollapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Loading category data by Firebase UI
     */
    private void loadCategory() {
        // setting adapter
        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(Category.class,
                        R.layout.category_card,
                        CategoryViewHolder.class,
                        category) {
            @Override
            protected void populateViewHolder(CategoryViewHolder viewHolder, Category model, int position) {
                // set category name
                viewHolder.txtCategoryName.setText(model.getName());
                // set category image
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imgCategory);
                // get current category item
                final Category currentItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(MainActivity.this, "" + currentItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        // after setting adapter, binding to recycler view
        mRecyclerViewCategory.setAdapter(adapter);
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
