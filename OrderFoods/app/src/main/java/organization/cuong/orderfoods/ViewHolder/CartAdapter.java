package organization.cuong.orderfoods.ViewHolder;

import android.content.Context;
import android.cuong.orderfoods.R;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import organization.cuong.orderfoods.Interface.ItemClickListener;
import organization.cuong.orderfoods.Model.Order;

/**
 * Created by QUOC CUONG on 09/10/2017.
 * Building item on cart
 */
class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_cart_name, txt_price;
    public ImageView img_cart_count;

    private ItemClickListener itemClickListener;

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        txt_cart_name = itemView.findViewById(R.id.cart_item_name);
        txt_price = itemView.findViewById(R.id.cart_item_price);
        img_cart_count = itemView.findViewById(R.id.card_item_count);
    }

    @Override
    public void onClick(View view) {

    }
}

/**
 * Building adapter for card recycler view
 */
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        // getting properties of cart item
        String quantity = listData.get(position).getQuantity();
        String price = listData.get(position).getPrice();
        String name = listData.get(position).getProductName();

        // setting image cart count
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("" + quantity, Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);

        // setting text price
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int totalPrice = (Integer.parseInt(price)) * (Integer.parseInt(quantity));
        holder.txt_price.setText(fmt.format(totalPrice));

        // setting cart name
        holder.txt_cart_name.setText(name);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
