package it.smellsliketeamspirit.testmarvel.entities;

public class Creator {

    private String resURI;
    private String name;
    private String role;

    public Creator(String resURI, String name, String role) {
        this.resURI = resURI;
        this.name = name;
        this.role = role;
    }

    public String getResURI() {
        return resURI;
    }

    public void setResURI(String resURI) {
        this.resURI = resURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
