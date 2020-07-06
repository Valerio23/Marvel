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

import it.smellsliketeamspirit.testmarvel.entities.Comic;
import it.smellsliketeamspirit.testmarvel.entities.Creator;
import it.smellsliketeamspirit.testmarvel.entities.MarvelImage;

public abstract class ComicAPI implements Response.Listener<String> {

    private static final String TIME_STAMP = "1"; // NON TOCCARE QUESTO!!!
    private static final String APIKEY = "fed0a168e2fc7c65424b14a30b89b358";
    private static final String HASH = "671fc2069018eab3f9c5fe6549ba864a";
    private RequestQueue requestQueue;

    public abstract void fillLayout(List<Comic> comics);

    protected ComicAPI(Context context) {
        Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024); // 10 MB cache
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    public void searchComics() {
        String url = "https://gateway.marvel.com/v1/public/comics?ts=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, APIKEY, HASH);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    public void searchComicsByName(String s) {
        String url = "https://gateway.marvel.com/v1/public/comics?ts=%s&title=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, s, APIKEY, HASH);
        Log.w("TAG_M", s);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    public void searchComicsByTitleStartsWith(String s) {
        String url = "https://gateway.marvel.com/v1/public/comics?ts=%s&titleStartsWith=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, s, APIKEY, HASH);
        Log.w("TAG_M", s);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResponse(String response) {

        ArrayList<Comic> comics = new ArrayList<>();
        ArrayList<Creator> creators = new ArrayList<>();
        ArrayList<String> charactersName = new ArrayList<>();

        try {
            JSONObject jsonObjectResponse = new JSONObject(response);
            JSONObject jsonObjectData = jsonObjectResponse.getJSONObject("data");
            JSONArray jsonArrayResults = jsonObjectData.getJSONArray("results");

            for (int i=0; i < jsonArrayResults.length(); i++) {

                JSONObject object = jsonArrayResults.getJSONObject(i);

                Comic comic = new Comic();
                comic.setId(object.getString("id"));
                comic.setTitle(object.getString("title"));
                comic.setDescription(object.getString("variantDescription"));
                comic.setFormat(object.getString("format"));
                comic.setPageCount(object.getInt("pageCount"));
                comic.setResURI(object.getString("resourceURI"));

                JSONObject jsonObjectSeries = object.getJSONObject("series");
                comic.setSeriesName(jsonObjectSeries.getString("name"));
                comic.setSeriesResURI(jsonObjectSeries.getString("resourceURI"));

                JSONObject jsonObjectThumbnail = object.getJSONObject("thumbnail");
                comic.setMarvelImage(new MarvelImage(jsonObjectThumbnail.getString("path"), jsonObjectThumbnail.getString("extension")));

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
                comic.setCreators(creators);

                JSONObject jsonObjectCharacters = object.getJSONObject("characters");
                comic.setCollectionURICharacters(jsonObjectCharacters.getString("collectionURI"));
                JSONArray jsonArrayCharacters = jsonObjectCharacters.getJSONArray("items");
                for (int k=0; k < jsonArrayCharacters.length(); k++) {

                    JSONObject jsonObjectCharacter = jsonArrayCharacters.getJSONObject(k);
                    String characterName = jsonObjectCharacter.getString("name");
                    charactersName.add(characterName);
                }
                comic.setCharactersName(charactersName);

                JSONObject jsonObjectStories = object.getJSONObject("stories");
                comic.setCollectionURIStories(jsonObjectStories.getString("collectionURI"));

                JSONObject jsonObjectEvents = object.getJSONObject("events");
                comic.setCollectionURIEvents(jsonObjectEvents.getString("collectionURI"));

                comics.add(comic);
            }

            fillLayout(comics);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
