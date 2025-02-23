import java.util.List;
import java.time.LocalDate;
public class OperationsAPIImpl implements OperationsAPIInterface {
    private FilmAPIImpl filmAPI;
    private GroupBookingsImpl groupBookingAPI;
    private FriendsOfLancasterImpl membershipAPI;
    //private FinanceAPIImpl financeAPI;

    public OperationsAPIImpl( FilmAPIImpl film, GroupBookingsImpl groupBooking,FriendsOfLancasterImpl membership) {
        //this.calendarAPI = calendar;
        this.filmAPI = film;
        this.groupBookingAPI = groupBooking;
        this.membershipAPI = membership;
        //this.financeAPI = finance;
    }
    // 2. Get scheduled films for Operations team
    public boolean getScheduledFilms(LocalDate date) {
        return filmAPI.getFilmsScheduledOn(date);
    }

    // 3. Get group bookings for Operations team
    @Override
    public List<Integer> getGroupBookings(LocalDate date) {
        return groupBookingAPI.getBookingsForDate(date);
    }

    // 4. Check if a member has priority booking access (Operations team needs to verify eligibility)
    @Override
    public boolean isMemberEligibleForPriorityBooking(int memberId, LocalDate eventDate) {
        return membershipAPI.isEligibleForPriorityBooking(memberId, eventDate);
    }

}
