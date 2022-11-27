package DAI_SMTP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        String configContent;
        try{
            Path configPath = Path.of("C:\\Users\\malor\\Documents\\source\\DAI-2022-SMTP\\config.json");
            configContent = Files.readString(configPath);
        } catch (IOException e) {
            System.out.println("Error while reading the file, please check the URI");
            return;
        }

        JSONArray groups, messages;

        try {
            JSONObject configJSON = new JSONObject(configContent);

            groups = configJSON.getJSONArray("groups");
            for(int i = groups.length() - 1; i >= 0; --i){
                if(groups.getJSONObject(i).getJSONArray("mails").length() < Constants.MinimumMailPerGroup)
                    groups.remove(i);
            }
            messages = configJSON.getJSONArray("messages");
        }catch (JSONException e){
            System.out.println("JSON parser error : " + e.getMessage());
            return;
        }

        if(groups.length() < 1){
            System.out.println("No groups were provided");
            return;
        }
        if(messages.length() < 1){
            System.out.printf("No messages were provided");
            return;
        }


        displayGroups(groups);
        int selectedGroup = prompt(groups.length());
        System.out.println(Constants.DisplaySeparator);
        displayMessages(messages);
        int selectedMessage = prompt(messages.length());
        System.out.println(Constants.DisplaySeparator);

        String sender = groups.getJSONObject(selectedGroup).getString("sender");
        JSONArray receiversJSON = groups.getJSONObject(selectedGroup).getJSONArray("mails");
        List<String> receivers = new ArrayList<String>();
        for(int i = 0; i < receiversJSON.length(); ++i){
            receivers.add(receiversJSON.getJSONObject(i).getString("address"));
        }

        String subject = messages.getJSONObject(selectedMessage).getString("subject");
        String content = messages.getJSONObject(selectedMessage).getString("content");



        MailSender mailSender = new MailSender("localhost", 25);
        int i = mailSender.send(sender, receivers, subject, content);
        int j = i+ 1;

    }

    // TODO : Ne pas afficher ni compter les groupes de moins de 3 mails
    public static void displayGroups(JSONArray groups){
        System.out.println("List of groups : ");
        for(int i = 0; i < groups.length(); ++i){
            JSONObject group = groups.getJSONObject(i);
            System.out.println("[" + i + "] : " + group.getString("title"));
            System.out.println("[SENDER] : " + group.getString("sender"));
            System.out.println("[RECEIVERS] : ");
            JSONArray mails = group.getJSONArray("mails");
            for(int j = 0; j < mails.length(); ++j){
                System.out.println(" - " + mails.getJSONObject(i).getString("address"));
            }
        }
    }

    public static void displayMessages(JSONArray messages){
        System.out.println("List of messages : ");
        for(int i = 0; i < messages.length(); ++i){
            JSONObject message = messages.getJSONObject(i);
            System.out.println("[" + i + "] : ");
            System.out.println(" - [SUBJECT] : " + message.getString("subject"));
            System.out.println(" - [CONTENT] : " + message.getString("content"));
        }
    }

    public static int prompt(int nb){
        Scanner input = new Scanner(System.in);
        int selection = -1;

        while(true) {
            System.out.printf("Select option [0 - " + (nb-1) + " ] : " );
            try {
                selection = input.nextInt();
                if(selection < 0 || selection >= nb){
                    System.out.println("Entry must be between 0 and " + (nb-1));
                }
                else return selection;
            }
            catch(Exception e){
                input.nextLine();
                System.out.println("Wrong entry");
            }
        }
    }
}