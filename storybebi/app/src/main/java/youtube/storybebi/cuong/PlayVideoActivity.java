package youtube.storybebi.cuong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PlayVideoActivity extends AppCompatActivity {
    private TextView txt_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        Intent intent = getIntent();

        Bundle data = intent.getBundleExtra("data");
        String title = (String) data.get("title");

        txt_title = (TextView) findViewById(R.id.title_video_play);
        txt_title.setText(title);
    }
}
