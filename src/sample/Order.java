package sample;

import java.util.ArrayList;

public class Order {
    private ArrayList<Title> titles;
    private Integer orderNumber;
    private Double total;

    Order()
    {
        titles = new ArrayList<>();
    }
    Order(Cart cart)
    {
        titles = new ArrayList<>();
        for(CartItem i: cart.getItems())
        {
            Title titleForList, titleToAdd = i.getTitle();
            if((titleForList = titles.stream().filter(title -> title.equals(i.getTitle())).findFirst().orElse(null)) != null)
            {
                titleForList.addKey(i.getKey());
            }
            else
            {
                titleForList = new Title(titleToAdd.getName(), titleToAdd.getReleaseDate(), titleToAdd.getDescription(), titleToAdd.getDeveloper(), titleToAdd.getGenre(), titleToAdd.getPlatform(), titleToAdd.getPrice(), titleToAdd.getRating());
                titles.add(titleForList);
            }
        }
        total = cart.getTotal();
    }



    public ArrayList<Title> getTitles() {
        return titles;
    }
    public void setTitles(ArrayList<Title> list) {
        this.titles.addAll(list);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
