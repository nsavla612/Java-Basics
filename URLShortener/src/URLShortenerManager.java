public class URLShortenerManager {
    int capacity ;
    DatabaseManager databaseManager;
    public URLShortenerManager(int capacity) {
        databaseManager = new DatabaseManager(capacity);
    }

    public String add( String longURL) {
        System.out.println(" adding " + longURL + " into system ");
        return databaseManager.add(longURL);
    }

    public String get( String shortURL) {
        System.out.println(" getting value of " + shortURL + " from system ");
        return databaseManager.get(shortURL);
    }

    public boolean remove( String shortURL) {
        System.out.println(" removing " + shortURL + " from system ");
        return databaseManager.remove(shortURL);
    }
}
