import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MailSender {
    private String host;
    private int port;

    public MailSender(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public int send(String from, String to, String subject, String content){
        try {
            Socket socket = new Socket(host, port);
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            BufferedReader is = new BufferedReader(new InputStreamReader (socket.getInputStream(),  StandardCharsets.UTF_8));

            while(!is.ready());
            while(is.ready()){
                System.out.println(is.readLine());
            }

            String EHLOrequest = "EHLO test.com\r\n";
            os.write(EHLOrequest);
            os.flush();
            System.out.println(EHLOrequest);

            while(!is.ready());
            while(is.ready()){
                System.out.println(is.readLine());
            }


            String FROMrequest = "MAIL FROM:<" + from + ">\r\n";
            System.out.println(FROMrequest);
            os.write(FROMrequest);
            os.flush();

            while(!is.ready());
            while(is.ready()){
                System.out.println(is.readLine());
            }

            String RCPTrequest = "RCPT TO: <" + to + ">\r\n";
            System.out.println(RCPTrequest);
            os.write(RCPTrequest);
            os.flush();

            while(!is.ready());
            while(is.ready()){
                System.out.println(is.readLine());
            }

            String DATArequest = "DATA\r\n";
            System.out.println(DATArequest);
            os.write(DATArequest);
            os.flush();

            while(!is.ready());
            while(is.ready()){
                System.out.println(is.readLine());
            }

            String DATAContentrequest = "From: malo@malo.com\r\n" +
                    "To: "+to+"\r\n" +
                    "Subject: "+subject+"\r\n\n" +
                    content +
                    "\r\n" +
                    ".\r\n";
            System.out.println(DATAContentrequest);
            os.write(DATAContentrequest);
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
