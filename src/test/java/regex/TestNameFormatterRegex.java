package regex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practice.regex.FullNameFormatterRegex;

@DisplayName("Форматирование ФИО c помощью регулярных выражений")
public class TestNameFormatterRegex {

    private ByteArrayOutputStream outContent;
    private ByteArrayInputStream inContent;
    /**
     * original in/out streams links
     */
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;
    /**
     * string when format is wrong
     */
    private static final String WRONG_FORMAT = "Неверное форматирование\n";
    /**
     * string at which the testing program will terminate
     */
    private static final String EXIT_CODE = "0";
    /**
     * os dependent line separator
     */
    private static final String LS = System.lineSeparator();
    /**
     * max time to execute each test, on occasion infinity loop
     */
    private static final int TEST_TIMEOUT_SECONDS = 10;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    @DisplayName("Текст = Ivanov Ivan Ivanovich")
    void formatFullName() {
        launchApplication(
                collectTestInput("Ivanov Ivan Ivanovich"),
                collectTestOutput(
                        String.format("Surname: %s%nName: %s%nPatronymic: %s", "Ivanov", "Ivan", "Ivanovich"))
        );
    }

    @Test
    @DisplayName("Текст = Saltikov-Shedrin Mihail Evgenevich")
    void formatFullNameWithDoubleSurname() {
        launchApplication(
                collectTestInput("Saltikov-Shedrin Mihail Evgenevich"),
                collectTestOutput(
                        String.format("Surname: %s%nName: %s%nPatronymic: %s", "Saltikov-Shedrin", "Mihail", "Evgenevich"))
        );
    }

    @Test
    @DisplayName("Текст = Ivanov Ivan Ivanovich vv2vvv")
    void superfluousWord() {
        launchApplication(
                collectTestInput("Ivanov Ivan Ivanovich vv2vvv"),
                collectTestOutput("The string entered is not a full name."));
    }

    @Test
    @DisplayName("Текст = 1111 2222 3333")
    void numberInput() {
        launchApplication(
                collectTestInput("1111 2222 3333"),
                collectTestOutput("The string entered is not a full name."));
    }

    @Test
    @DisplayName("Текст = Ivan")
    void oneWord() {
        launchApplication(
                collectTestInput("Ivan"),
                collectTestOutput("The string entered is not a full name."));
    }

    @Test
    @DisplayName("Проверка используются ли методы регулярных выражений")
    void checkRegularExpression() {
        final List<String> lines = new ArrayList<>();
        try {
            Path path = Paths.get("src", "main", "java", "practice","regex","FullNameFormatterRegex.java");
            lines.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> errLines = lines.stream()
                .filter(line -> line.matches(".*(split|matches|Pattern|Matcher|replace).*"))
                .collect(Collectors.toList());

        assertFalse(errLines.isEmpty(),
                "\nВы не использовали регулярные выражения\n");
    }

    /**
     * call user method
     *
     * @param userInputMock - string lines imitate real user input
     * @param expected      - expected console output
     */

    private void launchApplication(String userInputMock, String expected) {
        provideInput(userInputMock);
        assertTimeoutPreemptively(
                Duration.ofSeconds(TEST_TIMEOUT_SECONDS),
                () -> FullNameFormatterRegex.main(new String[0]));
        assertEquals(expected.strip(), replaceSeparatorToCurrentOs(outContent.toString()), WRONG_FORMAT);
    }

    /*
  Replace CRLF and LF to OS dependent separator
  @param text - string to apply
   */
    private String replaceSeparatorToCurrentOs(String text) {
        return text.strip()
                .replaceAll("\r\n", "\n")
                .replaceAll("\n", System.lineSeparator());
    }

    /*
    Change default System.in and fill with strings to imitate user input
    @param data - string for application input
     */
    private void provideInput(String data) {
        inContent = new ByteArrayInputStream(data.getBytes());
        System.setIn(inContent);
    }

    /*
    Construct string with lines separators and EXIT_CODE which signal that application must stop
    @param cases - strings, emulated user inputs for application
     */
    private String collectTestInput(String... cases) {
        return collectTestOutput(cases)
                .concat(EXIT_CODE);
    }

    /*
  Construct string with lines separators, use for base string actual, expected
  @param cases - strings, input/output for each line
   */

    private String collectTestOutput(String... cases) {
        return String.join(LS, cases)
                .concat(LS);
    }

}
