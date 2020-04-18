package com.example.launcherapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class appDrawer extends AppCompatActivity {
    RecyclerView appDrawer;
    drawerAdapter adapter;
    private List<appInfo> appsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_drawer);
        new appLoader().execute();

        appDrawer = findViewById(R.id.appDrawer);
        Log.d("LMAO", String.valueOf(appsList));


    }
    public void update() {
//        adapter.notifyItemInserted(adapter.getItemCount()-1);
        Log.d("LMAO", String.valueOf(appsList));
        adapter = new drawerAdapter(appDrawer.this,appsList);
        appDrawer.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        appDrawer.setAdapter(adapter);
    }
    public class appLoader extends AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... voids)
        {

            PackageManager pm = getPackageManager();
            appsList = new ArrayList<appInfo>();

            Intent i = new Intent(Intent.ACTION_MAIN, null);
            i.addCategory(Intent.CATEGORY_LAUNCHER);

            List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
            for(ResolveInfo ri:allApps) {
                appInfo app = new appInfo();
                app.label = ri.loadLabel(pm);
                app.packageName = ri.activityInfo.packageName;
                app.icon = ri.activityInfo.loadIcon(pm);
                appsList.add(app);
            }
            return "Success";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            update();
        }
    }

}

