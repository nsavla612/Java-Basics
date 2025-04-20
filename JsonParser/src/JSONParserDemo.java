public class JSONParserDemo {
    public static void main( String[] args) {
        String jsonString = "{ \"name\": \"John\", \"age\": 20, \"city\": \"London York\", \"isAdmin\": \"true\", \"scores\": [10, 20, 30] }";
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonString);
        System.out.println( jsonElement.getvalue());
    }
}
