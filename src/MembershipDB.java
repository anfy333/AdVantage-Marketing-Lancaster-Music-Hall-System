import java.util.List;
import java.util.ArrayList;

public class MembershipDB {
    private List<Member> members;
    //private List<Booking> bookings;

    public MembershipDB() {
        this.members = new ArrayList<>();
        //this.bookings = new ArrayList<>();
    }
    public Member getMemberById(int memberId) {
        return null;
        //return members.stream().filter(m -> m.getId() == memberId).findFirst().orElse(null);
    }
}
