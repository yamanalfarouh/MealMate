package com.example.androidproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class MyAdapter extends PagerAdapter {
    Context context;
    int[] images;
    LayoutInflater layoutInflater;

    public MyAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View v = layoutInflater.inflate(R.layout.item, container, false);
        ImageView imageView = v.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        container.addView(v);

        // Set click listener for each image
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(context, FirstActivity.class);
                        break;
                    case 1:
                        intent = new Intent(context, SecondActivity.class);
                        break;
                    case 2:
                        intent = new Intent(context, ThirdActivity.class);
                        break;
                    default:
                        // Fallback in case of unexpected positions
                        intent = new Intent(context, FirstActivity.class);
                        break;
                }
                context.startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
