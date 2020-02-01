package edu.quinnipiac.ser210.ch4stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {

    private int seconds = 0; // record the number of seconds passed
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        runTimer(); // seperate method to update the stopwatch. Start when activity is created
    }

    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view) {
        running = true; // starts the stopwatch
    }
    //Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) {
        running = false; //stops the stopwatch
    }
    //Reset the stopwatch when the Reset button is clicked.
    public void onClickReset(View view) {
        running = false; //stops the stopwatch
        seconds = 0; // set the seconds to zero
    }
    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.time_view); //get the text view

        //Handler to handle help schedule code
        final Handler handler = new Handler(); // creates new handler
        handler.post(new Runnable() { //calls post() method, passing new runnable. Will run with out delay

            @Override
            public void run(){
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                //formats the seconds into hours, min, secs
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000); // post to run code again after delay of 1,000 miliseconds

            }
        });



    }
}
