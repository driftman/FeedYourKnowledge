package com.interview.fyk.feedyourknowledge.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.interview.fyk.feedyourknowledge.R;
import com.interview.fyk.feedyourknowledge.data.model.CategoryItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemHolder> {

    private Context context;
    private List<CategoryItem> categories = new ArrayList<>();
    private CategoryOnClickListener listener;

    public CategoryAdapter(Context context, CategoryOnClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public CategoryAdapter(Context context, CategoryOnClickListener listener, List<CategoryItem> categories) {
        this.context = context;
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public CategoryAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryAdapter.ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        CategoryItem categoryItem = categories.get(position);
        holder.bind(context, categoryItem, listener);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView logo;

        public ItemHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            logo = (ImageView) itemView.findViewById(R.id.item_thumbnail);
        }

        public void bind(final Context context, final CategoryItem categoryItem, final CategoryOnClickListener listener) {
            title.setText(categoryItem.getTitle());
            Picasso.with(context)
                    .load(categoryItem.getRessourceId())
                    .resize(250, 250)
                    .into(logo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.goToNews(categoryItem);
                }
            });
        }
    }
}
