package com.nvchung.recreatesoundrecorder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvchung.recreatesoundrecorder.DB.DBHelper;
import com.nvchung.recreatesoundrecorder.Model.InforSaveRecord;
import com.nvchung.recreatesoundrecorder.R;

public class AdapterSaveRecord extends RecyclerView.Adapter<AdapterSaveRecord.ViewHolder> {
    private Context context;
    private DBHelper dbHelper;
    private ItemSaveclick itemSaveclick;

    public interface ItemSaveclick {
        void onItemSaveClik(String path);
    }

    public AdapterSaveRecord(Context context, ItemSaveclick itemSaveclick) {
        this.context = context;
        dbHelper = new DBHelper(context);
        this.itemSaveclick = itemSaveclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_saved, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InforSaveRecord inforSaveRecord = dbHelper.readDataAt(position);
        holder.record_name.setText(inforSaveRecord.getmName()+"");
        holder.record_time.setText(inforSaveRecord.getmTime()+"");

    }

    @Override
    public int getItemCount() {
        return dbHelper.getCont();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView record_name;
        private TextView record_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            record_name = itemView.findViewById(R.id.record_name);
            record_time = itemView.findViewById(R.id.record_time);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            InforSaveRecord i = dbHelper.readDataAt(pos);
            String path = i.getmFilePath();
            if(itemSaveclick!=null)
            itemSaveclick.onItemSaveClik(path);

        }
    }

}
