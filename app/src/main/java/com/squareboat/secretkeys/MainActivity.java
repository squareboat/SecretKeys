package com.squareboat.secretkeys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    public native String getAPIKey();

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        storeSecret();

        mTextView = (TextView) findViewById(R.id.text_view);

        if(LocalStoreUtils.getAppSecret(this)!=null)
            mTextView.setText(getResources().getString(R.string.app_text_line) + "\n\n" + LocalStoreUtils.getAppSecret(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void storeSecret(){

        if(LocalStoreUtils.getAppSecret(this)==null) {

            byte[] data = Base64.decode(getAPIKey(), Base64.DEFAULT);
            try {
                LocalStoreUtils.setAppSecret(new String(data, "UTF-8"), this);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }
}
