package sidea.version.nudge.dto;

public enum PointType {

    충전(1),획득(2),기부(3);

    private final int value;

    private static final PointType[] VALUES;

    static {
        VALUES = values();
    }

    private PointType(int value){
        this.value = value;
    }

    public int value(){
        return this.value;
    }


}
