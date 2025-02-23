import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface GroupBookings {
    boolean bookGroup( int groupSize, LocalDateTime date, String venue);
    boolean updateGroupBooking(int bookingId, LocalDateTime newdate,int newGroupSize);
    boolean cancelGroupBooking(int bookingId);
    String getBookingDetails(int bookingId);
    List<Integer> getBookingsForDate (LocalDate date);

}
