/**
 * Sovilla Flavio & Romano Malo
 */

package DAI_SMTP;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class MailSender {
    private String host;
    private int port;

    /**
     * Create a MailSender
     * @param host SMTP host address or name
     * @param port SMTP host port
     */
    public MailSender(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // TODO : Gérer le retour du serveur qui s'affiche qu'à moitié pour l'instant

    /**
     * Send an email
     * @param from Sender mail address
     * @param to List of receivers mail addresses
     * @param subject Mail subject
     * @param content Mail content
     * @return 0 if error, 1 if success
     */
    public int send(String from, List<String> to, String subject, String content) {
        try {
            Socket socket = new Socket(host, port);
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            while (!is.ready()) ;
            while (is.ready()) {
                System.out.println(is.readLine());
            }

            os.write("EHLO " + host + "\r\n");
            os.write("MAIL FROM:<" + from + ">\r\n");
            for (String receiver : to) {
                os.write("RCPT TO: <" + receiver + ">\r\n");
            }
            os.write("DATA\r\n");
            os.write("From: " + from + "\r\n");
            os.write("To: " + String.join(",", to) + "\r\n");
            os.write("Content-Type: text/plain; charset=utf-8\r\n");
            os.write("Subject: " + "=?utf-8?B?" + Base64.getEncoder().encodeToString(subject.getBytes()) + "?=\r\n\r\n");
            os.write(content);
            os.write("\r\n.\n");
            os.write("QUIT\r\n");
            os.flush();

            /*
            String line;
            while ((line = is.readLine()) != null) {
                System.out.println(line);
            }*/


        } catch (IOException e) {
            System.out.println("ERROR : " + e);
            return 0;
        }
        return 1;
    }
}
