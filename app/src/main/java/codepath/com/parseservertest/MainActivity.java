package codepath.com.parseservertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.parse.ParseLiveQueryClient;
import com.parse.ParseQuery;
import com.parse.SubscriptionHandling;

public class MainActivity extends AppCompatActivity {


    TextView tvMessage;
    String player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMessage = findViewById(R.id.tvMessage);


//        // Make sure the Parse server is setup to configured for live queries
//        // URL for server is determined by Parse.initialize() call.
        ParseLiveQueryClient parseLiveQueryClient = ParseLiveQueryClient.Factory.getClient();
//
        ParseQuery<GameScore> query = ParseQuery.getQuery(GameScore.class);
        // This query can even be more granular (i.e. only refresh if the entry was added by some other user)
        // parseQuery.whereNotEqualTo(USER_ID_KEY, ParseUser.getCurrentUser().getObjectId());
//
//        // Connect to Parse server]
        SubscriptionHandling<GameScore> subscriptionHandling = parseLiveQueryClient.subscribe(query);
//
       subscriptionHandling.handleEvent(SubscriptionHandling.Event.CREATE, new SubscriptionHandling.HandleEventCallback<GameScore>() {
            @Override
            public void onEvent(ParseQuery<GameScore> query, GameScore object) {
                player = object.getPlayerName();
                tvMessage.setText(player);
            }
        });


    }
}



