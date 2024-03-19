package web.calcmathlab2;

import java.util.HashMap;
import java.util.function.BiFunction;

import static java.lang.Math.*;

public class EquationSystemSolver {
    private static final double EPSILON = 0.01;
    private static final double MAX_ITERATIONS = 1000;
    static double f1(double x, double y) {
        return tan(x*y + 0.2) - pow(x, 2);
    }
    static double df1dx(double x, double y) {
        return y / pow(cos(x*y + 0.2), 2) - 2*x;
    }
    static double df1dy(double x, double y) {
        return x / pow(cos(x*y + 0.2), 2);
    }
    static double f2(double x, double y) {return sin(x+y) - 1.2*x - 0.2;}
    static double df2dx(double x, double y) {return cos(x+y) - 1.2;}
    static double df2dy(double x, double y) {return cos(x+y);}
    static double g(double x, double y) {return pow(x, 2) + 2*pow(y, 2) - 1;}
    static double dgdx(double x, double y) {return 2*x;}
    static double dgdy(double x, double y) {return 4*y;}
    public static HashMap<String, Number> newtonSolve(double x, double y, BiFunction<Double, Double, Double> f, BiFunction<Double, Double, Double> dfdx, BiFunction<Double, Double, Double> dfdy, BiFunction<Double, Double, Double> g, BiFunction<Double, Double, Double> dgdx, BiFunction<Double, Double, Double> dgdy) throws ConvergenceException {
        double dX;
        double dY;
        double determinant;
        int iterationCount = 0;
        do {
            determinant = dfdx.apply(x, y) * dgdy.apply(x, y) - dfdy.apply(x, y) * dgdx.apply(x, y);
            dX = (-f.apply(x, y) * dgdy.apply(x, y) + g.apply(x, y) * dgdx.apply(x, y)) / determinant;
            dY = (dfdx.apply(x, y) * (-g.apply(x, y)) + f.apply(x, y) * dgdx.apply(x, y)) / determinant;
            x += dX;
            y += dY;
            iterationCount++;
            if (iterationCount == MAX_ITERATIONS) {
                throw new ConvergenceException("Система не сходится при заданном приближении");
            }
        } while (!(abs(dX) <= EPSILON || abs(dY) <= EPSILON));

        HashMap<String, Number> solution = new HashMap<>();
        solution.put("x", x);
        solution.put("y", y);
        solution.put("dx", dX);
        solution.put("dy", dY);
        solution.put("iterations", iterationCount);
        return solution;
    }
}
