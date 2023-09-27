package sample;

public class CartItem {

    private Title title;
    private Key key;

    CartItem(){}
    CartItem(Title t, Key k){
        title = t;
        key = k;
    }

    public Title getTitle()
    {return title;}
    public Key getKey()
    {return key;}


}
