package com.example.webhub.mail;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public interface EmailService {

    void sendTextEmail(String people, String subject, String text) throws UnsupportedEncodingException;

    void sendEmailWithAttachment();

    void sendHTMLEmail();
}