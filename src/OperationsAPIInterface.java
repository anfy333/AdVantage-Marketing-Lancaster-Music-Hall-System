import java.util.List;
import java.time.LocalDate;
public interface OperationsAPIInterface {
    // Venue and Scheduling Data
    //List<Booking> getVenueAvailability(LocalDate date);
    //boolean isSpaceAvailable(LocalDate date, String room);

    // Film Programming Data
    boolean getScheduledFilms(LocalDate date);

    // Group Bookings Data
    List<Integer> getGroupBookings(LocalDate date);

    // Membership Priority Booking (Operations should check if a member is eligible)
    boolean isMemberEligibleForPriorityBooking(int memberId, LocalDate eventDate);

    // Revenue & Financial Reports
    //double getRevenueReport(LocalDate startDate, LocalDate endDate);
}
