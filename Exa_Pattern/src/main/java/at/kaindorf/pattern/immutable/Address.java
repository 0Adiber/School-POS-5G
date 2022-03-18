package at.kaindorf.pattern.immutable;

public class Address {

    private String streetname;
    private int zipCode;

    public Address(String streetname, int zipCode) {
        this.streetname = streetname;
        this.zipCode = zipCode;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
