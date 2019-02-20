package hust.hhh.mystudy.ui.progressbar;

import android.app.Activity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import hust.hhh.mystudy.R;

public class NumberProgressBarActivity extends Activity {

    private Timer mTimer;
    private NumberProgressBar nbp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_progress_bar);

        nbp = this.findViewById(R.id.number_progressbar);

        mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nbp.increaseProgress(1);
                    }
                });
            }
        };
        mTimer.schedule(task, 1000, 100);


        NumberProgressBar.OnProgressBarListener listener = new NumberProgressBar.OnProgressBarListener() {
            @Override
            public void onProgressChange(int progress) {
                if (progress == 100) {
                    mTimer.cancel();
                }
            }
        };
        nbp.setOnProgressBarListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
