package com.interview.fyk.feedyourknowledge.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.interview.fyk.feedyourknowledge.R;
import com.interview.fyk.feedyourknowledge.data.model.CategoryItem;
import com.interview.fyk.feedyourknowledge.ui.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

public class CategoryActivity  extends BaseActivity implements CategoryOnClickListener {

    public final static String CATEGORY_ITEM = "CATEGORY_ITEM";

    private static final List<CategoryItem> sRegions = Arrays.asList(
            new CategoryItem("Monde", null, R.drawable.monde),
            new CategoryItem("Europe", "europe", R.drawable.europe),
            new CategoryItem("France", "france", R.drawable.france),
            new CategoryItem("Afrique", "afrique", R.drawable.africa),
            new CategoryItem("Moyen Orient", "moyen-orient", R.drawable.middle_east),
            new CategoryItem("Amériques", "ameriques", R.drawable.americas),
            new CategoryItem("Asie", "asie-pacifique", R.drawable.asia)
    );

    private static final List<CategoryItem> sThematiques = Arrays.asList(
            new CategoryItem("Économie", "economie", R.drawable.economie),
            new CategoryItem("Sports", "sports", R.drawable.sports),
            new CategoryItem("Culture", "culture", R.drawable.culture),
            new CategoryItem("Planète", "planete", R.drawable.planete),
            new CategoryItem("Découvertes", "decouvertes", R.drawable.decouvertes)
    );

    private CategoryAdapter adapter;

    private RecyclerView recyclerViewRegions;
    private RecyclerView recyclerViewThematiques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initViews();
    }

    @Override
    protected void initViews() {
        super.initViews();

        setToolbarTitle("La Matinale du Monde");

        recyclerViewRegions = (RecyclerView) findViewById(R.id.list_categories);
        recyclerViewThematiques = (RecyclerView) findViewById(R.id.list_categories_thematique);

        // setting up the recycler view
        recyclerViewRegions.setNestedScrollingEnabled(false);
        recyclerViewRegions.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewRegions.setItemAnimator(new DefaultItemAnimator());

        recyclerViewThematiques.setNestedScrollingEnabled(false);
        recyclerViewThematiques.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewThematiques.setItemAnimator(new DefaultItemAnimator());

        recyclerViewRegions.setAdapter(new CategoryAdapter(this, this, sRegions));
        recyclerViewThematiques.setAdapter(new CategoryAdapter(this, this, sThematiques));
    }

    @Override
    public void goToNews(CategoryItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(CATEGORY_ITEM, item);
        startActivity(intent);
    }
}
