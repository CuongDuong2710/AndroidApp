package organization.cuong.orderfoods;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.cuong.orderfoods.R;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import organization.cuong.orderfoods.Database.Database;
import organization.cuong.orderfoods.Model.Food;
import organization.cuong.orderfoods.Model.Order;

public class FoodDetail extends AppCompatActivity {
    @BindView(R.id.img_food) ImageView food_image;
    @BindView(R.id.food_name) TextView food_name;
    @BindView(R.id.food_price) TextView food_price;
    @BindView(R.id.food_description) TextView food_description;
    @BindView(R.id.collapsing) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.btnCart) FloatingActionButton btnCart;
    @BindView(R.id.number_button) ElegantNumberButton numberButton;

    // getting foodId from FoodList
    String foodId = "";
    // setting current food
    Food currentFood = null;

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

        // adding to card
        addToCart();
    }

    /**
     * adding to card
     */
    private void addToCart() {
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDiscount()
                ));
                Toast.makeText(FoodDetail.this, "Added to Card", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * setting food details (image, name, price, description)
     * @param foodId
     */
    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentFood = dataSnapshot.getValue(Food.class);

                // setting image
                Picasso.with(getBaseContext()).load(currentFood.getImage())
                        .into(food_image);

                // setting components
                collapsingToolbarLayout.setTitle(currentFood.getName());
                food_name.setText(currentFood.getName());
                food_price.setText(currentFood.getPrice());
                food_description.setText(currentFood.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
