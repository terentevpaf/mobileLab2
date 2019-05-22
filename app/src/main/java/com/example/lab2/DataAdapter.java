package com.example.lab2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    public JsonData jd;
    public static List<String> items = new ArrayList<>();
    public static List<String> images = new ArrayList<>();
    String JsonDataText;
    private Context context;

    public static String GetItem(int num)
    {
        StringBuilder bul = new StringBuilder();
        bul.append(items.get(num));
        return bul.toString().trim();
    }

    public static String GetImage(int num)
    {
        return images.get(num);
    }

    DataAdapter(Context cont)
    {
        super();
        jd = new JsonData();
        this.context = cont;
    }

    public void JsonData(String data)
    {
        JsonDataText = data;
        try {
            JSONArray arrayJS = new JSONArray(data);
            for(int i=1;i<arrayJS.length();i++)
            {
                JSONObject object= arrayJS.getJSONObject(i);
                items.add(object.getString("name"));
                images.add(object.getString("graphic"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public View elementLV;
        public ViewHolder(View v)
        {
            super(v);
            this.elementLV = v;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.list_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.elementLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Detail.class);
                i.putExtra("json", JsonDataText);
                i.putExtra("num", new Integer(viewHolder.getAdapterPosition()).toString());
                i.putExtra("countItem", new Integer(getItemCount()).toString());
                context.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        String col = ((position + 1) % 2 == 0) ? "#CCCCCC" : "#FFFFFF";
        holder.elementLV.setBackgroundColor(Color.parseColor(col));
        TextView mTextView = (TextView) holder.elementLV.findViewById(R.id.name);
        mTextView.setText(GetItem(position));

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground_load)
                .error(R.drawable.ic_launcher_foreground_load)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide
                .with(context)
                .load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + GetImage(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(options)
                .into((ImageView) holder.elementLV.findViewById(R.id.image));
    }

    @Override
    public int getItemCount()
    {
        Integer obj = new Integer(items.size());
        return obj;
    }

}