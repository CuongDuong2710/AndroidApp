package youtube.storybebi.cuong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyCnA-6rmPWV--SGIN6Bept0_XJc8KxPG4I";

    private TextView txt_title;
    private String videoId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        getData();

        // Initializing YouTube Player View
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        // add listener to YouTubePlayer instance
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        // Start buffering
        if (!wasRestored) {
            youTubePlayer.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize", Toast.LENGTH_SHORT).show();
    }

    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(ErrorReason errorReason) {

        }
    };

    private void getData() {
        Intent intent = getIntent();

        Bundle data = intent.getBundleExtra("data");
        String title = (String) data.get("title");
        videoId = (String) data.get("videoId");

    }
}
