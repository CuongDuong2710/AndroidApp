package android.cuong.broadcastreceiver;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by QUOC CUONG on 07/11/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<IncomingNumber> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<IncomingNumber> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.id.setText(arrayList.get(position).getId());
        holder.number.setText(arrayList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, number;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textId);
            number = itemView.findViewById(R.id.textNumber);
        }
    }
}
