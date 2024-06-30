package Entity;

public enum Status {
    C("Completed"), P("Pending"), R("Reject");
    private String status;

    Status(String status) {
        this.status = status;
    }

    private String getStatus() {
        return this.status;
    }
}
