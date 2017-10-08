package organization.cuong.orderfoods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.cuong.orderfoods.R;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import organization.cuong.orderfoods.Interface.ItemClickListener;
import organization.cuong.orderfoods.Model.Food;
import organization.cuong.orderfoods.ViewHolder.FoodViewHolder;

/**
 * Loading food list by categoryId
 */
public class FoodList extends AppCompatActivity {
    @BindView(R.id.recycler_food) RecyclerView recycler_food;
    RecyclerView.LayoutManager layoutManager = null;

    FirebaseDatabase database = null;
    DatabaseReference foodList = null;
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter = null;

    String categoryId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        ButterKnife.bind(this);

        // Init Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Foods");

        // Init recycler view
        recycler_food.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_food.setLayoutManager(layoutManager);

        // Get CategoryId from Home
        if (getIntent() != null) {
            categoryId = getIntent().getStringExtra("CategoryId");
        }

        // load Food list
        if (!categoryId.isEmpty() && categoryId != null) {
            loadFoodsList(categoryId);
        }

    }

    /**
     * Loading Foods list by categoryId
     * @param categoryId
     */
    private void loadFoodsList(String categoryId) {
        // setting adapter by filtering "MenuId"
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodList.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                // set food name
                viewHolder.foodName.setText(model.getName());
                // set food image
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.foodImage);
                // set current food
                final Food currentFoodItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FoodList.this, "" + currentFoodItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        // set adapter for recycler view
        recycler_food.setAdapter(adapter);
    }
}
