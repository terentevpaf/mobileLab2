package com.example.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PageFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    private static Context context;
    int pageNumber;
    int backColor;

    public static List<String> items = new ArrayList<>();
    public static List<String> images = new ArrayList<>();
    public static List<String> helptext = new ArrayList<>();

    public static String GetItem(int num)
    {
        StringBuilder bul = new StringBuilder();
        bul.append(items.get(num) + ". " + helptext.get(num));
        return bul.toString().trim();
    }

    public static String GetImage(int num)
    {
        return images.get(num);
    }

    public static void JsonData(String data)
    {
        try {
            JSONArray arrayJS = new JSONArray(data);

            String dopText;
            for(int i=1;i<arrayJS.length();i++)
            {
                dopText = "";
                JSONObject object= arrayJS.getJSONObject(i);
                items.add(object.getString("name"));
                images.add(object.getString("graphic"));
                if(object.has("helptext"))
                {
                    dopText = object.getString("helptext");
                }
                else
                {
                    dopText = "";
                }
                helptext.add(dopText);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    static PageFragment newInstance(int page, Context cont) {

        context = cont;
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        Random rnd = new Random();
        backColor = Color.argb(255, 255, 255, 255);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail, null);

        TextView tvPage = (TextView) view.findViewById(R.id.tvPage);
        tvPage.setText(GetItem(pageNumber));
        tvPage.setBackgroundColor(backColor);

        ImageView bigImage = (ImageView) view.findViewById(R.id.bigImage);

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground_load)
                .error(R.drawable.ic_launcher_foreground_load)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide
                .with(context)
                .load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + GetImage(pageNumber))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(options)
                .into(bigImage);

        return view;
    }
}