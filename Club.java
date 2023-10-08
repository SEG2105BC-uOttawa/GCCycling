public class Club extends Account {

    private String clubName;

    public Club(String username, String password, String role) {
        super(username, password, role);
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return this.clubName;
    }

}
