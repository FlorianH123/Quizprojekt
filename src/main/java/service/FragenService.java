package service;

import java.util.ArrayList;
import java.util.List;
import model.Frage;
import singlePlayer.SinglePlayer10;

import javax.ws.rs.Path;

/**
 * Created by Florian on 13.06.2017.
 * Test Klasse zum Fragen erstellen
 */
public class FragenService {
   //TODO Lösche diese Klassse
    public List<Frage> getFragen() {
        /*
        Frage f1 = new Frage ("test", "a", "b", "c", "d");
        Frage f2 = new Frage ("test2", "f", "g", "h", "i");
        List<Frage> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        return list;
        */
        SinglePlayer10 sp = new SinglePlayer10();
        List<Frage> list = sp.getList();
        return list;
    }

}
