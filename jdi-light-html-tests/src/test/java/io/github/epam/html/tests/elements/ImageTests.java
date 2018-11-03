package io.github.epam.html.tests.elements;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.light.elements.base.WindowsManager.acceptAlert;
import static com.epam.jdi.light.elements.base.WindowsManager.getAlertText;
import static com.epam.jdi.light.elements.composite.WebPage.getUrl;
import static io.github.com.StaticSite.htmlElementsPage;
import static io.github.com.pages.HtmlElementsPage.jdiLogo;
import static io.github.com.pages.HtmlElementsPage.jdiLogo;
import static io.github.epam.html.tests.elements.BaseValidations.baseValidation;
import static io.github.epam.html.tests.site.steps.Preconditions.shouldBeLoggedIn;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class ImageTests extends TestsInit {

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        htmlElementsPage.shouldBeOpened();
    }
    String text = "/JDI/images/jdi-logo.jpg";


    @Test
    public void getValueTest() {
        assertEquals(jdiLogo.getValue(), text);
    }

    @Test
    public void getSrcTest() {
        assertEquals(jdiLogo.src(), text);
    }
    @Test
    public void getAltTest() {
        assertEquals(jdiLogo.alt(), "Jdi Logo 2");
    }

    @Test
    public void clickTest() {
        jdiLogo.click();
        assertEquals(getAlertText(), "JDI Logo");
        acceptAlert();
    }

    @Test
    public void isValidationTest() {
        jdiLogo.is().src(containsString("jdi-logo.jpg"));
        jdiLogo.is().alt(is("Jdi Logo 2"));
        jdiLogo.is().height(100);
        jdiLogo.is().width(101);
    }


    @Test
    public void assertValidationTest() {
        jdiLogo.assertThat().alt(is("Jdi Logo 2"));
    }

    @Test
    public void baseValidationTest() {
        baseValidation(jdiLogo);
    }
}