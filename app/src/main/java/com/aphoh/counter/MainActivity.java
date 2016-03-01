package com.aphoh.counter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  int mCurrentCount = 0;
  boolean mRecording = false;
  TextView mResultView;
  private Button mButton;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mResultView = (TextView) findViewById(R.id.result_text_view);

    mButton = (Button) findViewById(R.id.button);
    mButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (mRecording) {
          mCurrentCount += 1;
        } else {
          startCounting();
        }
      }
    });

  }

  private void startCounting(){
    mButton.setText("Measuring...");
    mRecording = true;
    mCurrentCount += 1;
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        float clickrate = ((float) mCurrentCount) / 10f;
        mResultView.setText(String.format("Total click rate was: %s per second", clickrate));
        timeoutClicks(5);
        mRecording = false;
        mCurrentCount = 0;
      }
    }, 10 * 1000);
  }

  private void timeoutClicks(int secs){
    mButton.setEnabled(false);
    mButton.setText("Test Finished");
    new Handler().postDelayed(new Runnable() {
      @Override public void run() {
        mButton.setEnabled(true);
        mButton.setText("Ready");
      }
    }, secs * 1000);

  }

}
