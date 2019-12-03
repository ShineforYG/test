package thread;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class CheckerTask extends TimerTask {
    static Timer timer = new Timer();

    static void launch(){
        timer.schedule(new CheckerTask(),OutTime.getOutTime());
    }
    @Override
    public void run() {
        long now = Calendar.getInstance().getTimeInMillis();
        long last = OutTime.getLastTime();
        long tar = last + OutTime.getOutTime();
        if (now > tar) {
            System.out.println("超时");
            timer.cancel();
        } else {
            System.out.println("没有超时");
            long delay = tar - now;
            timer.schedule(new CheckerTask(), delay);
        }
    }
}
