package calculation;

import enums.OperationEnum;

import java.util.HashSet;
import java.util.Set;

public class Calculator {

    Set<String> getTaskSet(OperationEnum operation, int min, int max, int count) {
        Set<String> tasks = new HashSet<>();
        while (tasks.size() < count) {
            addTaskToSet(tasks, operation, min, max);
        }
        return tasks;
    }

    private void addTaskToSet(Set<String> tasks, OperationEnum operation, int min, int max) {
        int first = getRandomIntBetweenRange(min, max);
        int second = getRandomIntBetweenRange(min, max);

        int result = calculate(operation, first, second);
        if (result >= min && result <= max) {
            tasks.add(generateTask(operation, first, second));
        }
    }

    private int getRandomIntBetweenRange(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    private int calculate(OperationEnum operation, int first, int second) {
        switch (operation) {
            case SUBTRACTION:
                return first - second;
            case ADDITION:
            default:
                return first + second;
        }
    }

    private String generateTask(OperationEnum operation, int first, int second) {
        switch (operation) {
            case SUBTRACTION:
                return String.format("%s - %s =", first, second);
            case ADDITION:
            default:
                return String.format("%s + %s =", first, second);
        }

    }
}
