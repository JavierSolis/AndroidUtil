package com.javiersolis.lib.android.util.twiter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * by Javier Solis @JavierTwiteando
 * Github http://bit.ly/onGithub
 */
public class UtilTwiter {
    public static void openTwiterAcount(Context context, String userId, String profileName)
    {
        Intent intent = null;
        try {
            // get the Twitter app if possible
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id="+userId));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"+profileName));
        }
        context.startActivity(intent);
    }
}
