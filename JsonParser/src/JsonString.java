public class JsonString implements  JsonElement{
    String value;

    JsonString(String value) {
        this.value = value;
    }
    @Override
    public Object getvalue() {
        return value;
    }
}
