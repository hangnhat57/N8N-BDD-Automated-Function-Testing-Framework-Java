package tech.nhatnguyen.kishu;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.mail.*;
import javax.mail.search.SubjectTerm;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import static tech.nhatnguyen.shiba.SystemConfiguration.getEnv;


public class EmailHelper {
    private static final String USER_PROTOCOL = getEnv("MAIL_PROTOCOL");
    private static final String USER_HOST = getEnv("MAIL_HOST");
    private static final String USER_EMAIL = getEnv("MAIL_EMAIL");
    private static final String USER_PASSWORD = getEnv("MAIL_PASSWORD");


    public String checkEmail(String Mailtitle, String linktext) throws Exception {
        String linkurl = "";
        /*Set up email protocol and credential to login*/
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", USER_PROTOCOL);
        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore(USER_PROTOCOL);
        store.connect(USER_HOST, USER_EMAIL, USER_PASSWORD);
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        Message[] messages = null;
        boolean isMailFound = false;
        Message mailFromSystem = null;

        for (int i = 0; i < 10; i++) {
            messages = folder.search(new SubjectTerm(Mailtitle), folder.getMessages());

            if (messages.length == 0) {
                Thread.sleep(10000);
            }
        }

        for (Message mail : messages) {
            if (!mail.isSet(Flags.Flag.SEEN)) {
                mailFromSystem = mail;
                isMailFound = true;
            }
        }
        if (!isMailFound) {
            throw new Exception("Could not find new mail with title is: " + Mailtitle);

        } else {
            String line;
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromSystem.getInputStream()));
            Document doc = Jsoup.parse(mailFromSystem.getContent().toString());
            org.jsoup.select.Elements links = doc.select("a[href]");
            for (org.jsoup.nodes.Element link : links) {
                if (link.text().contains(linktext)) {
                    System.out.println("Found the link: " + link.attr("href"));
                    linkurl = link.attr("href");
                }
            }
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            mailFromSystem.setFlag(Flags.Flag.DELETED, true);
            folder.close(true);
            store.close();
        }

        return linkurl;
    }
}
