package organization.cuong.orderfoods.ViewHolder;

import android.cuong.orderfoods.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import organization.cuong.orderfoods.Interface.ItemClickListener;

/**
 * Created by QUOC CUONG on 11/10/2017.
 * Building item for order status
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements OnClickListener{

    public TextView txtOrderId, txtOrderStatus, txtOrderPhone, txtOrAddress;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);

        txtOrderId = itemView.findViewById(R.id.order_id);
        txtOrderStatus = itemView.findViewById(R.id.order_status);
        txtOrderPhone = itemView.findViewById(R.id.order_phone);
        txtOrAddress = itemView.findViewById(R.id.order_address);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
