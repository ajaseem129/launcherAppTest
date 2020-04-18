package com.example.launcherapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton chrome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chrome=(ImageButton) findViewById(R.id.chrome);
        chrome.setImageDrawable(getActivityIcon(this,"com.android.chrome", "com.google.android.apps.chrome.Main"));
        onClicks();
    }
    public void onClicks()
    {
        chrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
                startActivity(launchIntent);
            }
        });
    }

    /**
     * This method fetches the drawable file(icon in this case) of the Application we need. It takes the Application Package name and current activity name.
     * Gets the PackageManager with respect to the context of the function call.
     * Then it sets the component of the intent, giving it an Identifier, generated using the constructor of ComponentName Class. It takes the package name and activityName to create the unique Identifier.
     * ResolveInfo is used to store the information that is returned from an intent, against an IntentFilter.
     * Here, we use the resolveActivity method to resolve the best action to perform for a given Intent.
     * Finally returns the Drawable Icon containing the resolution's icon.  If the item does not have an icon, the default activity icon is returned.
     * @param context
     * @param packageName
     * @param activityName
     * @return
     */
    public static Drawable getActivityIcon(Context context, String packageName, String activityName) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityName));
        ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);

        return resolveInfo.loadIcon(pm);
    }
}
