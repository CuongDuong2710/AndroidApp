package youtube.storybebi.cuong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import youtube.storybebi.cuong.DividerItemDecoration;
import youtube.storybebi.cuong.object.Movie;
import youtube.storybebi.cuong.R;
import youtube.storybebi.cuong.RecyclerTouchListener;
import youtube.storybebi.cuong.adapter.MovieAdapter;

public class ListMoviesActivity extends AppCompatActivity {
    @BindView(R.id.list_video) RecyclerView recyclerView;

    private List<Object> mRecyclerViewItems = new ArrayList<>();
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_movies);
        ButterKnife.bind(this);

        // Call before getMovies() to avoid 'NullPointerException' in 'mAdapter.notifyDataSetChanged()'
        mAdapter = new MovieAdapter(ListMoviesActivity.this, mRecyclerViewItems);

        // get mRecyclerViewItems list from album
        getMovies();
        addNativeExpressAds();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                Object item = mRecyclerViewItems.get(position);
                if (!(item instanceof NativeExpressAdView)) {
                    // get data record
                    HashMap<String, Object> data = (HashMap<String, Object>) mRecyclerViewItems.get(position);
                    // set up movie
                    String title = String.valueOf(data.get("title"));
                    String lenght = String.valueOf(data.get("length"));
                    String videoId = String.valueOf(data.get("videoId"));
                    String imageUrl = String.valueOf(data.get("imageUrl"));
                    String description = String.valueOf(data.get("description"));

                    Movie movie = new Movie(imageUrl, title, videoId, lenght, description);

                    Toast.makeText(getApplicationContext(), movie.getTitle(), Toast.LENGTH_SHORT).show();
                    sendData(movie.getTitle(), movie.getVideoId(), movie.getDescription());
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private void addNativeExpressAds() {
        for (int i = 0; i < mRecyclerViewItems.size(); i+=8 ) {
            NativeExpressAdView adView = new NativeExpressAdView(this);
            adView.setAdSize(new AdSize(320, 150));
            adView.setAdUnitId("ca-app-pub-2267083612728588/6133498163");
            adView.loadAd(new AdRequest.Builder().build());
            mRecyclerViewItems.add(i, adView);
        }
    }

    /**
     * Get data from album (AlbumsAdapter)
     */
    private void getMovies() {
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("movies");
        // get list hashmap
        List<HashMap<String, Object>> hashMaps = (List<HashMap<String, Object>>) data.getSerializable("data");

        mRecyclerViewItems.addAll(hashMaps);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * send data to play video youtube
     * @param title
     * @param videoId
     */
    private void sendData(String title, String videoId, String description) {
        Intent intent = new Intent(ListMoviesActivity.this, PlayVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", title );
        bundle.putString("videoId", videoId);
        bundle.putString("description", description);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }
}
