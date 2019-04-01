package domain;

import java.util.ArrayList;

public class Author{
    private String name;
    private int ssn;

    public Author() {
    }

    public Author(String name, int ssn) {
        this.name = name;
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String a) {
        this.name = a;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }


    @Override
    public String toString() {
        return  name  +
                " - " + ssn;
    }
}
