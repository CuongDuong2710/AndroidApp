package youtube.storybebi.cuong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QUOC CUONG on 31/08/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // A movie item view type
    private static final int MOVIE_ITEM_VIEW_TYPE = 0;

    // The Native Express view type
    private static final int AD_VIEW_TYPE = 1;

    // An Activity's context
    private Context mContext;

    // The list of Native Express and movie items
    private List<Object> mRecyclerViewItems;

    /**
     * The {@Link MovieItemViewHolder} class.
     * Provides a reference to each view in the movie item view.
     */
    public class MovieItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_video) ImageView image;
        @BindView(R.id.title_video) TextView title;
        @BindView(R.id.duration_video) TextView duration;

        public MovieItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class NativeExpressAdViewHolder extends RecyclerView.ViewHolder {
        NativeExpressAdViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 8 == 0) ? AD_VIEW_TYPE : MOVIE_ITEM_VIEW_TYPE;
    }

    public MovieAdapter(Context context, List<Object> movies) {
        this.mContext = context;
        this.mRecyclerViewItems = movies;
    }

    /**
     * inflates movie_list_row.xml or native_express_ad_cointainer.xml
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case AD_VIEW_TYPE:
                View nativeExpressLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.native_express_ad_cointainer, parent, false);
                return new NativeExpressAdViewHolder(nativeExpressLayoutView);
            case MOVIE_ITEM_VIEW_TYPE:
            default:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.movie_list_row, parent, false);
                return new MovieItemViewHolder(itemView);
        }
    }

    /**
     * set data to each row
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case AD_VIEW_TYPE:
                NativeExpressAdViewHolder nativeExpressHolder = (NativeExpressAdViewHolder) holder;
                NativeExpressAdView adView = (NativeExpressAdView) mRecyclerViewItems.get(position);

                // Get a reference to the CardView that going to hold my hand.
                // This is the CardView from that layout file I made, which is kept as the 'itemView' by the ViewHolder
                ViewGroup adCardView = (ViewGroup) nativeExpressHolder.itemView;

                // And once I've got a hold of that CardView, I can remove any other NativeExpressAdView
                adCardView.removeAllViews();

                // And I can also check the parent of the NativeExpressAdView to make sure it's not
                // in the hierachy of any of the other cards.
                if (adView.getParent() != null) {
                    ((ViewGroup)adView.getParent()).removeView(adView);
                }

                // And now that my ad is fully detached and I know the CardView is empty, I can pop in the ad.
                // And my ViewHolder is ready to be returned and viewed by the user.
                adCardView.addView(adView);
                break;
            case MOVIE_ITEM_VIEW_TYPE:
            default:
                // cast holder to MovieItemViewHolder
                MovieItemViewHolder myViewHolder = (MovieItemViewHolder) holder;

                // get hashmap record
                HashMap<String, Object> data = (HashMap<String, Object>) mRecyclerViewItems.get(position);

                // get each values by key
                String title = String.valueOf(data.get("title"));
                String lenght = String.valueOf(data.get("length"));
                String videoId = String.valueOf(data.get("videoId"));
                //int position = videoUrl.lastIndexOf("/");
                //videoUrl.substring(position + 1).trim())
                String imageUrl = String.valueOf(data.get("imageUrl"));
                String description = String.valueOf(data.get("description"));

                // create new Movie
                Movie movie = new Movie(imageUrl, title, videoId, lenght, description);

                // add movie details to the movie item view
                Glide.with(mContext).load(movie.getImage()).into(myViewHolder.image); // loading album cover using Glide library
                myViewHolder.title.setText(movie.getTitle());
                myViewHolder.duration.setText(movie.getLength());
                break;
        }
    }
}
