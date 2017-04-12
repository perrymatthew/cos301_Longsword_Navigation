public class Waypoint {

    private String name;
    private Double co_ord;
    private Boolean disabled;
    private Boolean operational;

    public Waypoint(){
        name = "";
        co_ord = 0.0;
        disabled = false;
        operational = true;
    }

    public void setNode(String n, Double co_ords, Boolean dis, Boolean available){
        name = n;
        co_ord = co_ords;
        disabled = dis;
        operational = available;
    }

    public String getName() {
        return name;
    }

    public Double getCo_Ord() {
        return co_ord;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public Boolean getOperational() {
        return operational;
    }
}