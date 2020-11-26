package data;

public class DataBean {
    private Republic republic;
    public DataBean() {
        republic = new Republic();
        this.setUp();
    }

    public Republic getRepublic() {
        return this.republic;
    }


    private void setUp() {
        republic.addState("Burgenland",1, 208, 27);
        republic.addState("Kärnten", 2, 391, 60);
        republic.addState("Niederösterreich", 3, 1217, 197);
        republic.addState("Oberösterreich", 4, 1250, 150);
        republic.addState("Salzburg",5,276, 45);
        republic.addState("Steiermark", 6, 2809, 287);
        republic.addState("Tirol", 7, 456, 70);
        republic.addState("Vorarlberg", 8, 484, 52);
        republic.addState("Wien", 9, 3200, 397);
        republic.addState("Österreich", 10, 10271, 1185);
    }
}
