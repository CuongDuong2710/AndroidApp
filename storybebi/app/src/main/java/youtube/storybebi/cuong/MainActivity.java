package youtube.storybebi.cuong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movie> movies = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        recyclerView = (RecyclerView) findViewById(R.id.list_video);

        mAdapter = new MovieAdapter(movies);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                Movie movie = movies.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

        prepareMovieDate();
    }

    private void prepareMovieDate() {
        Movie movie = new Movie(R.drawable.around_the_world_eighty_days, "Around The World In Eighty Days");
        movies.add(movie);

        movie = new Movie(R.drawable.david_copperfield, "David Copperfield");
        movies.add(movie);

        movie = new Movie(R.drawable.frankenstein, "Frankenstein");
        movies.add(movie);

        movie = new Movie(R.drawable.story_arion_and_dolphin, "Story of Arion and the Dolphin");
        movies.add(movie);

        movie = new Movie(R.drawable.aladin_and_the_magic_lamp, "Aladdin And The Magic Lamp");
        movies.add(movie);

        mAdapter.notifyDataSetChanged();

    }

}
