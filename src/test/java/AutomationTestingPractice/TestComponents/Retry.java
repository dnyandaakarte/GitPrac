package AutomationTestingPractice.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	@Override
	public boolean retry(ITestResult result) {
		int count = 0;
		int maxtry = 1;
		if(count<maxtry) {
			count++;
			return true;
		}
		else {
			return false;
		}
	}

}
