package it.smellsliketeamspirit.testmarvel.requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import it.smellsliketeamspirit.testmarvel.entities.Creator;
import it.smellsliketeamspirit.testmarvel.entities.MarvelImage;
import it.smellsliketeamspirit.testmarvel.entities.Serie;

public abstract class SerieAPI implements Response.Listener<String> {

    private static final String TIME_STAMP = "1"; // NON TOCCARE QUESTO!!!
    private static final String APIKEY = "fed0a168e2fc7c65424b14a30b89b358";
    private static final String HASH = "671fc2069018eab3f9c5fe6549ba864a";
    private RequestQueue requestQueue;

    public abstract void fillLayout(List<Serie> series);

    protected SerieAPI(Context context) {
        Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024); // 10 MB cache
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    public void searchSeriesByTitle(String s) {
        String url = "https://gateway.marvel.com/v1/public/series?ts=%s&title=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, s, APIKEY, HASH);
        Log.w("TAG_M", s);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    public void searchSeriesByTitleStartsWith(String s) {
        String url = "https://gateway.marvel.com/v1/public/series?ts=%s&titleStartsWith=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, s, APIKEY, HASH);
        Log.w("TAG_M", s);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResponse(String response) {

        ArrayList<Serie> series = new ArrayList<>();
        ArrayList<Creator> creators = new ArrayList<>();
        ArrayList<String> charactersName = new ArrayList<>();

        try {
            JSONObject jsonObjectResponse = new JSONObject(response);
            JSONObject jsonObjectData = jsonObjectResponse.getJSONObject("data");
            JSONArray jsonArrayResults = jsonObjectData.getJSONArray("results");

            for (int i=0; i < jsonArrayResults.length(); i++) {

                JSONObject object = jsonArrayResults.getJSONObject(i);

                Serie serie = new Serie();
                serie.setId(object.getString("id"));
                serie.setTitle(object.getString("title"));
                serie.setDescription(object.getString("description"));
                serie.setResURI(object.getString("resourceURI"));
                serie.setStartYear(object.getInt("startYear"));
                serie.setEndYear(object.getInt("endYear"));
                serie.setType(object.getString("type"));

                JSONObject jsonObjectThumbnail = object.getJSONObject("thumbnail");
                serie.setMarvelImage(new MarvelImage(jsonObjectThumbnail.getString("path"), jsonObjectThumbnail.getString("extension")));

                JSONObject jsonObjectCreators = object.getJSONObject("creators");
                JSONArray jsonArrayCreators = jsonObjectCreators.getJSONArray("items");
                for (int j=0; j < jsonArrayCreators.length(); j++) {

                    JSONObject jsonObjectCreator = jsonArrayCreators.getJSONObject(j);
                    Creator creator = new Creator(
                            jsonObjectCreator.getString("resourceURI"),
                            jsonObjectCreator.getString("name"),
                            jsonObjectCreator.getString("role")
                    );

                    creators.add(creator);
                }
                serie.setCreators(creators);

                JSONObject jsonObjectCharacters = object.getJSONObject("characters");
                serie.setCollectionURICharacters(jsonObjectCharacters.getString("collectionURI"));
                JSONArray jsonArrayCharacters = jsonObjectCharacters.getJSONArray("items");
                for (int k=0; k < jsonArrayCharacters.length(); k++) {

                    JSONObject jsonObjectCharacter = jsonArrayCharacters.getJSONObject(k);
                    String characterName = jsonObjectCharacter.getString("name");
                    charactersName.add(characterName);
                }
                serie.setCharactersName(charactersName);

                JSONObject jsonObjectStories = object.getJSONObject("stories");
                serie.setCollectionURIStories(jsonObjectStories.getString("collectionURI"));

                JSONObject jsonObjectComics = object.getJSONObject("comics");
                serie.setCollectionURIComics(jsonObjectComics.getString("collectionURI"));

                JSONObject jsonObjectEvents = object.getJSONObject("events");
                serie.setCollectionURIEvents(jsonObjectEvents.getString("collectionURI"));

                JSONObject jsonObjectNextSerie = object.getJSONObject("next");
                serie.setNextSerie(jsonObjectNextSerie.getString("name"));

                JSONObject jsonObjectPrevSerie = object.getJSONObject("previous");
                serie.setPrevSerie(jsonObjectPrevSerie.getString("name"));

                series.add(serie);
            }

            fillLayout(series);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
