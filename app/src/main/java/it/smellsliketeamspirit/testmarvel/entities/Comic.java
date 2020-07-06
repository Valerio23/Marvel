package it.smellsliketeamspirit.testmarvel.entities;

import java.util.ArrayList;

public class Comic {

    private String id;
    private String title;
    private String description;
    private String format;
    private int pageCount;
    private String resURI;
    private String seriesResURI;
    private String seriesName;
    private int price;
    private MarvelImage marvelImage;
    private ArrayList<Creator> creators;
    private String collectionURICharacters;
    private ArrayList<String> charactersName;
    private String collectionURIStories;
    private String collectionURIEvents;

    public Comic() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getResURI() {
        return resURI;
    }

    public void setResURI(String resURI) {
        this.resURI = resURI;
    }

    public String getSeriesResURI() {
        return seriesResURI;
    }

    public void setSeriesResURI(String seriesResURI) {
        this.seriesResURI = seriesResURI;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MarvelImage getMarvelImage() {
        return marvelImage;
    }

    public void setMarvelImage(MarvelImage marvelImage) {
        this.marvelImage = marvelImage;
    }

    public ArrayList<Creator> getCreators() {
        return creators;
    }

    public void setCreators(ArrayList<Creator> creators) {
        this.creators = creators;
    }

    public String getCollectionURICharacters() {
        return collectionURICharacters;
    }

    public void setCollectionURICharacters(String collectionURICharacters) {
        this.collectionURICharacters = collectionURICharacters;
    }

    public ArrayList<String> getCharactersName() {
        return charactersName;
    }

    public void setCharactersName(ArrayList<String> charactersName) {
        this.charactersName = charactersName;
    }

    public String getCollectionURIStories() {
        return collectionURIStories;
    }

    public void setCollectionURIStories(String collectionURIStories) {
        this.collectionURIStories = collectionURIStories;
    }

    public String getCollectionURIEvents() {
        return collectionURIEvents;
    }

    public void setCollectionURIEvents(String collectionURIEvents) {
        this.collectionURIEvents = collectionURIEvents;
    }
}
