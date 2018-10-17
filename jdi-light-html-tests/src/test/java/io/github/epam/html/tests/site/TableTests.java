package io.github.epam.html.tests.site;

import com.epam.jdi.light.elements.complex.table.Table;
import io.github.epam.TestsInit;
import io.github.epam.html.tests.site.steps.Preconditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.github.com.StaticSite.performancePage;
import static io.github.com.pages.PerformancePage.users;
import static io.github.com.pages.PerformancePage.usersFast;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static org.testng.Assert.assertEquals;

public class TableTests extends TestsInit {
    @BeforeMethod
    public void before() {
        Preconditions.shouldBeLoggedIn();
        performancePage.shouldBeOpened();
    }
    @Test
    public void tableTest() {
        out.println("==================");
        out.println("Table");
        tableTestScenario(users);
        out.println("==================");
        out.println("Fast Table");
        tableTestScenario(usersFast);
        out.println("==================");
    }
    private void tableTestScenario(Table table) {
        timeStart = currentTimeMillis();
        assertEquals(table.row(1).getValue(), "Burke Tucker,076 1971 1687,et.euismod.et@ut.edu,GozŽe");
        logTime("Get 1 row");
        String zacharyEmail = "ipsum.non.arcu@auctorullamcorper.ca";
        assertEquals(table.cell(3,4), zacharyEmail);
        logTime("Get cell(3,4)");
        assertEquals(table.cell("Email",4), zacharyEmail);
        logTime("Get cell(Email,4)");
        assertEquals(table.column(2).getValue().substring(0, 30),
                "076 1971 1687,(011307) 16843,0");
        logTime("Get column(1)");
        String value = table.preview();
        assertEquals(value.substring(0,198),
                "Name Phone Email City\n" +
                        "Burke Tucker 076 1971 1687 et.euismod.et@ut.edu GozŽe\n" +
                        "Grady Brock (011307) 16843 cursus.et@commodo.org Alcobendas\n" +
                        "Harding Lloyd 0800 1111 neque.In.ornare@mauris.co.uk Beauvais\n");
        logTime("Preview");
        value = table.getValue();
        assertEquals(value.substring(0,225),
                "||X||Name|Phone|Email|City||\n" +
                        "||1||Burke Tucker|076 1971 1687|et.euismod.et@ut.edu|GozŽe||\n" +
                        "||2||Grady Brock|(011307) 16843|cursus.et@commodo.org|Alcobendas||\n" +
                        "||3||Harding Lloyd|0800 1111|neque.In.ornare@mauris.co.uk|Beauvais||");
        logTime("Get value");
    }

    private long timeStart;
    private void logTime(String description) {
        out.println(description + ": " + (currentTimeMillis() - timeStart) + "ms");
        timeStart = currentTimeMillis();
    }
}