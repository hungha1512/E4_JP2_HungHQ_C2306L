package Entity;

public enum Currency {
    USD("USD"), VND("VND");
    private String type;

    Currency(String type) {
        this.type = type;
    }

    private String getType() {
        return type;
    }
}
