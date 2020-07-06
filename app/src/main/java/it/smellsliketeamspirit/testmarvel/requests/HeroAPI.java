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

import it.smellsliketeamspirit.testmarvel.entities.Hero;
import it.smellsliketeamspirit.testmarvel.entities.MarvelImage;

public abstract class HeroAPI implements Response.Listener<String> {

    private static final String TIME_STAMP = "1"; // NON TOCCARE QUESTO!!!
    private static final String APIKEY = "fed0a168e2fc7c65424b14a30b89b358";
    private static final String HASH = "671fc2069018eab3f9c5fe6549ba864a";
    private RequestQueue requestQueue;

    public abstract void fillLayout(List<Hero> heroes);

    protected HeroAPI(Context context) {
        Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024); // 10 MB cache
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    public void searchHeroes() {
        String url = "https://gateway.marvel.com/v1/public/characters?ts=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, APIKEY, HASH);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    public void searchHeroesByName(String s) {
        String url = "https://gateway.marvel.com/v1/public/characters?ts=%s&name=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, s, APIKEY, HASH);
        Log.w("TAG_M", s);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    public void searchHeroesByNameStartsWith(String s) {
        String url = "https://gateway.marvel.com/v1/public/characters?ts=%s&nameStartsWith=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, s, APIKEY, HASH);
        Log.w("TAG_M", s);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResponse(String response) {

        ArrayList<Hero> heroes = new ArrayList<>();

        try {
            JSONObject jsonObjectResponse = new JSONObject(response);
            JSONObject jsonObjectData = jsonObjectResponse.getJSONObject("data");
            JSONArray jsonArrayResults = jsonObjectData.getJSONArray("results");

            for (int i=0; i < jsonArrayResults.length(); i++) {

                JSONObject object = jsonArrayResults.getJSONObject(i);

                Hero hero = new Hero();
                hero.setId(object.getString("id"));
                hero.setName(object.getString("name"));
                hero.setDescription(object.getString("description"));
                hero.setResURI(object.getString("resourceURI"));

                JSONObject jsonObjectThumbnail = object.getJSONObject("thumbnail");
                hero.setImgHero(new MarvelImage(jsonObjectThumbnail.getString("path"), jsonObjectThumbnail.getString("extension")));

                JSONObject jsonObjectComics = object.getJSONObject("comics");
                hero.setCollectionURIComics(jsonObjectComics.getString("collectionURI"));

                JSONObject jsonObjectSeries = object.getJSONObject("series");
                hero.setCollectionURISeries(jsonObjectSeries.getString("collectionURI"));

                JSONObject jsonObjectStories = object.getJSONObject("stories");
                hero.setCollectionURIStories(jsonObjectStories.getString("collectionURI"));

                JSONObject jsonObjectEvents = object.getJSONObject("events");
                hero.setCollectionURIEvents(jsonObjectEvents.getString("collectionURI"));

                heroes.add(hero);
            }

            fillLayout(heroes);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
