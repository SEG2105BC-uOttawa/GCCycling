public class CyclingClubAccount extends Account {

    private String cyclingClubName;

    public CyclingClubAccount(String username, String pwd, String cyclingClubName) {
        super(username, pwd);
        this.cyclingClubName = cyclingClubName;
    }

}
