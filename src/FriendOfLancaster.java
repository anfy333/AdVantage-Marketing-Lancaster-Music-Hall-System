import java.time.LocalDate;
public interface FriendOfLancaster {
    boolean addMember(String name, String surname, String email);
    boolean renewMembership(String memberId);
    boolean cancelMembership(String memberId);
    String getMemberDetails(String memberId);

    boolean isEligibleForPriorityBooking(int memberId, LocalDate eventDate);

    // Track subscription status and send renewal reminders
    boolean checkSubscriptionStatus(int memberId);
    void sendRenewalReminder(int memberId);
}
