<<<<<<< HEAD
package org.hngxfreelunch.LiquidApplicationApi.services.email;

import lombok.extern.slf4j.Slf4j;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.EmailRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.MailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
@Slf4j
public class BrevoEmailServiceImpl implements EmailService {

    @Autowired
    private WebClient.Builder webClient;

    @Value("${sendinblue.mail.url}")
    private String mailUrl;

    @Value("${mail.api.key}")
    private String mailApiKey;

    @Value("${sendinblue.mail.name}")
    private String senderName;

    @Value("${sendinblue.mail.email}")
    private String senderEmail;

    @Value("${brevo_api_key}")
    private String API_KEY;

    @Value("${api_url}")
    private String API_URL;

    @Async
    @Override
    public void sendEmail(String to, String subject, String htmlContent) {
        WebClient client = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("api-key", API_KEY)
                .build();

        EmailRequest emailRequest = EmailRequest.builder()
                .sender(MailInfo.builder().email(senderEmail).name(senderName).build())
                .to(List.of(MailInfo.builder().email(to).name(to).build()))
                .htmlContent(htmlContent)
                .subject(subject)
                .build();

        client.post()
                .uri(API_URL)
                .body(BodyInserters.fromValue(emailRequest))
                .retrieve()
                .toBodilessEntity()
                .block();
        log.info("Registration Email sent for user: " + to + "!");
    }

}
=======
package org.hngxfreelunch.LiquidApplicationApi.services.email;

import lombok.extern.slf4j.Slf4j;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.EmailRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.MailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
@Slf4j
public class BrevoEmailServiceImpl implements EmailService {

    @Autowired
    private WebClient.Builder webClient;

    @Value("${sendinblue.mail.url}")
    private String mailUrl;

    @Value("${mail.api.key}")
    private String mailApiKey;

    @Value("${sendinblue.mail.name}")
    private String senderName;

    @Value("${sendinblue.mail.email}")
    private String senderEmail;

    @Value("${brevo_api_key}")
    private String API_KEY;

    @Value("${api_url}")
    private String API_URL;

    @Async
    @Override
    public void sendEmail(String to, String subject, String htmlContent) {
        WebClient client = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("api-key", API_KEY)
                .build();

        EmailRequest emailRequest = EmailRequest.builder()
                .sender(MailInfo.builder().email(senderEmail).name(senderName).build())
                .to(List.of(MailInfo.builder().email(to).name(to).build()))
                .htmlContent(htmlContent)
                .subject(subject)
                .build();

        client.post()
                .uri(API_URL)
                .body(BodyInserters.fromValue(emailRequest))
                .retrieve()
                .toBodilessEntity()
                .block();
        log.info("Registration Email sent for user: " + to + "!");
    }

}
>>>>>>> 37617686d864be3d5717d47a2fb029fe19dd3361
