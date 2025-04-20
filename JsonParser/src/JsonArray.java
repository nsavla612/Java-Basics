import java.util.List;
import java.util.stream.Collectors;

public class JsonArray implements  JsonElement{
    List<JsonElement> elements;

    JsonArray(  List<JsonElement> elements) {
        this.elements = elements;
    }
    @Override
    public Object getvalue() {
        return elements.stream().map( JsonElement::getvalue).collect(Collectors.toList());
    }
}
