public class Good {
    public static int getCurrentId() {
        return currentId;
    }

    private static int currentId;

    private int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private String name;

    public Good(int id, String name) {
        this.id = id;
        this.name = name;
        currentId++;
    }
}
