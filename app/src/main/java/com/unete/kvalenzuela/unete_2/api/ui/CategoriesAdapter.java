package com.unete.kvalenzuela.unete_2.api.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unete.kvalenzuela.unete_2.R;
import com.unete.kvalenzuela.unete_2.api.model.Asociacion;
import com.unete.kvalenzuela.unete_2.api.model.CategoryDisplayList;
import com.unete.kvalenzuela.unete_2.api.model.CategoryListBody;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<CategoryDisplayList> mItems;

    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    interface OnItemClickListener {

        void onItemClick(CategoryDisplayList clickedAppointment);

    }

    public CategoriesAdapter( Context mContext, List<CategoryDisplayList> mItems) {
        this.mItems = mItems;
        this.mContext = mContext;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener OnItemClickListener) {
        this.mOnItemClickListener = OnItemClickListener;
    }

    public void swapItems(List<CategoryDisplayList> associations) {
        if (associations == null) {
            mItems = new ArrayList<>(0);
        } else {
            mItems = associations;
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.category_item_list, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CategoryDisplayList asociacion = mItems.get(position);

        holder.razon.setText(asociacion.getRazon_Social());
        holder.descripcion.setText(asociacion.getDescripcion());

     /*   String uri = "@drawable/s" + asociacion.getSubcategoria_Id_SubCat();
        Context context = holder.subcategoria.getContext();
        int imageResource = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
        Drawable imagen = ContextCompat.getDrawable(context, imageResource);

        holder.subcategoria.setBackground(imagen);*/
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView razon;
        public TextView descripcion;
        public View subcategoria;

        public ViewHolder(View itemView) {
            super(itemView);

            razon = itemView.findViewById(R.id.txtnombreAC);
            descripcion = (TextView) itemView.findViewById(R.id.txtdescripcion);
            //subcategoria = (TextView) itemView.findViewById(R.id.subcatAC);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                mOnItemClickListener.onItemClick(mItems.get(position));
            }
        }
    }



}
