package damian.demo.service.email;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;

@SpringBootTest( properties = {"spring.mail.host=localhost",
        "spring.mail.port=25"})

@RunWith(SpringRunner.class)
@ActiveProfiles( "test" )
public class EmailServiceImplTest {


    @Rule
    public GreenMailRule server = new GreenMailRule(new ServerSetup(25, "localhost", "smtp"));

    @Autowired
    private EmailService emailService;

    @Test
    public void shouldSendSingleMail() throws MessagingException{

        String to = "damianjedruchniewicz@gmail.com";
        String subject = "tests";
        String text = "This is only test;)";
        boolean concent = true;

        emailService.sendMail(to, subject, text, concent);

        MimeMessage[] receivedMessages = server.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        MimeMessage current = receivedMessages[0];
        assertEquals(to, current.getAllRecipients()[0].toString());
        assertEquals(subject, current.getSubject());


    }
}
