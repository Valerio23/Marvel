package it.smellsliketeamspirit.testmarvel.entities;

import java.util.ArrayList;

public class Serie {

    private String id;
    private String title;
    private String description;
    private String resURI;
    private int startYear;
    private int endYear;
    private String type;
    private MarvelImage marvelImage;
    private ArrayList<Creator> creators;
    private ArrayList<String> charactersName;
    private String collectionURICharacters;
    private String collectionURIStories;
    private String collectionURIComics;
    private String collectionURIEvents;
    private String nextSerie;
    private String prevSerie;

    public Serie() {

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

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public ArrayList<String> getCharactersName() {
        return charactersName;
    }

    public void setCharactersName(ArrayList<String> charactersName) {
        this.charactersName = charactersName;
    }

    public String getCollectionURICharacters() {
        return collectionURICharacters;
    }

    public void setCollectionURICharacters(String collectionURICharacters) {
        this.collectionURICharacters = collectionURICharacters;
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

    public String getCollectionURIEvents() {
        return collectionURIEvents;
    }

    public void setCollectionURIEvents(String collectionURIEvents) {
        this.collectionURIEvents = collectionURIEvents;
    }

    public String getNextSerie() {
        return nextSerie;
    }

    public void setNextSerie(String nextSerie) {
        this.nextSerie = nextSerie;
    }

    public String getPrevSerie() {
        return prevSerie;
    }

    public void setPrevSerie(String prevSerie) {
        this.prevSerie = prevSerie;
    }
}
