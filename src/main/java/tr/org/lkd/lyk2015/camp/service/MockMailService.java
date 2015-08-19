package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;

@Service
public class MockMailService implements MailService {

	@Override
	public boolean sendEmail(String to, String subject, String content) {
		// mailGonder
		// mailGittyse

		return true;

	}

}
