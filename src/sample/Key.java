package sample;

public class Key extends Displayable {
    private String key;

    public Key(String k)
    {
        key = k;
    }

    public void setValue(String k)
    {key = k;}
    public String getValue()
    {
        return key;
    }


}
