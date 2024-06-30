package Entity;

public class Customer {
    private String id;
    private String name;
    private String phone;

    public Customer() {
        ;
    }

    public Customer(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer getCustomer(Customer customer) {
        return customer;
    }

    public String customerToLine(String separator) {
        StringBuilder builder = new StringBuilder();
        return builder
                .append(id)
                .append(separator)
                .append(name)
                .append(separator)
                .append(phone)
                .append(separator)
                .toString();
    }
}
