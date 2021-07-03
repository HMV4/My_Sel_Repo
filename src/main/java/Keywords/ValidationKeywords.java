package Keywords;

public class ValidationKeywords extends GenericKeywords{

	
	public void validateTitle(String titlename) {

	}
	public void validateText(String textName) {

	}
	public void validateElementPresent(String locator) {
		
		boolean result = isElementpresent(locator);
		reportFailure("Element not present" +locator,true);
		

	}
		public void validatelogin(String elementName) {
	
		}
		
	}
