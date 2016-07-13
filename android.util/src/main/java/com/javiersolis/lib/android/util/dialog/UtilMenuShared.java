package com.javiersolis.lib.android.util.dialog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.plus.PlusShare;
import com.javiersolis.lib.android.util.R;
import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Javier Solis @JavierTwiteando  @PineappleTic on 24/01/16.
 */
public class UtilMenuShared implements BottomSheetListener {
    private String text;

    private String url;
    private Context context;
    private BottomSheet bottomSheet;

    public UtilMenuShared(Context context, String text, String url) {
        this.context = context;
        this.text = text;
        this.url = url;

        this.bottomSheet = new BottomSheet.Builder(this.context)
                .grid()
                .setSheet(R.menu.menu_shared)
                .setTitle("Compartir vía:")
                .setListener(this).create();
    }

    public void showMenu() {
        this.bottomSheet.show();
    }

    @Override
    public void onSheetShown(@NonNull BottomSheet bottomSheet) {

    }

    @Override
    public void onSheetItemSelected(@NonNull BottomSheet bottomSheet, MenuItem menuItem) {

        int i = menuItem.getItemId();

        /*
        if (i == R.id.shared_cancel) {
            this.bottomSheet.dismiss();

        } else*/
        if (i == R.id.shared_facebook) {
            this.shareAppLinkViaFacebook();

        } else if (i == R.id.shared_twiter) {
            this.sharedTwiter();

        } else if (i == R.id.shared_google_plus) {
            this.sharedGooglePlus();

        } else if (i == R.id.shared_whatsapp) {
            this.sharedViaWhastApp();
        } else if (i == R.id.shared_messenger) {
            this.shareAppLinkViaMessenger();


        } else if (i == R.id.shared_sms) {
            this.sharedAllSmsApps();

        } else if (i == R.id.shared_copy) {
            this.copyToMemory();

        }
    }

    private void copyToMemory() {
        try {
            ClipboardManager clipboard = (ClipboardManager) this.context.getSystemService(this.context.CLIPBOARD_SERVICE);
            clipboard.setText(this.text + " " + this.url);
            Toast.makeText(this.context, "Se copió el link de instalación", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.bottomSheet.dismiss();

    }

    private void sharedGooglePlus() {
        try {
            Intent shareIntent = new PlusShare.Builder(this.context)
                    .setType("text/plain")
                    .setText(this.text)
                    .setContentUrl(Uri.parse(this.url))
                    .getIntent();

            this.context.startActivity(shareIntent);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        this.bottomSheet.dismiss();
    }

    private void sharedTwiter() {
        try {
            String tweetUrl =
                    String.format("https://twitter.com/intent/tweet?text=%s&url=%s",
                            urlEncode(this.text), urlEncode(this.url));
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

// Narrow down to official Twitter app, if available:
            List<ResolveInfo> matches = this.context.getPackageManager().queryIntentActivities(intent, 0);
            for (ResolveInfo info : matches) {
                if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                    intent.setPackage(info.activityInfo.packageName);
                }
            }

            this.context.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        this.bottomSheet.dismiss();
    }

    public String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.wtf("WTF", "UTF-8 should always be supported", e);
            throw new RuntimeException("URLEncoder.encode() failed for " + s);
        }
    }

    private void shareAppLinkViaFacebook() {
        String urlToShare = this.url;

        try {
            try {
                /*
                Intent intent1 = new Intent();
                intent1.setClassName("com.facebook.katana", "com.facebook.katana.activity.composer.ImplicitShareIntentHandler");
                intent1.setAction("android.intent.action.SEND");
                intent1.setType("text/plain");
                intent1.putExtra("android.intent.extra.TEXT", urlToShare);
                this.context.startActivity(intent1);
                */

                //Log.i("REFLECTION", activity.applicationInfo.packageName);
                //Log.i("REFLECTION", activity.name);


                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, urlToShare);

                //Log.i("REFLECTION", activity.applicationInfo.packageName);
                //Log.i("REFLECTION", activity.name);
                ComponentName name = new ComponentName("com.facebook.katana",
                        "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias");

                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                shareIntent.setComponent(name);

                this.context.startActivity(shareIntent);


            } catch (Exception e) {
                e.printStackTrace();
                // If we failed (not native FB app installed), try share through SEND
                Intent intent = new Intent(Intent.ACTION_SEND);
                String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                this.context.startActivity(intent);
            }

            /*
com.facebook.messenger.intents.ShareIntentHandler
com.facebook.orca

com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias
com.facebook.katana
            */
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        this.bottomSheet.dismiss();

    }


    private void shareAppLinkViaMessenger() {
        String urlToShare = this.url;

        try {
            try {
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, urlToShare);

                ComponentName name = new ComponentName("com.facebook.orca",
                        "com.facebook.messenger.intents.ShareIntentHandler");

                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                shareIntent.setComponent(name);
                this.context.startActivity(shareIntent);

            } catch (Exception e) {
                e.printStackTrace();
                // If we failed (not native FB app installed), try share through SEND
                Intent intent = new Intent(Intent.ACTION_SEND);
                String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                this.context.startActivity(intent);
            }

            /*
com.facebook.messenger.intents.ShareIntentHandler
com.facebook.orca

com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias
com.facebook.katana
            */
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        this.bottomSheet.dismiss();

    }


    public void sharedAllSmsApps() {
        try {
            String shareBody = this.text + " " + this.url;
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            this.context.startActivity(Intent.createChooser(sharingIntent, "Compartir con"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        this.bottomSheet.dismiss();
    }

    public void sharedViaWhastApp() {

        try {
            PackageManager pm = this.context.getPackageManager();

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = this.text + " " + this.url;

            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            this.context.startActivity(Intent.createChooser(waIntent, "Compartir con"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this.context, "WhatsApp no ha sido instalado", Toast.LENGTH_SHORT)
                    .show();

            return;
        }

        this.bottomSheet.dismiss();


    }

    @Override
    public void onSheetDismissed(@NonNull BottomSheet bottomSheet, int i) {

    }


}
