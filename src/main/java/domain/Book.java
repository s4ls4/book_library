package domain;
/*
author: Narita Marcel
 */

public class Book extends BaseEntity<Long>{
    private String serialNumber;
    private String name;
    private String author;
    private int price;

    public Book() {
    }

    public Book(String serialNumber, String name, int group,String a) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = group;
        this.author=a;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String a) {
        this.name = a;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String a) {
        this.author = a;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int group) {
        this.price = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!name.equals(book.name)) return false;
        if (!serialNumber.equals(book.serialNumber)) return false;
        return name.equals(book.name);
    }

    @Override
    public int hashCode() {
        int result = serialNumber.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "Book: " +
                "serialNumber - " + serialNumber + '\'' +
                "| name - '" + name + '\'' +
                "| author - " + author +
                "| price - " + price+
                "  " + super.toString();
    }
}
