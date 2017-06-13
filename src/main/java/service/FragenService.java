package service;

/**
 * Created by Florian on 13.06.2017.
 */
import java.util.ArrayList;
import java.util.List;

import model.Frage;

public class FragenService {
    public List<Frage> getFragen() {
        Frage f1 = new Frage ("test", "a", "b", "c", "d");
        Frage f2 = new Frage ("test2", "f", "g", "h", "i");
        List<Frage> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        return list;
    }

}
