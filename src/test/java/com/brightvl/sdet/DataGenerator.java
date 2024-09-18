package com.brightvl.sdet;

import java.util.Random;

public class DataGenerator {
    /**
     * Генерирует почтовый индекс
     * @return строка с индексом
     */
    public static String generatePostCode() {
        Random random = new Random();
        StringBuilder postCode = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            postCode.append(random.nextInt(10));
        }
        return postCode.toString();
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
