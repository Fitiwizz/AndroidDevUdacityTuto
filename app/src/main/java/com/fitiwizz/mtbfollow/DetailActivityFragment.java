package com.fitiwizz.mtbfollow;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();

    private static final String SHARE_HASHTAG = " #KikooLol";

    private String mShareStr;
    private ShareActionProvider mShareActionProvider;

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailfragment, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);

        ShareActionProvider mShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareIntent());
        } else {
            Log.d(LOG_TAG, "SHARE NULL");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            mShareStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            TextView detailedText = (TextView) rootView.findViewById(R.id.detailed_data);
            detailedText.setText(mShareStr);
        }

        return rootView;
    }

    private Intent createShareIntent()
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, mShareStr + SHARE_HASHTAG)
        ;

        return shareIntent;
    }
}
