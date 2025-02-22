public class Member {
    private int id;
    private String name;
    private String surname;
    private String email;
    private boolean isActive;
    private boolean subscriptionActive;

    public Member(String name, String surname, String email) {
        this.id = 7;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isActive = true;
        this.subscriptionActive = true;
    }

    public int getId() { return id; }
    public boolean isActive() { return isActive; }
    public boolean isSubscriptionActive() { return subscriptionActive; }
}
