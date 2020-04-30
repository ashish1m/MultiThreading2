import model.Activity;

import java.util.concurrent.atomic.AtomicBoolean;

public class App {

    public static void main(String[] args) {
        System.out.println("Program started.");
        AtomicBoolean isProcessing = new AtomicBoolean();
        RemoteService service = new RemoteService();
        service.getUserRecentActivities((activities) -> {
            for (Activity activity :
                    activities) {
                System.out.println(activity);
            }
            isProcessing.set(false);
        });
        isProcessing.set(true);

        while (isProcessing.get()){
            //keep running
        }

        service.stop();
        System.out.println("Program terminated.");
    }
}
