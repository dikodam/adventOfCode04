import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adam on 07.12.2016.
 */
public class Room {

    private String name;
    private int id;
    private String checksum;

    /**
     * generates a Room with a valid checksum
     */
    public Room(String name, int id) {
        this(name, id, generateChecksum(name));
    }


    public Room(String name, int id, String checksum) {
        this.name = name;
        this.id = id;
        this.checksum = checksum;
    }

    private static String generateChecksum(String name) {
        Map<Character, Integer> charCounts = new HashMap<>();
        // count all alphabetical characters [a-z]
        for (int i = 0; i < name.length(); i++) {
            Character c = new Character(name.charAt(i));
            Integer count = charCounts.get(c);
            if (count == null) {
                charCounts.put(c, 1);
            } else {
                charCounts.put(c, count + 1);
            }
        }
        // sort them by 1. quantity 2. alphabet
        // return first 5
        StringBuilder sb = new StringBuilder("");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return ((Room)obj).getChecksum() == checksum;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getChecksum(){
        return checksum;
    }
}
