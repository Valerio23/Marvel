package it.smellsliketeamspirit.testmarvel.entities;

import java.util.ArrayList;

public class Story {

    private String id;
    private String title;
    private String resURI;
    private String type;
    private ArrayList<Creator> creators;
    private ArrayList<String> charactersName;
    private String collectionURICharacters;
    private String collectionURISeries;
    private String collectionURIComics;
    private String collectionURIEvents;
    private String originalIssueResURI;
    private String originalIssueName;

    public Story() {

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

    public String getResURI() {
        return resURI;
    }

    public void setResURI(String resURI) {
        this.resURI = resURI;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Creator> getCreators() {
        return creators;
    }

    public void setCreators(ArrayList<Creator> creators) {
        this.creators = creators;
    }

    public ArrayList<String> getCharactersName() {
        return charactersName;
    }

    public void setCharactersName(ArrayList<String> charactersName) {
        this.charactersName = charactersName;
    }

    public String getCollectionURISeries() {
        return collectionURISeries;
    }

    public void setCollectionURISeries(String collectionURISeries) {
        this.collectionURISeries = collectionURISeries;
    }

    public String getCollectionURIComics() {
        return collectionURIComics;
    }

    public void setCollectionURIComics(String collectionURIComics) {
        this.collectionURIComics = collectionURIComics;
    }

    public String getCollectionURIEvents() {
        return collectionURIEvents;
    }

    public void setCollectionURIEvents(String collectionURIEvents) {
        this.collectionURIEvents = collectionURIEvents;
    }

    public String getOriginalIssueResURI() {
        return originalIssueResURI;
    }

    public void setOriginalIssueResURI(String originalIssueResURI) {
        this.originalIssueResURI = originalIssueResURI;
    }

    public String getOriginalIssueName() {
        return originalIssueName;
    }

    public void setOriginalIssueName(String originalIssueName) {
        this.originalIssueName = originalIssueName;
    }

    public String getCollectionURICharacters() {
        return collectionURICharacters;
    }

    public void setCollectionURICharacters(String collectionURICharacters) {
        this.collectionURICharacters = collectionURICharacters;
    }
}
