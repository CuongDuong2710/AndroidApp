package youtube.storybebi.cuong;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by QUOC CUONG on 31/08/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movie> movies;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image_video);
            title = view.findViewById(R.id.title_video);
        }
    }

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.image.setImageResource(movie.getImage());
        holder.title.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
