package tr.org.lkd.lyk2015.camp.service;

public interface MailService {

	public abstract boolean sendEmail(String to, String subject, String content);

}
