import java.time.LocalTime;

public interface Lib {

    static String getGreeting() {
        int hour = LocalTime.now().getHour();
        if (hour < 12) {
            return "Good Morning";
        } else if (hour < 18) {
            return "Good Afternoon";
        } else {
            return "Good Evening";
        }
    }
}
