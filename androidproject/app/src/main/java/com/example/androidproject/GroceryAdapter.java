package com.example.androidproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    private final Context context;
    private final List<GroceryItem> groceryList;

    public GroceryAdapter(Context context, List<GroceryItem> groceryList) {
        this.context = context;
        this.groceryList = groceryList;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new GroceryViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        GroceryItem item = groceryList.get(position);

        // Temporarily disable listener to avoid unintended triggers
        holder.purchasedCheckBox.setOnCheckedChangeListener(null);

        // Bind data to the views
        holder.itemTextView.setText(item.getName());
        holder.purchasedCheckBox.setChecked(item.isPurchased());

        // Re-enable the listener
        holder.purchasedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setPurchased(isChecked);
            if (onItemCheckedChangeListener != null) {
                onItemCheckedChangeListener.onItemCheckedChanged(holder.getAdapterPosition(), isChecked);
            }
        });
        // Handle Edit Button
        holder.editButton.setOnClickListener(v -> showEditDialog(item, holder.getAdapterPosition()));
        // Handle Delete Button
        holder.deleteButton.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) { // Ensure position is valid
                groceryList.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                notifyItemRangeChanged(adapterPosition, groceryList.size());
                saveGroceryList(); // Save changes after deletion
            }
        });
    }


    @Override
    public int getItemCount() {
        return groceryList.size();
    }

    static class GroceryViewHolder extends RecyclerView.ViewHolder {

        TextView itemTextView;
        CheckBox purchasedCheckBox;
        Button editButton, deleteButton;

        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemTextView);
            purchasedCheckBox = itemView.findViewById(R.id.purchasedCheckBox);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    private void showEditDialog(GroceryItem item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Item");

        // Input field for editing item name
        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText(item.getName());
        builder.setView(input);

        // Positive button to save changes
        builder.setPositiveButton("Save", (dialog, which) -> {
            item.setName(input.getText().toString().trim());
            notifyItemChanged(position); // Update RecyclerView
            saveGroceryList(); // Save changes to persistent storage
        });

        // Negative button to cancel
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void saveGroceryList() {
        SharedPreferences preferences = context.getSharedPreferences("groceryData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> savedData = new HashSet<>();
        for (GroceryItem item : groceryList) {
            // Save each item as "name|purchased" format
            savedData.add(item.getName() + "|" + item.isPurchased());
        }
        editor.putStringSet("groceryItems", savedData);
        editor.apply();
    }

    private OnItemCheckedChangeListener onItemCheckedChangeListener;

    public interface OnItemCheckedChangeListener {
        void onItemCheckedChanged(int position, boolean isChecked);
    }

    public void setOnItemCheckedChangeListener(OnItemCheckedChangeListener listener) {
        this.onItemCheckedChangeListener = listener;
    }
}
