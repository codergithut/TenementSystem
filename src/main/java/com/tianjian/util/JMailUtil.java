package com.tianjian.util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

/**
 * Created by tianjian on 2019/2/16.
 */
public class JMailUtil {

    public static boolean sendMessageToUser(JavaMailSender javaMailSender, String sendMail, String fromMail, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom(fromMail);//设置发信人，发信人需要和spring.mail.username配置的一样否则报错
            message.setTo(sendMail);				//设置收信人
            message.setSubject("操作验证码");	//设置主题
            message.setText(content,true);  	//第二个参数true表示使用HTML语言来编写邮件
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
