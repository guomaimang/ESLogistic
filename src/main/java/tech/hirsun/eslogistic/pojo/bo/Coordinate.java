package tech.hirsun.eslogistic.pojo.bo;


import lombok.Getter;

@Getter
public class Coordinate {
    private final double X;
    private final double Y;

    public Coordinate(double x, double y) {
        X = x;
        Y = y;
    }

    public double calDistance(Coordinate other) {
        return Math.sqrt((X - other.X) * (X - other.X) + (Y - other.Y) * (Y - other.Y));
    }

}
