package com.unete.kvalenzuela.unete_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.unete.kvalenzuela.unete_2.api.model.RegistersDisplayList;

import java.util.ArrayList;
import java.util.List;

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ViewHolder>  {

    private List<RegistersDisplayList> mItems;

    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    interface OnItemClickListener {

        void onItemClick(RegistersDisplayList clickedAppointment);

        void onCancelAppointment(RegistersDisplayList canceledAppointment);

    }

    public ManagerAdapter(Context context, List<RegistersDisplayList> items) {
        mItems = items;
        mContext = context;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void swapItems(List<RegistersDisplayList> appointments) {
        if (appointments == null) {
            mItems = new ArrayList<>(0);
        } else {
            mItems = appointments;
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.register_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RegistersDisplayList appointment = mItems.get(position);

        View statusIndicator = holder.statusIndicator;

        // estado: se colorea indicador segÃºn el estado
        switch (appointment.getEstatus()) {
            case "ACTIVO":
                // ocultar botÃ³n
                holder.cancelButton.setVisibility(View.GONE);
                statusIndicator.setBackgroundResource(R.color.completedStatus);
                break;
            case "INACTIVO":
                // ocultar botÃ³n
                holder.cancelButton.setVisibility(View.GONE);
                statusIndicator.setBackgroundResource(R.color.cancelledStatus);
                break;
            case "EN ESPERA":
                // mostrar botÃ³n
                holder.cancelButton.setVisibility(View.VISIBLE);
                statusIndicator.setBackgroundResource(R.color.activeStatus);
                break;
        }

        holder.razon_social.setText(appointment.getRazon_Social());
        holder.representante.setText(appointment.getRep_Nombre());
        holder.rep_cel.setText(appointment.getRep_Cel());
        holder.rep_correo.setText(appointment.getRep_Correo());
        holder.subCategory.setText(appointment.getSubcategoria_Id_SubCat());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView razon_social;
        public TextView representante;
        public TextView rep_cel;
        public TextView rep_correo;
        public TextView subCategory;
        public Button cancelButton;
        public View statusIndicator;

        public ViewHolder(View itemView) {
            super(itemView);

            statusIndicator = itemView.findViewById(R.id.indicator_appointment_status);
            razon_social = (TextView) itemView.findViewById(R.id.text_razon_social);
            representante = (TextView) itemView.findViewById(R.id.text_rep_name);
            rep_cel = (TextView) itemView.findViewById(R.id.text_rep_cel);
            rep_correo = (TextView) itemView.findViewById(R.id.text_rep_correo);
            subCategory = (TextView) itemView.findViewById(R.id.text_subCat);
            cancelButton = (Button) itemView.findViewById(R.id.button_activate_appointment);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mOnItemClickListener.onCancelAppointment(mItems.get(position));
                    }
                }
            });
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
