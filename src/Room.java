import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import javafx.collections.transformation.SortedList;
import javafx.util.Pair;

import javax.jnlp.IntegrationService;
import java.util.*;

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

    public static String generateChecksum(String name) {
        List<Map.Entry<Character, Integer>> charCounts = new ArrayList<>();

        Map<Character, Integer> usedChars = new HashMap<>();

        for (int i = 0; i < name.length(); i++) {
            Character c = new Character(name.charAt(i));
            if (c >= 97 && c <= 122) {
                Integer count = usedChars.get(c);
                if (count == null) {
                    usedChars.put(c, 1);
                } else {
                    usedChars.put(c, count + 1);
                }
            }
        }

        for (Map.Entry<Character, Integer> entry : usedChars.entrySet()) {
            charCounts.add(entry);
        }

        charCounts.sort((o1, o2) -> {
            int intComparison = o2.getValue().compareTo(o1.getValue());
            if (intComparison == 0) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return intComparison;
            }
        });

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 5; i++) {
            sb.append(charCounts.get(i).getKey());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return ((Room) obj).getChecksum() == checksum;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getChecksum() {
        return checksum;
    }
}
