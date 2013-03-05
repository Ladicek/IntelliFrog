import com.intellij.testFramework.ParsingTestCase;
import cz.ladicek.intellifrog.parser.FrogParserDefinition;

public class FrogParsingTest extends ParsingTestCase {
    public FrogParsingTest() {
        super("", "sf", new FrogParserDefinition());
    }

    public void testParsingTestData() {
        doTest(true);
    }

    @Override
    protected String getTestDataPath() {
        return "testData";
    }
}
