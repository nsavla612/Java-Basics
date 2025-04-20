public class JsonNumber implements JsonElement{
    Number value;

    JsonNumber(Number value) {
        this.value = value;
    }
    @Override
    public Object getvalue() {
        return value;
    }
}
