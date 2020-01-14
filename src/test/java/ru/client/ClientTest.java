package ru.client;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Ignore
public class ClientTest {
    static private final String TMPDIR = System.getProperty("java.io.tmpdir");
    private final InputStream backupIn = System.in;
    private final PrintStream backupOut = System.out;
    private ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

    @After
    public void tearDown() {
        System.setIn(backupIn);
        System.setOut(backupOut);
    }

    @Test
    public void testWithWrongStateFromServer() {
        String input = "772431842240" + System.lineSeparator() + "q";
        System.setOut(new PrintStream(testOutput));
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        new Client().init();
        System.setOut(backupOut);
        var expect = String.format("%s%n%s%n%s%n%s%n",
                "Введите ИНН или адрес файла со списком ИНН. Для выхода введите q",
                "Проверка запроса...",
                "ИНН: 772431842240 Результат: 5 - Некорректный ИНН",
                "Введите ИНН или адрес файла со списком ИНН. Для выхода введите q"
        );
        var result = testOutput.toString();

        assertThat(result, is(expect));
    }

    @Test
    public void testWithRightStateFromServer() {
        String input = "7713011336" + System.lineSeparator() + "q";
        System.setOut(new PrintStream(testOutput));
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        new Client().init();
        System.setOut(backupOut);
        var expect = String.format("%s%n%s%n%s%s%n%s%n",
                "Введите ИНН или адрес файла со списком ИНН. Для выхода введите q",
                "Проверка запроса...",
                "ИНН: 7713011336 Результат: 3 - Налогоплательщик с указанным ИНН ",
                "зарегистрирован в ЕГРН (КПП не соответствует ИНН или не указан)",
                "Введите ИНН или адрес файла со списком ИНН. Для выхода введите q"
        );
        var result = testOutput.toString();

        assertThat(result, is(expect));
    }

    @Test
    public void testWhenINNFromFile() throws IOException {
        var file = new File(TMPDIR + File.separator + "test.txt");
        file.createNewFile();
        try (BufferedWriter writer = new BufferedWriter(new PrintWriter(file))) {
            writer.write(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%n%s%n%s",
                    "7713011336",
                    "0013011336",
                    "wrong",
                    "771301133634234",
                    "7713011",
                    "7721503733",
                    "672204588096",
                    "772481742000")
            );
        }
        String input = file.getAbsolutePath() + System.lineSeparator() + "q";
        System.setOut(new PrintStream(testOutput));
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        new Client().init();
        System.setOut(backupOut);
        var expect = String.format("%s%n%s%n%s%s%n%s%n",
                "Введите ИНН или адрес файла со списком ИНН. Для выхода введите q",
                "Проверка запроса...",
                "ИНН: 7713011336 Результат: 3 - Налогоплательщик с указанным ИНН ",
                "зарегистрирован в ЕГРН (КПП не соответствует ИНН или не указан)",
                "Введите ИНН или адрес файла со списком ИНН. Для выхода введите q"
        );
        var result = testOutput.toString();

        assertThat(result, is(expect));
    }
}