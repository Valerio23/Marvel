package it.smellsliketeamspirit.testmarvel.entities;

public class MarvelImage {

    private String path;
    private String ext;

    public MarvelImage(String path, String ext) {
        this.path = path;
        this.ext = ext;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFullPath() {
        String fullPath = path + "/standard_medium" + "." + ext;
        return fullPath;
    }

}
