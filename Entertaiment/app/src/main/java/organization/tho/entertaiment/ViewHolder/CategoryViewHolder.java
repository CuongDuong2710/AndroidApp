package organization.tho.entertaiment.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import organization.tho.entertaiment.Listener.ItemClickListener;
import organization.tho.entertaiment.R;

/**
 * Created by QUOC CUONG on 16/10/2017.
 * Buidling item on Category menu
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
    public TextView txtCategoryName;
    public ImageView imgCategory;

    private ItemClickListener itemClickListener;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        txtCategoryName = itemView.findViewById(R.id.title);
        imgCategory = itemView.findViewById(R.id.thumbnail);

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
