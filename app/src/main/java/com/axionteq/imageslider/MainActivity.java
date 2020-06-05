package com.axionteq.imageslider;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.axionteq.simpleimageslider.BaseSliderView;
import com.axionteq.simpleimageslider.DefaultSliderView;
import com.axionteq.simpleimageslider.SliderLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {

    private SliderLayout mDemoSlider;

    // Billionaires json url
    private static final String getURL = "https://api.androidhive.info/json/movies.json";
    HashMap<String, String> url_maps = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mDemoSlider = (SliderLayout) findViewById( R.id.slider );

        // Creating volley request obj
        JsonArrayRequest billionaireReq = new JsonArrayRequest( getURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        url_maps = new HashMap<>();
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject( i );
                                url_maps.put( obj.getString( "title" ) + " - " + obj.getString( "releaseYear" ), obj.getString( "image" ) );

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        for (String name : url_maps.keySet()) {
                            //TextSliderView textSliderView = new TextSliderView(MainActivity.this);
                            // initialize a SliderLayout
                            DefaultSliderView defaultSliderView = new DefaultSliderView( MainActivity.this );
                            defaultSliderView.image( url_maps.get( name ) ).setScaleType( BaseSliderView.ScaleType.Fit );

                            defaultSliderView.bundle( new Bundle() );
                            defaultSliderView.getBundle().putString( "extra", name );

                            mDemoSlider.addSlider( defaultSliderView );
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( getApplicationContext(), "network issue: please enable wifi/mobile data + " + error, Toast.LENGTH_SHORT ).show();
            }
        } );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue( billionaireReq );

        mDemoSlider.setPresetTransformer( SliderLayout.Transformer.Default );
        mDemoSlider.setPresetIndicator( SliderLayout.PresetIndicators.Center_Bottom );
        mDemoSlider.setDuration( 4000 );

    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText( this, slider.getBundle().get( "extra" ) + "", Toast.LENGTH_SHORT ).show();
    }

}
