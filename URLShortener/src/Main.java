public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        URLShortenerManager urlShortenerManager = new URLShortenerManager(5);
        String longURL1 = "www.google.com";
        String longURL2 = "www.app folio.com";
        String longURL3 = "www.amazon.com";

        String short1 = urlShortenerManager.add(longURL1);
        String longUrl = urlShortenerManager.get(short1);
        System.out.println(" longUrl- " + longUrl + " shorturl- " + short1);
        System.out.println();

        String short2 = urlShortenerManager.add(longURL2);
        longUrl = urlShortenerManager.get(short2);
        System.out.println(" longUrl- " + longUrl + " shorturl- " + short2);
        System.out.println();

        boolean result = urlShortenerManager.remove(short2);
        System.out.println(" result- " + result);

        urlShortenerManager.get(longURL3);
        System.out.println(" longUrl- " + longUrl);

    }
}