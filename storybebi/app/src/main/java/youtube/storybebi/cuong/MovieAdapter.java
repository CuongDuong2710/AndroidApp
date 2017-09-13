package youtube.storybebi.cuong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QUOC CUONG on 31/08/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context mContext;
    private List<HashMap<String, Object>> movies;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_video) ImageView image;
        @BindView(R.id.title_video) TextView title;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public MovieAdapter(Context context, List<HashMap<String, Object>> movies) {
        this.mContext = context;
        this.movies = movies;
    }

    /**
     * inflates movie_list_row.xml
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    /**
     * set data to each row
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // get hashmap record
        HashMap<String, Object> data = movies.get(position);

        // get each values by key
        String title = String.valueOf(data.get("title"));
        String lenght = String.valueOf(data.get("length"));
        String videoId = String.valueOf(data.get("videoId"));
        String imageUrl = String.valueOf(data.get("imageUrl"));

        // create new Movie
        Movie movie = new Movie(imageUrl, title, videoId, lenght);

        // loading album cover using Glide library
        Glide.with(mContext).load(movie.getImage()).into(holder.image);
        holder.title.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
