package Entity;

public enum Type {
    WITHDRAWAL("WITHDRAWAL"), DEPOSIT("DEPOSIT");
    private String type;

    Type(String type) {
        this.type = type;
    }

    private String getType(){
        return this.type;
    }
}
