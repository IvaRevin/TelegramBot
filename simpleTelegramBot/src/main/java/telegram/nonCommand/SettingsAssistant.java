package telegram.nonCommand;

import exception.IllegalSettingsException;

public class SettingsAssistant {
    static int calculateMax(int min, int max) {
        if (max > min) {
            return max;
        }
        return min;
    }

    static int calculateMin(int min, int max) {
        if (min < max) {
            return min;
        }
        return max;
    }

    static int calculateListCount(int listCount) {
        if (listCount > 10) {
            return  10;
        } else {
            return listCount;
        }
    }

    static int calculateUniqueTaskCount(int min, int max) {
        int uniqueTaskCount = 0;
        if (max - 2 * min + 1 >= 0) {
            uniqueTaskCount = ((max - 2 * min + 2) * (max - 2 * min + 1 ))/2;
        }
        if (uniqueTaskCount < 0) {
            throw new IllegalArgumentException(String.format("Заданные значения %s - %s слишком велики. " +
                    "Число уникальных задач не влезает в Integer", min, max));
        }
        if (uniqueTaskCount == 0) {
            throw new IllegalSettingsException(String.format("\uD83D\uDCA9 Для пары чисел %s - %s не существует" +
                    " сложений и вычитаний, результат которых попадает в интервал между ними", min, max));
        }
        return uniqueTaskCount;
    }
}
