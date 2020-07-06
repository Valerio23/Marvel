package it.smellsliketeamspirit.testmarvel.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;

import java.util.List;

import it.smellsliketeamspirit.testmarvel.R;
import it.smellsliketeamspirit.testmarvel.entities.Hero;

public abstract class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.Holder> implements View.OnClickListener {

    private final List<Hero> heroes;
    private RequestQueue requestQueue;

    public abstract void onClickAdapterCallBack(Hero hero);

    public List<Hero> getHeroes() {
        return heroes;
    }

    protected HeroAdapter(Context context, List<Hero> all) {
        heroes = all;
        // Uso cache
        Cache cache = new DiskBasedCache(context.getCacheDir(), 16 * 1024 * 1024); // 16 MB cache
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cl;
        cl = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_character, parent, false);
        cl.setOnClickListener(this);
        return new Holder(cl);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Hero hero = heroes.get(position);
        holder.tvHero.setText(hero.getName());
        getImage(position, hero.getImgHero().getFullPath(), holder.ivHero);
    }

    private void getImage(int position, String strHeroThumb, final ImageView imageView) {
        requestQueue.cancelAll(position);
        ImageRequest imageRequest = new ImageRequest(strHeroThumb,
                imageView::setImageBitmap, 0, 0, null, Bitmap.Config.ARGB_8888,
                error -> imageView.setImageResource(R.drawable.ic_launcher_background));
        imageRequest.setTag(position);
        requestQueue.add(imageRequest);
    }

    @Override
    public void onClick(View v) {
        int position = ((RecyclerView) v.getParent()).getChildAdapterPosition(v);
        Hero hero = heroes.get(position);
        onClickAdapterCallBack(hero);
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        final TextView tvHero;
        final ImageView ivHero;
        ConstraintLayout clContainer;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvHero = itemView.findViewById(R.id.tvHero);
            ivHero = itemView.findViewById(R.id.ivHero);
            clContainer = itemView.findViewById(R.id.cvItemHeroLayout);
        }
    }


}
