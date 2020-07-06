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
import it.smellsliketeamspirit.testmarvel.entities.Story;

public abstract class StoryAPI implements Response.Listener<String> {

    private static final String TIME_STAMP = "1"; // NON TOCCARE QUESTO!!!
    private static final String APIKEY = "fed0a168e2fc7c65424b14a30b89b358";
    private static final String HASH = "671fc2069018eab3f9c5fe6549ba864a";
    private RequestQueue requestQueue;

    public abstract void fillLayout(List<Story> stories);

    protected StoryAPI(Context context) {
        Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024); // 10 MB cache
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    public void searchStoryByComic(String s) {
        String url = "https://gateway.marvel.com/v1/public/stories?ts=%s&comics=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, s, APIKEY, HASH);
        Log.w("TAG_M", s);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    public void searchStoryBySerie(String s) {
        String url = "https://gateway.marvel.com/v1/public/stories?ts=%s&series=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, s, APIKEY, HASH);
        Log.w("TAG_M", s);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    public void searchStoryByEvent(String s) {
        String url = "https://gateway.marvel.com/v1/public/stories?ts=%s&events=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, s, APIKEY, HASH);
        Log.w("TAG_M", s);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    public void searchStoryByCharacter(String s) {
        String url = "https://gateway.marvel.com/v1/public/stories?ts=%s&characters=%s&apikey=%s&hash=%s";
        String urlSearch = String.format(Locale.getDefault(), url, TIME_STAMP, s, APIKEY, HASH);
        Log.w("TAG_M", s);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSearch, this, error -> {});
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResponse(String response) {

        ArrayList<Story> stories = new ArrayList<>();
        ArrayList<Creator> creators = new ArrayList<>();
        ArrayList<String> charactersName = new ArrayList<>();

        try {
            JSONObject jsonObjectResponse = new JSONObject(response);
            JSONObject jsonObjectData = jsonObjectResponse.getJSONObject("data");
            JSONArray jsonArrayResults = jsonObjectData.getJSONArray("results");

            for (int i=0; i < jsonArrayResults.length(); i++) {

                JSONObject object = jsonArrayResults.getJSONObject(i);

                Story story = new Story();
                story.setId(object.getString("id"));
                story.setTitle(object.getString("title"));
                story.setResURI(object.getString("resourceURI"));
                story.setType(object.getString("type"));

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
                story.setCreators(creators);

                JSONObject jsonObjectCharacters = object.getJSONObject("characters");
                story.setCollectionURICharacters(jsonObjectCharacters.getString("collectionURI"));
                JSONArray jsonArrayCharacters = jsonObjectCharacters.getJSONArray("items");
                for (int k=0; k < jsonArrayCharacters.length(); k++) {

                    JSONObject jsonObjectCharacter = jsonArrayCharacters.getJSONObject(k);
                    String characterName = jsonObjectCharacter.getString("name");
                    charactersName.add(characterName);
                }
                story.setCharactersName(charactersName);

                JSONObject jsonObjectSeries = object.getJSONObject("series");
                story.setCollectionURISeries(jsonObjectSeries.getString("collectionURI"));

                JSONObject jsonObjectComics = object.getJSONObject("comics");
                story.setCollectionURIComics(jsonObjectComics.getString("collectionURI"));

                JSONObject jsonObjectEvents = object.getJSONObject("events");
                story.setCollectionURIEvents(jsonObjectEvents.getString("collectionURI"));

                JSONObject jsonObjectIssue = object.getJSONObject("originalIssue");
                story.setOriginalIssueName(jsonObjectIssue.getString("name"));
                story.setOriginalIssueResURI(jsonObjectIssue.getString("resourceURI"));

                stories.add(story);
            }

            fillLayout(stories);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
