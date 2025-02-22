import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class FriendsOfLancasterImpl implements FriendOfLancaster{
    private MembershipDB db;
    public FriendsOfLancasterImpl(MembershipDB db) {
        this.db = db;
    }
    @Override
    public boolean isEligibleForPriorityBooking(int memberId, LocalDate eventDate) {
        Member member = db.getMemberById(memberId);
        if (member == null || !member.isActive()) {
            return false;
        }
        LocalDate today = LocalDate.now();
        long daysUntilEvent = ChronoUnit.DAYS.between(today, eventDate);
        return daysUntilEvent >= 14; // 2 weeks pre-sale
    }
    @Override
    public boolean addMember(String name, String surname, String email) {
        // Implementation for adding a member
        Member member = new Member(name, surname, email);
        return true;
    }

    @Override
    public boolean renewMembership(String memberId) {
        // Implementation for renewing a membership
        return true;
    }

    @Override
    public boolean cancelMembership(String memberId) {
        // Implementation for canceling a membership
        return true;
    }

    @Override
    public String getMemberDetails(String memberId) {
        // Implementation for retrieving member details
        return "Member details";
    }

    // 2. Subscription Tracking & Reminders
    @Override
    public boolean checkSubscriptionStatus(int memberId) {
        Member member = db.getMemberById(memberId);
        return member != null && member.isSubscriptionActive();
    }

    @Override
    public void sendRenewalReminder(int memberId) {
        Member member = db.getMemberById(memberId);
        if (member != null && !member.isSubscriptionActive()) {
            // Simulate sending an email notification
            System.out.println("Reminder: Your membership is expiring soon. Please renew!");
        }
    }

    // 3. Retrieve Historical Booking Data
    /*@Override
    public List<Booking> getMemberBookingHistory(int memberId) {
        return membershipDB.getBookingHistory(memberId);
    }

    // 4. Member Segmentation
    @Override
    public List<Member> getSegmentedMembers(String segmentType) {
        List<Member> allMembers = membershipDB.getAllMembers();
        List<Member> segmentedMembers = new ArrayList<>();

        for (Member member : allMembers) {
            int bookings = membershipDB.getBookingCount(member.getId());

            switch (segmentType.toLowerCase()) {
                case "frequent":
                    if (bookings > 10) segmentedMembers.add(member);
                    break;
                case "infrequent":
                    if (bookings > 0 && bookings <= 10) segmentedMembers.add(member);
                    break;
                case "vip":
                    if (bookings > 20) segmentedMembers.add(member);
                    break;
            }
        }
        return segmentedMembers;
    }*/
}
