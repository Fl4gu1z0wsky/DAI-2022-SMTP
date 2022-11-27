package DAI_SMTP;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MailSender {
    private String host;
    private int port;

    public MailSender(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // TODO : Gérer l'encodage
    public int send(String from, List<String> to, String subject, String content){
        try {
            Socket socket = new Socket(host, port);
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            BufferedReader is = new BufferedReader(new InputStreamReader (socket.getInputStream(),  StandardCharsets.UTF_8));

            os.write("EHLO test.com\r\n");
            os.write("MAIL FROM:<" + from + ">\r\n");
            for(String receiver : to){
                os.write("RCPT TO: <" + receiver + ">\r\n");
            }
            os.write("DATA\r\n");
            os.write("From: " + from +"\r\n");
            os.write("To: "+String.join(",", to)+"\r\n");
            os.write("Subject: "+subject+"\r\n\r\n");
            os.write(content);
            os.write("\r\n");
            os.write(".\r\n");
            os.write("QUIT\r\n");
            os.flush();

            while(!is.ready());
            while(is.ready()){
                System.out.println(is.readLine());
            }


        }
        catch(IOException e){
            System.out.println("ERROR : " + e);
            return -1;
        }
        return 0;
    }
}
