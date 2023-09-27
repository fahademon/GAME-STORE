package sample;

import java.time.LocalDate;
import java.util.*;

public class Title extends Displayable
{


    private String name;
    private LocalDate releaseDate;
    private String description;
    private String developer;
    private String platform;
    private ArrayList<String> genre;
    private HashSet<Key> keys;
    private Double rating;
    private Double price;
    private boolean exists = true;
    private final PersistenceDBHandler persistenceDBHandler = MySQLHandler.getInstance();
    //Image


    public Title()
    {
        name = "";
        releaseDate = LocalDate.EPOCH;
        description = "";
        platform = "";
        genre = new ArrayList<>();
        keys = new HashSet<>();
        rating = 0.0;
        price = 0.0;

    }

    Title(String n, LocalDate d, String desc, String dev, ArrayList<String> g, String plat, Double r, Double p)
    {
        name = n;
        releaseDate = d;
        description = desc;
        developer = dev;
        genre = new ArrayList<>();
        keys = new HashSet<>();
        genre.addAll(g);
        platform = plat;
        rating = r;
        price = p;
    }
    Title(String n, LocalDate d, String desc, String dev, String plat, Double r, Double p)
    {
        name = n;
        releaseDate = d;
        description = desc;
        developer = dev;
        platform = plat;
        rating = r;
        price = p;
        genre = new ArrayList<>();
        keys = new HashSet<>();

    }

    public Title(String name, String developer, String platform) {
        this.name = name;
        this.developer = developer;
        this.platform = platform;
    }

    Title(String n, LocalDate d, String desc, String dev, String plat, Double r, Double p, boolean exists)
    {
        name = n;
        releaseDate = d;
        description = desc;
        developer = dev;
        platform = plat;
        rating = r;
        price = p;
        genre = new ArrayList<>();
        keys = new HashSet<>();
        this.exists = exists;
    }

    @Override
    public boolean equals(Object o) {
        Title title = (Title) o;
        return name.equals(title.name) && developer.equals(title.developer) && platform.equals(title.platform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, developer, platform);
    }

    public Title(Title title)
    {
        name = title.name;
        releaseDate = title.releaseDate;
        description = title.description;
        developer = title.developer;
        platform = title.platform;
        rating = title.rating;
        price = title.price;
        genre = new ArrayList<>();
        genre.addAll(title.getGenre());
        keys = new HashSet<>();
        keys.addAll(title.getKeys());
    }

    public String getName()
    {
        return name;
    }

    public String getDeveloper()
    {
        return developer;
    }

    public LocalDate getReleaseDate()
    {
        return releaseDate;
    }

    public String getPlatform()
    {
        return platform;
    }

    public String getDescription()
    {
        return description;
    }

    public Double getRating()
    {
        return rating;
    }

    public Double getPrice()
    {
        return price;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public void setDeveloper(String developer)
    {
        this.developer = developer;
    }

    public void setReleaseDate(LocalDate releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public void setPlatform(String platform)
    {
        this.platform = platform;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setRating(Double rating)
    {
        this.rating = rating;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }


    public void addGenre(String g)
    {
        genre.add(g);
    }

    public HashSet<Key> getKeys() {
        return keys;
    }
    public void addKey(Key key){keys.add(key);}

    public void removeKey(Key key){keys.remove(key);}



    public void updateGenres(ArrayList<String> genres)
    {
        genre.clear();
        genre.addAll(genres);
    }

    public String getGenreString() {
        String tempGenres = "";
        for(String i: genre)
            tempGenres += i + " ";
        return tempGenres;
    }

    public Key popKey(){
        if (keys.size() == 0) {
            return null;
        }
        Key key = keys.iterator().next();
        keys.remove(key);
        return key;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public boolean doesExist() {
        return exists;
    }

    public ArrayList<String> getKeysStrings()
    {
        ArrayList<String> keyList = new ArrayList<>();
        for(Key i: keys)
        {
            keyList.add(i.getValue());
        }
        return keyList;
    }
}