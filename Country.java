//this class creates objects that represent countries and each object has a name and a flag
public class Country {
    //looked at Card class from hw07 / hw08 and changed accordingly
    private String name; // instance variables
    private String flag;
    public Country(String name, String flag) { //constructor
        this.name = name;
        this.flag = flag;
    }
    public String getName() {
        return name;
    }
    public String getFlag() {
        return flag;
    }



}
