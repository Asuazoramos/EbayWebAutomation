package data.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HashMapPractice {

    public HashMap<String, Integer> map;

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

    //pass an empty map, ang get the same instance modified with data. ideally coming from some
    //sort of data importing.
    public HashMap<String, Integer> populateMap(HashMap<String, Integer> mapToPopulate) {

        for (int i = 0; i < 20; i++) {
            int numberId = new Random().nextInt(200);
            mapToPopulate.put("Employee #" + i + ", Id", numberId);
        }

        return mapToPopulate;
    }

    public void displayMapValues(){
        setMap(new HashMap<>());
        HashMap<String, Integer> theMap = populateMap(getMap());

        for (Map.Entry<String, Integer> entry : map.entrySet()
             ) {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        }
    }
}
