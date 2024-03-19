package web.calcmathlab2;

import java.util.HashMap;
import java.util.function.DoubleUnaryOperator;

import static java.lang.Math.*;

public class SingleEquationSolver {
    private static final int MAX_ITERATIONS = 1000;
    static double f1(double x) {
        return 4.45*pow(x, 3) + 7.81*pow(x, 2) - 9.62*x - 8.17;
    }
    static double f1Derivative(double x) {return 13.35*pow(x, 2) + 15.62*x - 9.62;}
    static final double FUNCTION_1_LEFT_ROOT = -2.3433;
    static final double FUNCTION_1_MIDDLE_ROOT = -0.6386;
    static final double FUNCTION_1_RIGHT_ROOT = 1.2268;
    static double f2(double x) {return -2.95*pow(x, 2) - 10.28*x-3.23;}
    static final double FUNCTION_2_LEFT_ROOT = -3.1356;
    static final double FUNCTION_2_RIGHT_ROOT = -0.3492;
    static double f3(double x) {return cos(x) + pow(x, 3) - 0.99;}
    static double f3Derivative(double x) {return -sin(x) + 3*pow(x, 2);}
    static final double FUNCTION_3_LEFT_ROOT = -0.1264;
    static final double FUNCTION_3_MIDDLE_ROOT = 0.176;
    static final double FUNCTION_3_RIGHT_ROOT = 0.4404;
    private static HashMap<String, Number> formSolution(double x, int iterations, DoubleUnaryOperator function) {
        HashMap<String, Number> solution = new HashMap<>();
        solution.put("x", x);
        solution.put("f(x)", function.applyAsDouble(x));
        solution.put("iterations", iterations);
        return solution;
    }
    public static HashMap<String, Number> halfDivisionSolve(double leftBorder, double rightBorder, double epsilon, DoubleUnaryOperator function) throws ConvergenceException {
        double root = (leftBorder + rightBorder) / 2;
        int iterationCount = 0;
        while (!(abs(rightBorder - leftBorder) <= epsilon || abs(function.applyAsDouble(root)) <= epsilon)) {
            if (function.applyAsDouble(root) * function.applyAsDouble(leftBorder) <= 0) {
                rightBorder = root;
            } else if (function.applyAsDouble(root) * function.applyAsDouble(rightBorder) <= 0) {
                leftBorder = root;
            }
            root = (leftBorder + rightBorder) / 2;
            iterationCount++;
            if (iterationCount == MAX_ITERATIONS) {
                throw new ConvergenceException("Уравнение не сходится при заданной точности");
            }
        }
        return formSolution(root, iterationCount, function);
    }
    public static HashMap<String, Number> chordsSolve(double leftBorder, double rightBorder, double epsilon, DoubleUnaryOperator function) throws ConvergenceException {
        double root = leftBorder;
        int iterationCount = 0;
        while (!(abs(rightBorder - leftBorder) <= epsilon || abs(function.applyAsDouble(root)) <= epsilon)) {
            root = leftBorder - function.applyAsDouble(leftBorder) * ((rightBorder - leftBorder) / (function.applyAsDouble(rightBorder) - function.applyAsDouble(leftBorder)));
            if (function.applyAsDouble(root) * function.applyAsDouble(leftBorder) < 0)
                rightBorder = root;
            else
                leftBorder = root;
            iterationCount++;
            if (iterationCount == MAX_ITERATIONS) {
                throw new ConvergenceException("Уравнение не сходится при заданной точности");
            }
        }
        return formSolution(root, iterationCount, function);
    }
    public static HashMap<String, Number> simpleIterationsSolve(double leftBorder, double rightBorder, double epsilon, DoubleUnaryOperator function, DoubleUnaryOperator functionDerivative) throws ConvergenceException {
        double root = (leftBorder + rightBorder) / 2;
        double lambda;
        int iterationCount = 0;
        if (functionDerivative.applyAsDouble(leftBorder) < 0 && functionDerivative.applyAsDouble(rightBorder) < 0) {
            lambda = 1 / abs(functionDerivative.applyAsDouble(-0.585));
        }
        else {
            lambda = 1 / max(abs(functionDerivative.applyAsDouble(leftBorder)), abs(functionDerivative.applyAsDouble(rightBorder)));
        }
        double newRoot = root + lambda * function.applyAsDouble(root);
        while (abs(newRoot - root) > epsilon) {
            root = newRoot;
            newRoot = root + lambda * function.applyAsDouble(root);
            iterationCount++;
            if (iterationCount == MAX_ITERATIONS) {
                throw new ConvergenceException("Уравнение не сходится при заданной точности");
            }
        }
        return formSolution(newRoot, iterationCount, function);
    }
}
