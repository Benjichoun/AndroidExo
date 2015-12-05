package org.esiea.badelon_batista.ourapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Benjamin on 05/12/2015.
 */
public class BiersAdapter  extends RecyclerView.Adapter<BiersAdapter.BierHolder> {


    private JSONArray biers;





    @Override
    public BiersAdapter.BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Création d'une nouvelle vue
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bier_element, parent, false);
        // set the view's size, margins, paddings and layout parameters
        BierHolder bh = new BierHolder(v);
        return bh;
    }

    @Override
    public void onBindViewHolder(BiersAdapter.BierHolder holder, int position) {



        try {
            JSONObject object = this.biers.getJSONObject(position);

            holder.name.setText(object.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public class BierHolder extends RecyclerView.ViewHolder{
        public TextView name;

        public BierHolder(View v) {
            super(v);

            name = (TextView) v.findViewById(R.id.rv_bier_element_name);


        }
    }
    @Override
    public int getItemCount() {
        return this.biers.length();
    }

    public BiersAdapter(JSONArray bieres) {

        this.biers = bieres;
    }

    public void setNewBiere(JSONArray update){
        this.biers=update;

        notifyDataSetChanged();

    }

}
