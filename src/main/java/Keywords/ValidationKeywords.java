package Keywords;

import org.openqa.selenium.support.ui.Select;

public class ValidationKeywords extends GenericKeywords {

	public void validateTitle(String titlename) {

	}

	public void validateText(String textName) {

	}

	public void validateElementPresent(String locator) {

		boolean result = isElementpresent(locator);
		reportFailure("Element not present" + locator, true);

	}

	public void validatelogin(String elementName) {

	}

	// Drop option not present validation function
	public void validateSelectedValueInDropdown(String locatorKey, String option) {
		Select s = new Select(getElement(locatorKey));
		String text = s.getFirstSelectedOption().getText();

		if (!text.endsWith(option)) {

			logFailure("option" + option + " not present in dropdwon" + locatorKey, true);
		}

	}

	// Drop option present validation function
	public void validateSelectedValueNotInDropdown(String locatorKey, String option) {
		Select s = new Select(getElement(locatorKey));
		String text = s.getFirstSelectedOption().getText();

		if (text.endsWith(option)) {

			logFailure("option" + option + "  present in dropdwon" + locatorKey, true);
		}

	}

}
