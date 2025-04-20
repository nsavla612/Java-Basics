import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {
    int index;
    String json;

    private static final char OPEN_CURLY_BRACE = '{';
    private static final char CLOSE_CURLY_BRACE = '}';
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final char DOUBLE_QUOTE = '"';
    private static final char COLON = ':';
    private static final char COMMA = ',';

    public JsonElement parse( String jsonString) {
        this.index = 0;
        this.json = jsonString;
        skipWhiteSpaces();
        return  parseValue();
    }

    private JsonElement parseValue() {
        char currentChar = json.charAt(index);
        if(currentChar == OPEN_CURLY_BRACE) {
            return parseObject();
        } else if( currentChar == OPEN_SQUARE_BRACKET) {
            return parseArray();
        } else if ( currentChar == DOUBLE_QUOTE) {
            return parseString();
        } else if( Character.isDigit(currentChar) || currentChar == '-') {
            return parseNumber();
        } else if(currentChar == 'n') {
            return parseNull();
        }

        throw new RuntimeException(" Invalid JSON");
    }

    private JsonElement parseObject() {
        Map<String, JsonElement> properties = new HashMap<>();
        consume(OPEN_CURLY_BRACE);
        skipWhiteSpaces();
        while(json.charAt(index) != CLOSE_CURLY_BRACE) {
            String propertyName = parseString().getvalue().toString();
            skipWhiteSpaces();
            consume(COLON);
            skipWhiteSpaces();

            JsonElement propertyValue = parseValue();
            properties.put(propertyName, propertyValue);
            skipWhiteSpaces();

            if(json.charAt(index) == COMMA) {
                consume(COMMA);
                skipWhiteSpaces();
            }
        }
        consume(CLOSE_CURLY_BRACE);
        return new JsonObject(properties);
    }

    private JsonArray parseArray() {
        List<JsonElement> elements = new ArrayList<>();
        consume(OPEN_SQUARE_BRACKET);
        skipWhiteSpaces();
        while( json.charAt(index) != CLOSE_SQUARE_BRACKET) {
            JsonElement element = parseValue();
            elements.add(element);
            skipWhiteSpaces();
            if(json.charAt(index) == COMMA) {
                consume(COMMA);
                skipWhiteSpaces();
            }
        }
        consume(CLOSE_SQUARE_BRACKET);
        return new JsonArray(elements);
    }

    private JsonNumber parseNumber() {
        int startIndex = index;
        while(Character.isDigit(json.charAt(index)) || json.charAt(index)== '.') {
            index++;
        }
        String numberStr = json.substring(startIndex, index);
        if(numberStr.contains(".")) {
            return new JsonNumber(Double.parseDouble(numberStr));
        } else {
            return new JsonNumber(Long.parseLong(numberStr));
        }
    }
    private JsonElement parseNull() {
        consumeWord();
        return null;
    }

    private String consumeWord() {
        StringBuilder sb = new StringBuilder();
        while(Character.isLetter(json.charAt(index))) {
            sb.append(json.charAt(index));
            index++;
        }
        return sb.toString();
    }

    private JsonString parseString() {
        consume(DOUBLE_QUOTE);
        StringBuilder sb = new StringBuilder();
        while( json.charAt(index) != DOUBLE_QUOTE) {
            sb.append(json.charAt(index));
            index++;
        }
        consume(DOUBLE_QUOTE);
        return new JsonString(sb.toString());
    }

    private void skipWhiteSpaces() {
        while(Character.isWhitespace(json.charAt(index))) index++;
    }

    private void consume(char expected) {
        if(json.charAt(index) == expected) index++;
        else throw new RuntimeException(" Expected: " + expected);
    }
}