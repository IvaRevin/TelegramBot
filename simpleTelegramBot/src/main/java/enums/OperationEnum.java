package enums;

import java.util.Arrays;
import java.util.List;

public enum OperationEnum {
    ADDITION,//сложение
    SUBTRACTION;//вычитание

    public static List<OperationEnum> getPlusMinus() {
        return Arrays.asList(values());
    }
}
