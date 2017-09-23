package youtube.storybebi.cuong.fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import youtube.storybebi.cuong.R;
import youtube.storybebi.cuong.adapter.AlbumsAdapter;
import youtube.storybebi.cuong.object.Album;
import youtube.storybebi.cuong.object.Movie;

/**
 * Created by QUOC CUONG on 22/09/2017.
 */

public class ViNaFragment extends Fragment {
    @BindView(R.id.vni_recycler_view) RecyclerView recyclerView;

    private static final String TAG = "ViNaFragment";

    private DatabaseReference mDatabase = null;

    private AlbumsAdapter adapter = null;
    private List<Album> albums = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.vi_fragment, container, false);
        ButterKnife.bind(this, rootView);


        adapter = new AlbumsAdapter(getActivity(), albums);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getValueFromFireBase();

        return rootView;
    }

    private void getValueFromFireBase() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("albums");

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // get value
                /*
                "album": "Truyện cổ tích",
                        "imageUrl": "https://goo.gl/GGtjsW",
                        "movies": [
                {
                    "lenght": "10:30",
                        "title": "Ba anh em",
                        "videoId": "Pm5PGnPLJy8"
                },
                "numberOfMovies": "15"*/
                HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();

                String title = (String) value.get("album");
                String imageUrl = (String) value.get("imageUrl");
                String numberOfMovies = String.valueOf(value.get("numberOfMovies"));
                ArrayList<Movie> movies = (ArrayList<Movie>) value.get("movies");

                Album album = new Album(title, numberOfMovies, imageUrl);
                album.setMovies(movies);
                albums.add(album);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
