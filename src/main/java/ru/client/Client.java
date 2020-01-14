package ru.client;

import ru.nalog.npchk.FNSNDSCAWS2;
import ru.nalog.npchk.NdsRequest2;
import ru.nalog.npchk.NdsResponse2;

import java.util.Scanner;

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
    private final Scanner console = new Scanner(System.in );
    private final String[] statuses = {
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
     * @param inn Taxpayer identification number.
     * @return instance of a request to the server.
     */
    private NdsRequest2 validateInput(String inn) {
        NdsRequest2 request;
        request = new NdsRequest2();
        if (inn.matches("([0-9]{1}[1-9]{1}|[1-9]{1}[0-9]{1})([0-9]{8,10})")) {
            NdsRequest2.NP np = new NdsRequest2.NP();
            np.setINN(inn);
            request.getNP().add(np);
        } else {
            System.out.println("Неверный формат ИНН " + inn);
        }
        return request;
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
        response.getNP().forEach(el -> {
                    String state = el.getState();
                    System.out.println(
                            String.format("ИНН: %s Результат: %s - %s",
                                    el.getINN(),
                                    state,
                                    statuses[Integer.parseInt(state)]
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
            String input = ask("Введите ИНН. Для выхода введите q.");
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