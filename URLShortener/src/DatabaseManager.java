
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseManager {
    Map<String, String> shortToLong ;
    DatabaseManager( int capacity) {
        shortToLong = new ConcurrentHashMap<>(capacity);
    }

    public String add( String longURL) {
        String shortURL = generateShortURL(longURL);
        shortToLong.put(shortURL, longURL);
        return shortURL;
    }

    public String get( String shortURL) {
        if(!shortToLong.containsKey(shortURL))
            throw new IllegalArgumentException("shortURL does not exist in map");
        return shortToLong.get(shortURL);
    }

    public boolean remove( String shortURL) {
        if(!shortToLong.containsKey(shortURL))
            throw new IllegalArgumentException("shortURL does not exist in map");
        shortToLong.remove(shortURL);
        return true;
    }

    private String generateShortURL( String longURL ) {
        // return UUID.randomUUID();
        return String.valueOf(longURL.hashCode());
    }
}
