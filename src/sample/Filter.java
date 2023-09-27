package sample;

public class Filter {
    private TimePeriod timePeriod;
    private String searchText;
    private String order;

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Filter()
    {
        timePeriod = TimePeriod.ALL_TIME;
        order = "asc";
        searchText = "";
    }
    public void setOrder(String ob) {
        order = ob;
    }
    public String getOrder() {
        return order;
    }
    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }


}
