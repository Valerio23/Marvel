package it.smellsliketeamspirit.testmarvel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import it.smellsliketeamspirit.testmarvel.adapters.HeroAdapter;
import it.smellsliketeamspirit.testmarvel.entities.Hero;
import it.smellsliketeamspirit.testmarvel.requests.HeroAPI;

public class MainActivity extends AppCompatActivity {

    private HeroAPI mApi;
    private RecyclerView rv;
    private HeroAdapter heroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Holder();
    }

    private void onClickAPICallBack(Hero hero) {
        Toast.makeText(this, hero.getName(), Toast.LENGTH_SHORT).show();
    }

    // ------------------------------------------------------------------------------------------ //

    // Holder - BEGIN
    class Holder implements View.OnClickListener, TextView.OnEditorActionListener {

        final Button btnSearch;
        final EditText etSearch;

        Holder() {
            btnSearch = findViewById(R.id.btnSearch);
            etSearch = findViewById(R.id.etSearch);

            btnSearch.setOnClickListener(this);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            rv = findViewById(R.id.rvHeroes);
            rv.setLayoutManager(layoutManager);

            mApi = new HeroAPI(MainActivity.this) {
                @Override
                public void fillLayout(List<Hero> heroes) {
                    heroAdapter = new HeroAdapter(MainActivity.this, heroes) {
                        @Override
                        public void onClickAdapterCallBack(Hero hero) {
                            onClickAPICallBack(hero);
                        }
                    };

                    rv.setAdapter(heroAdapter);
                }
            };
        }

        private void search() {
            mApi.searchHeroes();
            // mApi.searchHeroesByNameStartsWith(etSearch.getText().toString());
        }

        @Override
        public void onClick(View v) {
            search();
        }

        void hideKeyboard(Activity activity) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                search();
                return false;
            }
            return false;
        }
    }
    // Holder - END

}
