import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        String configContent;
        try{
            Path configPath = Path.of("C:\\Users\\malor\\Documents\\source\\DAI-2022-SMTP\\config.json");
            configContent = Files.readString(configPath);
        } catch (IOException e) {
            return;
        }

        JSONObject configJSON = new JSONObject(configContent);

        MailSender mailSender = new MailSender("localhost", 25);
        mailSender.send("victor@nsa.gov", "bastian.chollet@heig-vd.ch", "Tu veux gagner des millions de US DOLLARS ?", "Rejoins moi à 10h derrière la cafet");

    }
}