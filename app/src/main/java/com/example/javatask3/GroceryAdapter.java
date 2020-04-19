package com.example.javatask3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class GroceryAdapter extends ArrayAdapter<GroceryItem> {
    public GroceryAdapter(@NonNull Context context, @NonNull List<GroceryItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.grocery_list_view_items,parent,false);
        }
        //Updating the ui
        GroceryItem currentItem = getItem(position);
        TextView groceryItem = listItemView.findViewById(R.id.grocery_item_placeholder);
        TextView groceryPrice = listItemView.findViewById(R.id.price_placeholder);

        assert currentItem != null;
        groceryItem.setText(currentItem.getItem());
        String item = String.valueOf(currentItem.getPrice());
        groceryPrice.setText(item);

        return listItemView;
    }
}
