package codepath.com.parseservertest;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("GameScore")
public class GameScore extends ParseObject {
    public String getPlayerName() {
        return getString("playerName");
    }
}
