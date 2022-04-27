package edu.mob.lab04;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class CRecyclerViewAdapter extends RecyclerView.Adapter<CRecyclerViewAdapter.CViewHolder> {

    public class CViewHolder extends RecyclerView.ViewHolder {

        TextView textId, textTyp, textNorma;

        CViewHolder(View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.tvID);
            textTyp = itemView.findViewById(R.id.tvType);
            textNorma = itemView.findViewById(R.id.tvStandard);
        }
    }

    private Cursor cData;
    private final LayoutInflater mInflater;

    public CRecyclerViewAdapter(Context context, Cursor mData) {
        this.mInflater = LayoutInflater.from(context);
        this.cData = mData;
    }

    @NonNull
    @Override
    public CViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                          int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new CViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CViewHolder holder, int position) {
        int i;
        if(cData.moveToPosition(position)){
            i = cData.getColumnIndex(CDBHelper.TYPE_ID);
            holder.textId.setText(String.valueOf(cData.getInt(i)));
            i = cData.getColumnIndex(CDBHelper.TYPE_TYPENAME);
            holder.textTyp.setText(cData.getString(i));
            i = cData.getColumnIndex(CDBHelper.TYPE_STANDARD);
            holder.textNorma.setText(cData.getString(i));
        }
    }

    @Override
    public int getItemCount() {
        return cData.getCount();
    }

    public HashMap<String, String> getItem(int id) {
        HashMap<String, String> item = null;
        int i;
        if(cData.moveToPosition(id)) {
            item = new HashMap<>();
            i = cData.getColumnIndex(CDBHelper.TYPE_ID);
            item.put("_id", String.valueOf(cData.getInt(i)));
            i = cData.getColumnIndex(CDBHelper.TYPE_TYPENAME);
            item.put("typ", cData.getString(i));
            i = cData.getColumnIndex(CDBHelper.TYPE_STANDARD);
            item.put("norma", cData.getString(i));
        }
        return item;
    }

    public void swapCursor(Cursor newCursor){
        if(cData!=null) cData.close();
        cData = newCursor;
        if(cData!=null) notifyDataSetChanged();
    }
}
