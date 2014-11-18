package shop.common.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.*;

/**
 * User: Dori Ding
 * Date: 13-1-19
 * Time: 14:08
 */
public class EmailSender {

    private static final Logger logger = Logger.getLogger(EmailSender.class);

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static EmailSender ourInstance = new EmailSender();

    private String emailForm;
    private String emailBcc;
    private String userName;
    private String password;
    private String emailProtocol;
    private String emailHost;
    private String emailPort;

    private EmailSender(){
        emailForm = ParamUtils.param("EMAIL_API", "FROM");
        emailBcc = ParamUtils.param("EMAIL_API", "BCC");
        userName = ParamUtils.param("EMAIL_API", "USERNAME");
        password = ParamUtils.param("EMAIL_API", "PASSWORD");
        emailProtocol = ParamUtils.param("EMAIL_API", "PROTOCOL");
        emailHost = ParamUtils.param("EMAIL_API", "HOST");
        emailPort = ParamUtils.param("EMAIL_API", "PORT");
    }

    public static EmailSender getInstance() {
        return ourInstance;
    }

    public void send(String to, String subject, String templateName, Map data) throws Exception {
        this.send(to, null, null, subject, templateName, data, null);
    }

    public void send(String to, String cc, String bcc, String subject, String templateName, Map data, String attachmentPath) throws Exception {
        try {
            String html = getHtml(templateName, data);
            this.sendTo(to, cc, bcc, subject, html, attachmentPath);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Exception(">>>>>>>>>>>> Send email fail: " + e.getMessage());
        }
    }


    private String getHtml(String templateName, Map data) throws IOException, TemplateException {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("email_template/" + templateName);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int i = 0;
        byte[] buff = new byte[1024];
        while ((i = in.read(buff)) != -1) {
            out.write(buff, 0, i);
        }
        in.close();
        out.close();
        String tempBody = new String(out.toByteArray(),DEFAULT_ENCODING);

        Configuration config = new Configuration();
        config.setTemplateLoader(new StringTemplateLoader(tempBody));
        config.setDefaultEncoding(DEFAULT_ENCODING);
        config.setNumberFormat(FreeMarkerUtil.FMK_NUMBER_FORMATE);

        Template template = config.getTemplate("", DEFAULT_ENCODING);
        template.setClassicCompatible(true);
        StringWriter w = new StringWriter();
        template.process(data, w);
        w.flush();

        String html = w.getBuffer().toString();

        return html;
    }

    private void sendByGmail(String to, String cc, String bcc, String subject, String context) throws Exception {
        Properties props = getEmailProperty();

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });
//        session.setDebug(true);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(userName));
            msg.setSubject(subject);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            if (cc != null && !cc.equals("")) {
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }
//            String addresslist = Config.getInstance(Constants.CONFIG_FILE_NAME).get("email.bcc");
            if (bcc != null && !bcc.equals("")) {
                msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
            }
            msg.setContent(context, "text/html;charset=UTF-8");
            Transport.send(msg);
            logger.warn("================== Email have been sent! =================");
        } catch (MessagingException e) {
            throw new Exception(">>>>>>>>>>>> Send email fail: " + e.getMessage());
        }

    }

    public void sendTo(String to, String cc, String bcc, String subject, String context, String attachmentPath) throws Exception {
        String from = emailForm;
        if (null == bcc) {
            bcc = emailBcc;
        }
        if (null == attachmentPath) {
            sendByGmail(to, cc, bcc, subject, context);
        } else {
            sendByGmail(to, cc, bcc, subject, context, attachmentPath);
        }
    }

    private void sendByGmail(String to, String cc, String bcc, String subject, String context, String attachmentPath) throws Exception {
        Properties props = getEmailProperty();

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });
//        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(userName));
            message.setSubject(subject);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            if (cc != null && !cc.equals("")) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }
//            String addresslist = Config.getInstance(Constants.CONFIG_FILE_NAME).get("email.bcc");
            if (bcc != null && !bcc.equals("")) {
                message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
            }

            message.setSentDate(new Date());
            // Set the email body
            MimeBodyPart messagePart = new MimeBodyPart();
            messagePart.setText(context, "UTF-8", "html");
            // Set the email attachment file
            MimeBodyPart attachmentPart = new MimeBodyPart();

            FileDataSource fileDataSource = new FileDataSource(attachmentPath) {
                public String getContentType() {
                    return "application/octet-stream";
                }
            };

            attachmentPart.setDataHandler(new DataHandler(fileDataSource));
            attachmentPart.setFileName(fileDataSource.getName());
            // Add all parts of the email to Multipart object
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messagePart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);
            Transport.send(message);

            logger.warn("================== Email have been sent! =================");
        } catch (MessagingException e) {
            throw new Exception(">>>>>>>>>>>> Send email fail: " + e.getMessage());
        }

    }


    private Properties getEmailProperty() {

        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", emailProtocol);
        props.setProperty("mail.smtp.host", emailHost);
        props.setProperty("mail.smtp.port", emailPort);
        props.setProperty("mail.smtp.starttls.enable", "true");
//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        return props;
    }

    public static void main(String[] args) throws Exception {
//        EmailSender.getInstance().send("dorid@synnex.com", "test", "<font color='red'>Testtttt</font>");
//        EmailSender.getInstance().send("dorid@synnex.com", "test", "<a href='www.baidu.com'>Testtttt</a>");
        Map m = new HashMap();
//        m.put("html", "aaaaaaaaaa");
        m.put("orderNo", "12345");
        m.put("userName", "1234snx");
        List<String> list = new ArrayList<String>();
        list.add("S1");
        list.add("S2");
        m.put("server", list);
        EmailSender.getInstance().send("andytang@synnex.com", "testtt", "enable_service_success.ftl", m);
    }
}
