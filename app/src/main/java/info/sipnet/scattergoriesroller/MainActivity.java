package info.sipnet.scattergoriesroller;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appbrain.AdId;
import com.appbrain.InterstitialBuilder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AdView mAdView;
    private InterstitialBuilder interstitialBuilder;
    TextView mTimerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        interstitialBuilder = InterstitialBuilder.create().setAdId(AdId.custom("AppBrainID"))
                .setFinishOnExit(this).preload(this);


        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button rollButton;
        rollButton = (Button)findViewById(R.id.rollButton);

        final TextView letterView = (TextView)findViewById(R.id.letterView);
        final TextView mTimerText = (TextView) findViewById(R.id.mTimerText);
        final List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");
        list.add("H");
        list.add("I");
        list.add("J");
        list.add("K");
        list.add("L");
        list.add("M");
        list.add("N");
        list.add("O");
        list.add("P");
        list.add("R");
        list.add("S");
        list.add("T");
        list.add("W");




        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ScatterRoller", "User is rolling.");

                Random random = new Random();
                int index = random.nextInt(list.size());
                letterView.setText(list.get(index));
                Log.d("ScatterRoller", "Random letter is: " + letterView);

                new CountDownTimer(180000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        mTimerText.setText(millisUntilFinished / 1000 + " seconds remaining " );
                    }

                    public void onFinish() {
                        mTimerText.setText("Round Over!");
                        Vibrator mVibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        mVibrate.vibrate(1500);
                    }
                }
              .start();
                Log.d("ScatterRoller", "New Timer Activity Started for Scatter Roller");
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (!interstitialBuilder.show(this)) {
            super.onBackPressed();
        }
    }
}
