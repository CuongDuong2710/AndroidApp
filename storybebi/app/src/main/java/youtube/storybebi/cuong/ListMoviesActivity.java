package youtube.storybebi.cuong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMoviesActivity extends AppCompatActivity {
    @BindView(R.id.list_video) RecyclerView recyclerView;

    private List<HashMap<String, Object>> movies = new ArrayList<>();
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_movies);
        ButterKnife.bind(this);

        mAdapter = new MovieAdapter(ListMoviesActivity.this, movies);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                // get data record
                HashMap<String, Object> data = movies.get(position);
                // set up movie
                String title = String.valueOf(data.get("title"));
                String lenght = String.valueOf(data.get("length"));
                String videoId = String.valueOf(data.get("videoId"));
                String imageUrl = String.valueOf(data.get("imageUrl"));

                Movie movie = new Movie(imageUrl, title, videoId, lenght);

                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected", Toast.LENGTH_SHORT).show();
                sendData(movie.getTitle(), movie.getVideoId());
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // get movies list from album
        getMovies();

    }

    /**
     * Get data from album (AlbumsAdapter)
     */
    private void getMovies() {
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("movies");
        // get list hashmap
        List<HashMap<String, Object>> hashMaps = (List<HashMap<String, Object>>) data.getSerializable("data");

        movies.addAll(hashMaps);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * send data to play video youtube
     * @param title
     * @param videoId
     */
    private void sendData(String title, String videoId) {
        Intent intent = new Intent(ListMoviesActivity.this, PlayVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", title );
        bundle.putString("videoId", videoId);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

//    private void prepareMovieDate() {
//        Movie movie = new Movie(R.drawable.around_the_world_eighty_days, "Anh chàng đánh trống", "ZCyDhkODKjw");
//        movies.add(movie);
//
//        movie = new Movie(R.drawable.david_copperfield, "Anh và em gái", "v5--09nEalQ");
//        movies.add(movie);
//
//        movie = new Movie(R.drawable.frankenstein, "Ba anh em", "Pm5PGnPLJy8");
//        movies.add(movie);
//
//        movie = new Movie(R.drawable.story_arion_and_dolphin, "Ba bà kéo sợi", "3-2c0b1u7s8");
//        movies.add(movie);
//
//        movie = new Movie(R.drawable.aladin_and_the_magic_lamp, "Ba người lùn trong rừng", "cypnbgSVv20");
//        movies.add(movie);
//
//        movie = new Movie(R.drawable.aladin_and_the_magic_lamp, "Hanxơ sắt", "wWdtrpHDiEc");
//        movies.add(movie);
//
//        movie = new Movie(R.drawable.aladin_and_the_magic_lamp, "Gã thợ xay nghèo khó", "PJY50e9WYs4");
//        movies.add(movie);
//
//        mAdapter.notifyDataSetChanged();
//
//    }

}
