package com.example.twitter.config;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
@Slf4j
public class MailConfiguration {

    private static final String APPLICATION_NAME="twitter";

    private static JsonFactory JSON_FACTORY= GsonFactory.getDefaultInstance();

    private static final String TOKENS_DIRECTORY_PATH="tokens";

    private static final List<String> scopes= Collections.singletonList(GmailScopes.GMAIL_SEND);

    private static final String CREDENTIALS_FILE_PATH="C:\\Users\\rohit\\IdeaProjects\\twitter\\target\\classes\\Credentials.json";


    private Credential getCredentials(final NetHttpTransport Http_Transport) throws IOException {
        // Load the credentials file as an input stream directly
        InputStream in = MailConfiguration.class.getResourceAsStream("/Credentials.json");

        if (in == null) {
            throw new FileNotFoundException("Credential file not found");
        }

        GoogleClientSecrets googleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                Http_Transport, JSON_FACTORY, googleClientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("TOKENS_DIRECTORY_PATH")))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        return credential;
    }

    @Bean
    public Gmail getService() throws IOException{

        NetHttpTransport HTTP_TRANSPORT;

        try{
            HTTP_TRANSPORT= GoogleNetHttpTransport.newTrustedTransport();
            return new Gmail.Builder(HTTP_TRANSPORT,JSON_FACTORY,getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME).build();

        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

    }
}
