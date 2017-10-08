package organization.cuong.orderfoods;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.cuong.orderfoods.R;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import organization.cuong.orderfoods.Model.Food;

public class FoodDetail extends AppCompatActivity {
    @BindView(R.id.img_food) ImageView food_image;
    @BindView(R.id.food_name) TextView food_name;
    @BindView(R.id.food_price) TextView food_price;
    @BindView(R.id.food_description) TextView food_description;
    @BindView(R.id.collapsing) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.btnCart) FloatingActionButton btnCart;
    @BindView(R.id.number_button) ElegantNumberButton numberButton;

    String foodId = "";

    FirebaseDatabase database = null;
    DatabaseReference foods = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);

        // Init FireBase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Foods");

        // setting collapsing toolbar layout
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandeddAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        // getting FoodId from FoodList
        if (getIntent() != null) {
            foodId = getIntent().getStringExtra("FoodId");
        }
        // loading food details
        if (!foodId.isEmpty()) {
            getDetailFood(foodId);
        }
    }

    /**
     * setting food details (image, name, price, description)
     * @param foodId
     */
    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);

                // setting image
                Picasso.with(getBaseContext()).load(food.getImage())
                        .into(food_image);

                // setting components
                collapsingToolbarLayout.setTitle(food.getName());
                food_name.setText(food.getName());
                food_price.setText(food.getPrice());
                food_description.setText(food.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
