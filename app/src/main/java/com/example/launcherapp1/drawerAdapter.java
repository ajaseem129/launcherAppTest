package com.example.launcherapp1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class drawerAdapter extends RecyclerView.Adapter<drawerAdapter.ViewHolder> {
    private List<appInfo> appsList;
    public drawerAdapter(Context c,List<appInfo> appList)
    {
        //This is where we build our list of app details, using the app
        //object we created to store the label, package name and icon
        this.appsList = appList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.appLabel);
            img = (ImageView) itemView.findViewById(R.id.appIcon);
        }
    }


    @NonNull
    @Override
    public drawerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.drawer_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull drawerAdapter.ViewHolder holder, final int position) {

        String appLabel = appsList.get(position).label.toString();
        String appPackage = appsList.get(position).packageName.toString();
        Drawable appIcon = appsList.get(position).icon;

        TextView textView = holder.textView;
        textView.setText(appLabel);
        ImageView imageView = holder.img;
        imageView.setImageDrawable(appIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appsList.get(position).packageName.toString());
                context.startActivity(launchIntent);
                Toast.makeText(v.getContext(), appsList.get(position).label.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }


}
