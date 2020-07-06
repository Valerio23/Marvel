package it.smellsliketeamspirit.testmarvel.entities;

public class Hero {

    private String id;
    private String name;
    private String description;
    private String resURI;
    private MarvelImage imgHero;
    private String collectionURIComics;
    private String collectionURISeries;
    private String collectionURIStories;
    private String collectionURIEvents;

    public Hero() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResURI() {
        return resURI;
    }

    public void setResURI(String resURI) {
        this.resURI = resURI;
    }

    public MarvelImage getImgHero() {
        return imgHero;
    }

    public void setImgHero(MarvelImage imgHero) {
        this.imgHero = imgHero;
    }

    public String getCollectionURIComics() {
        return collectionURIComics;
    }

    public void setCollectionURIComics(String collectionURI) {
        this.collectionURIComics = collectionURI;
    }

    public String getCollectionURISeries() {
        return collectionURISeries;
    }

    public void setCollectionURISeries(String collectionURISeries) {
        this.collectionURISeries = collectionURISeries;
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
