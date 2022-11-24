public class Main {
    public static void main(String[] args) {
        MailSender mailSender = new MailSender("localhost", 25);
        mailSender.send("victor@nsa.gov", "bastian.chollet@heig-vd.ch", "Tu veux gagner des millions de US DOLLARS ?", "Rejoins moi à 10h derrière la cafet");

    }
}