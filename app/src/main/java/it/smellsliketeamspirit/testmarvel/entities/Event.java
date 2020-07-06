package it.smellsliketeamspirit.testmarvel.entities;

import java.util.ArrayList;

public class Event {

    private String id;
    private String title;
    private String description;
    private String resURI;
    private MarvelImage marvelImage;
    private ArrayList<Creator> creators;
    private String collectionURICharacters;
    private ArrayList<String> charactersName;
    private String collectionURIStories;
    private String collectionURIComics;
    private String collectionURISeries;
    private String nextEvent;
    private String prevEvent;

    public Event() {

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

    public String getResURI() {
        return resURI;
    }

    public void setResURI(String resURI) {
        this.resURI = resURI;
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

    public String getCollectionURIComics() {
        return collectionURIComics;
    }

    public void setCollectionURIComics(String collectionURIComics) {
        this.collectionURIComics = collectionURIComics;
    }

    public String getCollectionURISeries() {
        return collectionURISeries;
    }

    public void setCollectionURISeries(String collectionURISeries) {
        this.collectionURISeries = collectionURISeries;
    }

    public String getNextEvent() {
        return nextEvent;
    }

    public void setNextEvent(String nextEvent) {
        this.nextEvent = nextEvent;
    }

    public String getPrevEvent() {
        return prevEvent;
    }

    public void setPrevEvent(String prevEvent) {
        this.prevEvent = prevEvent;
    }
}
