package service;

import java.util.List;
import model.*;
import singlePlayer.SinglePlayerALL;


/**
 * Created by Florian on 13.06.2017.
 * Test Klasse zum Fragen erstellen
 */
public class FragenService {
    public List<Level> getFragen(int anzahl, int resource){
        return new SinglePlayerALL().SinglePlayerStart(anzahl, resource);
    }

}
