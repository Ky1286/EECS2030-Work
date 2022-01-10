package javmos.enums;

public enum FunctionType{
    ORIGINAL,
    FIRST_DERIVATIVE,
    SECOND_DERIVATIVE, 
    THIRD_DERIVATIVE;

    private String name;
    private static FunctionType[] value = FunctionType.values();

    public static FunctionType[] functionValue() {
        return value;
    }

    public String valueOf() {
        return name;
    }
}
