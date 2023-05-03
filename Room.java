import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    String name;
    String des;
    int pow;
    HashMap<String, String> exits;
    ArrayList<String> ex;

    public Room (String name, String des, int pow) {
        this.name = name;
        this.des = des;
        this.pow = pow;
        exits = new HashMap<>();
        ex = new ArrayList<>();
    }

    public void addExit(String direction, String name) {
        exits.put(direction, name);
    }

    public void addEx(String exit) {
        ex.add(exit);
    }
    public ArrayList<String> getEx() {
        return ex;
    }

    public String listEx() {
        String exit = "";
        for (String temp: ex) {
            exit += temp + "\n";
        }
        return exit;
    }

    public String getName() {
        return name;
    }

    public void setDes() {
        this.des = "This room has been cleared and is empty There are exits:";
    }

    public String getDes() {
        return des;
    }

    public String getExit(String in) {
        return exits.get(in);
    }

    public String toString() {
        String fin = "";
        fin = getName() + " " + getDes();
        return fin;
    }
    
    public int getPow() {
        return pow;
    }


}