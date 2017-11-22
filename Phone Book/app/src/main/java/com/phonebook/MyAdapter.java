package com.phonebook;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private List<Model> list;

    public MyAdapter(List<Model> list) {
        this.list = list;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.MyViewHolder holder, final int position) {
        holder.text.setText(list.get(position).getName());
        holder.buiznes.setText(list.get(position).getBuiznes());

        holder.phone.setText(list.get(position).getPhone());
        holder.adress.setText(list.get(position).getAdress());

        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Model m = new Model();
                m.setName(list.get(position).getName());
                m.setBuiznes(list.get(position).getBuiznes());
                m.setAdress(list.get(position).getAdress());
                m.setAdress(list.get(position).getPhone());
                list.add(position +1, m);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final TextView phone;
        private final TextView adress;
        private TextView buiznes;
        private TextView text;
        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.recycler_text);
            buiznes = view.findViewById(R.id.buiznes);
            phone = (TextView) view.findViewById(R.id.text_phone);
            adress = (TextView) view.findViewById(R.id.text_adress);

        }
    }

}
