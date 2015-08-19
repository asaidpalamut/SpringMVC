package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;

@Service
public class MockExamService implements ExamService {

	@Override
	public boolean isExamSuccess(Long tckn, String email) {

		if (email.equals("saitpalam@gmail.com")) {

			return false;
		}

		return true;

	}

}
