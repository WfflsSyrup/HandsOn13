package model;

public class Record {
    private final String id;
    private String name;
    private int quantity;

    public Record(String id, String name, int quantity) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Record ID cannot be blank.");
        }
        this.id = id.trim();
        setName(name);
        setQuantity(quantity);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Record name cannot be blank.");
        }
        this.name = name.trim();
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.quantity = quantity;
    }
}