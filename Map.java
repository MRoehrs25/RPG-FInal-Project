import java.util.HashMap;
import java.util.*;

public class Map {
    HashMap<String, Room> map;
    ArrayList<Item> items;

    public Map() {
        map = new HashMap<>();
        items = new ArrayList<Item>();
    }

    public void addRoom(Room room) {
        map.put(room.getName(), room);
    }

    public Room getRoom(String roomName) {
       return map.get(roomName);
    }

    public void addItem(Item thing) {
        items.add(thing);
    }
    
    public Item getItem(int num) {
        return items.get(num);
    }
}