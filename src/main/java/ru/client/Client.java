package ru.client;

import ru.nalog.npchk.FNSNDSCAWS2;
import ru.nalog.npchk.NdsRequest2;
import static ru.nalog.npchk.NdsRequest2.NP;
import ru.nalog.npchk.NdsResponse2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Verifies the legal entities in the database of the Federal Tax Service (Russian Federation)
 * for the taxpayer identification number.
 * This console application uses SOAP and WSDL to interact with the tax service API
 * (https://npchk.nalog.ru/FNSNDSCAWS_2).
 *
 * Проверяет юридические лица в базе ФНС по номеру ИНН.
 * Приложение использует SOAP и WSDL для взаимодействия с API налоговой.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class Client {
    private final Scanner console = new Scanner(System.in);
    private final String[] status = {
            "Налогоплательщик зарегистрирован в ЕГРН и имел статус действующего в указанную дату",
            "Налогоплательщик зарегистрирован в ЕГРН, но не имел статус действующего в указанную дату",
            "Налогоплательщик зарегистрирован в ЕГРН",
            "Налогоплательщик с указанным ИНН зарегистрирован в ЕГРН (КПП не соответствует ИНН или не указан)",
            "Налогоплательщик с указанным ИНН не зарегистрирован в ЕГРН",
            "Некорректный ИНН",
            "Недопустимое количество символов ИНН",
            "Недопустимое количество символов КПП",
            "Недопустимые символы в ИНН",
            "Недопустимые символы в КПП",
            "КПП не должен использоваться при проверке ИП",
            "Некорректный формат даты",
            "некорректная дата (ранее 01.01.1991 или позднее текущей даты)"
    };

    /**
     * Sends a request/question for the user to the console.
     * @param question or request for the user.
     * @return a string with the data entered by the user in the console.
     */
    private String ask(String question) {
        System.out.println(question);
        return console.nextLine();
    }

    /**
     * Verifies user input from the console.
     * It's possible to enter INN or link to a file with a list of INNs.
     * @param inn Taxpayer identification number or link to a file with a list of INNs.
     * @return instance of a request to the server.
     */
    private NdsRequest2 validateInput(String inn) {
        NdsRequest2 request = new NdsRequest2();
        List<NP> npList = request.getNP();
        File file = new File(inn);
        if (file.isFile()) {
            npList.addAll(readINNFromFile(file));
        } else if (checkINNFormat(inn)) {
            npList.add(createNP(inn));
        }
        return request;
    }

    /**
     * Generates a set of INNs received from a file.
     * @param file INNs list file.
     * @return set of INNs.
     */
    private Set<NP> readINNFromFile(File file) {
        Set<NP> result = null;
        try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {
            result = buffer
                    .lines()
                    .map(inn -> {
                                NP np = null;
                                String innLine = inn.trim();
                                if (checkINNFormat(innLine)) {
                                    np = createNP(innLine);
                                }
                                return np;
                            }
                    ).collect(Collectors.toSet());
            result.remove(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Creates a container with INN and current date.
     * @param inn INN.
     * @return NP container.
     */
    private NP createNP(String inn) {
        NP np = new NP();
        np.setINN(inn);
        np.setDT(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        return np;
    }

    /**
     * Checks INN for compliance with the format.
     * @param inn INN.
     * @return true if the INN format is correct.
     */
    private boolean checkINNFormat(String inn) {
        if (inn.matches("([0-9]{1}[1-9]{1}|[1-9]{1}[0-9]{1})([0-9]{8,10})")) {
            return true;
        } else {
            System.out.println(String.format("Неверный формат ИНН или адрес файла: \"%s\"", inn));
            return false;
        }
    }

    /**
     * Sends a request to the tax base.
     * @param request Request to the server.
     * @return server response.
     */
    private NdsResponse2 sendRequest(NdsRequest2 request) {
        System.out.println("Проверка запроса...");
        return new FNSNDSCAWS2().getFNSNDSCAWS2Port().ndsRequest2(request);
    }

    /**
     * Prints the result of the response from the tax base to the console.
     * @param response Server response.
     */
    private void printResultToConsole(NdsResponse2 response) {
        response.getNP().forEach(np -> {
                    String state = np.getState();
                    System.out.println(
                            String.format("ИНН: %s Результат: %s - %s",
                                    np.getINN(),
                                    state,
                                    status[Integer.parseInt(state)]
                            )
                    );
                }
        );
    }

    /**
     * Initializes the main loop for user interaction in the console.
     */
    public void init() {
        boolean exit = false;
        do {
            String input = ask("Введите ИНН или адрес файла со списком ИНН. Для выхода введите q");
            if (!"q".equals(input)) {
                NdsRequest2 request = validateInput(input);
                if (request.getNP().size() != 0) {
                    printResultToConsole(sendRequest(request));
                }
            } else {
                exit = true;
            }
        } while (!exit);
    }

    /**
     * Main method.
     * @param args Arguments are not supposed.
     */
    public static void main(String[] args) {
        System.setProperty("javax.xml.soap.SAAJMetaFactory", "com.sun.xml.messaging.saaj.soap.SAAJMetaFactoryImpl");
        new Client().init();
    }
}