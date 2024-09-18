package com.brightvl.sdet;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {
    private static final Random random = new Random();
    /**
     * Генерирует почтовый индекс
     * @return строка с индексом
     */
    public static String generatePostCode() {
        return IntStream.range(0, 10)
                .mapToObj(i -> String.valueOf(random.nextInt(10)))
                .collect(Collectors.joining());
    }

    /**
     * Создает имя используя индекс
     * @param postCode индекс
     * @return строка с именем
     */
    public static String generateNameFromPostCode(String postCode) {
        StringBuilder firstName = new StringBuilder();
        for (int i = 0; i < postCode.length(); i += 2) {
            String numberStr = postCode.substring(i, i + 2);
            int number = Integer.parseInt(numberStr);
            char letter = (char) ('a' + (number % 26));
            firstName.append(letter);
        }
        return firstName.toString();
    }
}
