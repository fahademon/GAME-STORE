package sample;

import java.util.ArrayList;
import java.util.Date;

public class BrowseFilter extends Filter{

    private static BrowseFilter instance = null;
    private Double rating;
    private ArrayList<String> genres;
    private ArrayList<String> platforms;
    private Double maxPrice;
    private SortBy sortBy;

    private BrowseFilter()
    {
        genres = new ArrayList<>();
        platforms = new ArrayList<>();
        rating = 0.0;
        maxPrice = 500000.0;
        sortBy = SortBy.DATE;
    }
    public static BrowseFilter getInstance()
    {
        if(instance == null)
            instance = new BrowseFilter();
        return instance;
    }
    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    private Date releaseDate;


    public double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(ArrayList<String> platforms) {
        this.platforms = platforms;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void addGenre(String genre)
    {
        if(!genres.contains(genre))
            genres.add(genre);
    }
    public void removeGenre(String genre)
    {
        genres.remove(genre);
    }


    public void addPlatform(String platform) {
        if(!platforms.contains(platform))
            platforms.add(platform);
    }

    public void removePlatform(String platform) {
        platforms.remove(platform);
    }

}
