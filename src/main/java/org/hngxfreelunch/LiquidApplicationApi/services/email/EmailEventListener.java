package org.hngxfreelunch.LiquidApplicationApi.services.email;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.FreeLunchException;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailEventListener implements ApplicationListener<EmailEvent> {
    private final JavaMailServiceImpl javaMailService;
    @Override
    public void onApplicationEvent(EmailEvent event) {
        try{
            javaMailService.sendEmailJava(event.getEmail(), event.getSubject(), event.getFrom(), event.getHtmlContent());
        }catch(MessagingException | MailSendException e){
            e.printStackTrace();
            throw new FreeLunchException("Email Sending Failed, Please Retry");
        }
    }
}
