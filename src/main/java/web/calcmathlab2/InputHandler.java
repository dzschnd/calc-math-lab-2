package web.calcmathlab2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.DoubleUnaryOperator;

import static web.calcmathlab2.Main.*;
import static web.calcmathlab2.Main.PLOT_SYSTEM_2_BUTTON;
import static web.calcmathlab2.SingleEquationSolver.*;

//TODO: add file input/output
public class InputHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle (ActionEvent event) {
        if (event.getSource().equals(TO_MAIN_BUTTON)) {
            HEADER_MESSAGE.setText("Добро пожаловать.");
            MAIN_BUTTONS.getChildren().clear();
            TO_CHOOSE_EQUATION_BUTTON.setText("Решить одно уравнение");
            TO_CHOOSE_SYSTEM_BUTTON.setText("Решить систему уравнений");
            MAIN_BUTTONS.getChildren().addAll(TO_CHOOSE_EQUATION_BUTTON, TO_CHOOSE_SYSTEM_BUTTON);
            ROOT_LAYOUT.getChildren().clear();
            ROOT_LAYOUT.getChildren().addAll(HEADER_MESSAGE, MAIN_BUTTONS);
        }

        else if (event.getSource().equals(Main.TO_CHOOSE_EQUATION_BUTTON)) {
            HEADER_MESSAGE.setText("Выберите уравнение");
            ROOT_LAYOUT.getChildren().clear();
            ROOT_LAYOUT.getChildren().addAll(HEADER_MESSAGE, CHOOSE_EQUATION_BUTTONS, TO_MAIN_BUTTON);
        }
        else if (event.getSource().equals(Main.TO_CHOOSE_SYSTEM_BUTTON)) {
            HEADER_MESSAGE.setText("Выберите систему уравнений");
            ROOT_LAYOUT.getChildren().clear();
            ROOT_LAYOUT.getChildren().addAll(HEADER_MESSAGE, CHOOSE_SYSTEM_BUTTONS, TO_MAIN_BUTTON);
        }

        else if (event.getSource().equals(CHOOSE_EQUATION_1_BUTTON)) {
            handleChooseEquationButton(SOLVE_EQUATION_1_BUTTON, SOLVE_EQUATION_1_CHOOSE_FILE_BUTTON, PLOT_EQUATION_1_BUTTON);
        }
        else if (event.getSource().equals(CHOOSE_EQUATION_2_BUTTON)) {
            handleChooseEquationButton(SOLVE_EQUATION_2_BUTTON, SOLVE_EQUATION_2_CHOOSE_FILE_BUTTON, PLOT_EQUATION_2_BUTTON);
        }
        else if (event.getSource().equals(CHOOSE_EQUATION_3_BUTTON)) {
            handleChooseEquationButton(SOLVE_EQUATION_3_BUTTON, SOLVE_EQUATION_3_CHOOSE_FILE_BUTTON, PLOT_EQUATION_3_BUTTON);
        }

        else if (event.getSource().equals(CHOOSE_SYSTEM_1_BUTTON)) {
            handleChooseSystemButton(SOLVE_SYSTEM_1_BUTTON, SOLVE_SYSTEM_1_CHOOSE_FILE_BUTTON, PLOT_SYSTEM_1_BUTTON);
        }
        else if (event.getSource().equals(CHOOSE_SYSTEM_2_BUTTON)) {
            handleChooseSystemButton(SOLVE_SYSTEM_2_BUTTON, SOLVE_SYSTEM_2_CHOOSE_FILE_BUTTON, PLOT_SYSTEM_2_BUTTON);
        }

        else if (event.getSource().equals(PLOT_EQUATION_1_BUTTON)) {
            handlePlotEquationButton(SingleEquationSolver::f1);
        }
        else if (event.getSource().equals(PLOT_EQUATION_2_BUTTON)) {
            handlePlotEquationButton(SingleEquationSolver::f2);
        }
        else if (event.getSource().equals(PLOT_EQUATION_3_BUTTON)) {
            handlePlotEquationButton(SingleEquationSolver::f3);
        }
        else if (event.getSource().equals(PLOT_SYSTEM_1_BUTTON)) {
            handlePlotSystemButton(EquationSystemSolver::f1, EquationSystemSolver::g);
        }
        else if (event.getSource().equals(PLOT_SYSTEM_2_BUTTON)) {
            handlePlotSystemButton(EquationSystemSolver::f2, EquationSystemSolver::g);
        }

        else if (event.getSource().equals(SOLVE_EQUATION_1_CHOOSE_FILE_BUTTON)) {
            handleSolveEquationChooseFileButton(SingleEquationSolver::f1, SingleEquationSolver::f1Derivative,
                    FUNCTION_1_LEFT_ROOT, FUNCTION_1_MIDDLE_ROOT, FUNCTION_1_RIGHT_ROOT);
        }
        else if (event.getSource().equals(SOLVE_EQUATION_2_CHOOSE_FILE_BUTTON)) {
            handleSolveEquationChooseFileButton(SingleEquationSolver::f2, FUNCTION_2_LEFT_ROOT, FUNCTION_2_RIGHT_ROOT);
        }
        else if (event.getSource().equals(SOLVE_EQUATION_3_CHOOSE_FILE_BUTTON)) {
            handleSolveEquationChooseFileButton(SingleEquationSolver::f3, SingleEquationSolver::f3Derivative,
                    FUNCTION_3_LEFT_ROOT, FUNCTION_3_MIDDLE_ROOT, FUNCTION_3_RIGHT_ROOT);
        }
        else if (event.getSource().equals(SOLVE_SYSTEM_1_CHOOSE_FILE_BUTTON)) {
            handleSolveSystemChooseFileButton(EquationSystemSolver::f1, EquationSystemSolver::df1dx, EquationSystemSolver::df1dy, EquationSystemSolver::g, EquationSystemSolver::dgdx, EquationSystemSolver::dgdy);
        }
        else if (event.getSource().equals(SOLVE_SYSTEM_2_CHOOSE_FILE_BUTTON)) {
            handleSolveSystemChooseFileButton(EquationSystemSolver::f2, EquationSystemSolver::df2dx, EquationSystemSolver::df2dy, EquationSystemSolver::g, EquationSystemSolver::dgdx, EquationSystemSolver::dgdy);
        }

        else if (event.getSource().equals(SOLVE_EQUATION_1_BUTTON) || event.getSource().equals(SOLVE_EQUATION_2_BUTTON) || event.getSource().equals(SOLVE_EQUATION_3_BUTTON)) {
            try {
                double leftBorder = Double.parseDouble(LEFT_BORDER_INPUT.getText());
                double rightBorder = Double.parseDouble(RIGHT_BORDER_INPUT.getText());
                double epsilon = Double.parseDouble(ACCURACY_INPUT.getText());
                if (rightBorder <= leftBorder) {
                    RESPONSE_MESSAGE.setText("Правая граница должна быть больше левой");
                    return;
                }
                if (epsilon <= 0) {
                    RESPONSE_MESSAGE.setText("Точность должна быть положительным числом");
                    return;
                }
                if (event.getSource().equals(SOLVE_EQUATION_1_BUTTON)) {
                    handleSolveEquationButton(leftBorder, rightBorder, epsilon, SingleEquationSolver::f1, SingleEquationSolver::f1Derivative,
                                                                            FUNCTION_1_LEFT_ROOT, FUNCTION_1_MIDDLE_ROOT, FUNCTION_1_RIGHT_ROOT);
                }
                else if (event.getSource().equals(SOLVE_EQUATION_2_BUTTON)) {
                    handleSolveEquationButton(leftBorder, rightBorder, epsilon, SingleEquationSolver::f2,
                                                                            FUNCTION_2_LEFT_ROOT, FUNCTION_2_RIGHT_ROOT);
                }
                else if (event.getSource().equals(SOLVE_EQUATION_3_BUTTON)) {
                    handleSolveEquationButton(leftBorder, rightBorder, epsilon, SingleEquationSolver::f3, SingleEquationSolver::f3Derivative,
                                                                            FUNCTION_3_LEFT_ROOT, FUNCTION_3_MIDDLE_ROOT, FUNCTION_3_RIGHT_ROOT);
                }
            } catch (NumberFormatException e) {
                RESPONSE_MESSAGE.setText("Численные значения введены некорректно");
            }
        }

         else if (event.getSource().equals(SOLVE_SYSTEM_1_BUTTON) || event.getSource().equals(SOLVE_SYSTEM_2_BUTTON)) {
            try {
                double xInit = Double.parseDouble(X_INITIAL_INPUT.getText());
                double yInit = Double.parseDouble(Y_INITIAL_INPUT.getText());
                if (event.getSource().equals(SOLVE_SYSTEM_1_BUTTON)) {
                    HashMap<String, Number> solution = EquationSystemSolver.newtonSolve(xInit, yInit, EquationSystemSolver::f1, EquationSystemSolver::df1dx, EquationSystemSolver::df1dy, EquationSystemSolver::g, EquationSystemSolver::dgdx, EquationSystemSolver::dgdy);
                    RESPONSE_MESSAGE.setText(getSystemSolution(solution));
                }
                else if (event.getSource().equals(SOLVE_SYSTEM_2_BUTTON)) {
                    HashMap<String, Number> solution = EquationSystemSolver.newtonSolve(xInit, yInit, EquationSystemSolver::f2, EquationSystemSolver::df2dx, EquationSystemSolver::df2dy, EquationSystemSolver::g, EquationSystemSolver::dgdx, EquationSystemSolver::dgdy);
                    RESPONSE_MESSAGE.setText(getSystemSolution(solution));
                }
            } catch (NumberFormatException e) {
                RESPONSE_MESSAGE.setText("Численные значения введены некорректно");
            } catch (ConvergenceException e) {
                RESPONSE_MESSAGE.setText(e.getMessage());
            }
        }
    }

    private String getEquationSolution(HashMap<String, Number> solution) {
        return "x: " + solution.get("x") +
                "\nf(x): " + solution.get("f(x)") +
                "\nРешено за " + solution.get("iterations") + " итераций\n";
    }
    private String getSystemSolution(HashMap<String, Number> solution) {
        return "x: " + solution.get("x") +
                "\ny: " + solution.get("y") +
                "\ndx: " + solution.get("dx") +
                "\ndy: " + solution.get("dy") +
                "\nРешено за " + solution.get("iterations") + " итераций\n";
    }
    private void handleChooseEquationButton(Button solveEquationButton, Button solveEquationChooseFileButton, Button plotEquationButton) {
        RESPONSE_MESSAGE.setText("");
        LEFT_BORDER_INPUT.setText("");
        RIGHT_BORDER_INPUT.setText("");
        ACCURACY_INPUT.setText("");
        LEFT_BORDER_INPUT.setPromptText("Левая граница интервала");
        RIGHT_BORDER_INPUT.setPromptText("Правая граница интервала");
        ACCURACY_INPUT.setPromptText("Точность приближения");
        HEADER_MESSAGE.setText("Введите интервал");
        TO_CHOOSE_EQUATION_BUTTON.setText("Выбрать другое уравнение");
        ROOT_LAYOUT.getChildren().removeAll(CHOOSE_EQUATION_BUTTONS, TO_MAIN_BUTTON);
        ROOT_LAYOUT.getChildren().addAll(LEFT_BORDER_INPUT, RIGHT_BORDER_INPUT, ACCURACY_INPUT,
                RESPONSE_MESSAGE, solveEquationButton, solveEquationChooseFileButton, plotEquationButton, TO_CHOOSE_EQUATION_BUTTON, TO_MAIN_BUTTON);
    }
    private void handleChooseSystemButton(Button solveSystemButton, Button solveSystemChooseFileButton, Button plotSystemButton) {
        RESPONSE_MESSAGE.setText("");
        X_INITIAL_INPUT.setText("");
        Y_INITIAL_INPUT.setText("");
        X_INITIAL_INPUT.setPromptText("Начальное приближение х");
        Y_INITIAL_INPUT.setPromptText("Начальное приближение у");
        HEADER_MESSAGE.setText("Введите начальные приближения");
        TO_CHOOSE_SYSTEM_BUTTON.setText("Выбрать другую систему");
        ROOT_LAYOUT.getChildren().removeAll(CHOOSE_SYSTEM_BUTTONS, TO_MAIN_BUTTON);
        ROOT_LAYOUT.getChildren().addAll(X_INITIAL_INPUT, Y_INITIAL_INPUT,
                RESPONSE_MESSAGE, solveSystemButton, solveSystemChooseFileButton, plotSystemButton, TO_CHOOSE_SYSTEM_BUTTON, TO_MAIN_BUTTON);
    }
    private void handlePlotEquationButton(DoubleUnaryOperator f) {
        final double DEFAULT_EQUATION_BORDER = 3;
        try {
            double leftBorder = Double.parseDouble(LEFT_BORDER_INPUT.getText());
            double rightBorder = Double.parseDouble(RIGHT_BORDER_INPUT.getText());
            if (rightBorder <= leftBorder) {
                RESPONSE_MESSAGE.setText("Правая граница должна быть больше левой");
                PlotDrawer.drawEquation(-DEFAULT_EQUATION_BORDER, DEFAULT_EQUATION_BORDER, f);
                return;
            }
            PlotDrawer.drawEquation(leftBorder-0.5, rightBorder+0.5, f);
        } catch (NumberFormatException e) {
            PlotDrawer.drawEquation(-DEFAULT_EQUATION_BORDER, DEFAULT_EQUATION_BORDER, f);
        }
    }
    private void handlePlotSystemButton(BiFunction<Double, Double, Double> f, BiFunction<Double, Double, Double> g) {
        final double DEFAULT_SYSTEM_BORDER = 1;
        PlotDrawer.drawSystem(-DEFAULT_SYSTEM_BORDER, DEFAULT_SYSTEM_BORDER, f, g);
    }
    private void handleSolveEquationButton(double leftBorder, double rightBorder, double epsilon, DoubleUnaryOperator f,  DoubleUnaryOperator fDerivative, double leftRoot, double middleRoot, double rightRoot) {
        try {
            if (leftBorder < leftRoot && leftRoot < rightBorder && rightBorder <= middleRoot) {
                HashMap<String, Number> solution = SingleEquationSolver.chordsSolve(leftBorder, rightBorder, epsilon, f);
                RESPONSE_MESSAGE.setText(getEquationSolution(solution));
            } else if (leftRoot <= leftBorder && leftBorder < middleRoot && middleRoot < rightBorder && rightBorder <= rightRoot) {
                HashMap<String, Number> solution = SingleEquationSolver.simpleIterationsSolve(leftBorder, rightBorder, epsilon, f, fDerivative);
                RESPONSE_MESSAGE.setText(getEquationSolution(solution));
            } else if (middleRoot <= leftBorder && leftBorder < rightRoot && rightRoot < rightBorder) {
                HashMap<String, Number> solution = SingleEquationSolver.halfDivisionSolve(leftBorder, rightBorder, epsilon, f);
                RESPONSE_MESSAGE.setText(getEquationSolution(solution));
            } else if ((leftBorder < leftRoot && middleRoot < rightBorder) || (leftBorder < middleRoot && rightRoot < rightBorder)) {
                RESPONSE_MESSAGE.setText("Интервал содержит несколько корней");
            } else {
                RESPONSE_MESSAGE.setText("Интервал не содержит корней");
            }
        } catch (ConvergenceException e) {
            RESPONSE_MESSAGE.setText(e.getMessage());
        }
    }
    private void handleSolveEquationButton(double leftBorder, double rightBorder, double epsilon, DoubleUnaryOperator f, double leftRoot, double rightRoot) {
        try {
            if (leftBorder < leftRoot && leftRoot < rightBorder && rightBorder <= rightRoot) {
                HashMap<String, Number> solution = SingleEquationSolver.chordsSolve(leftBorder, rightBorder, epsilon, f);
                RESPONSE_MESSAGE.setText(getEquationSolution(solution));
            } else if (leftRoot <= leftBorder && leftBorder < rightRoot && rightRoot < rightBorder) {
                HashMap<String, Number> solution = SingleEquationSolver.halfDivisionSolve(leftBorder, rightBorder, epsilon, f);
                RESPONSE_MESSAGE.setText(getEquationSolution(solution));
            } else if (leftBorder < leftRoot && rightRoot < rightBorder) {
                RESPONSE_MESSAGE.setText("Интервал содержит несколько корней");
            } else {
                RESPONSE_MESSAGE.setText("Интервал не содержит корней");
            }
        } catch (ConvergenceException e) {
            RESPONSE_MESSAGE.setText(e.getMessage());
        }
    }
    private void handleSolveEquationChooseFileButton(DoubleUnaryOperator f,  DoubleUnaryOperator fDerivative, double leftRoot, double middleRoot, double rightRoot) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 3) {
                        double leftBorder = Double.parseDouble(values[0]);
                        double rightBorder = Double.parseDouble(values[1]);
                        double epsilon = Double.parseDouble(values[2]);
                        if (rightBorder <= leftBorder) {
                            RESPONSE_MESSAGE.setText("Правая граница должна быть больше левой");
                            return;
                        }
                        if (epsilon <= 0) {
                            RESPONSE_MESSAGE.setText("Точность должна быть положительным числом");
                            return;
                        }
                        handleSolveEquationButton(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2]), f, fDerivative, leftRoot, middleRoot, rightRoot);
                    }
                    else {
                        RESPONSE_MESSAGE.setText("Неверный формат файла. \nИспользуйте формат: \n<Левая граница, Правая граница, Точность>");
                    }
                }
                reader.close();
            } catch (NumberFormatException e) {
                RESPONSE_MESSAGE.setText("Численные значения введены некорректно");
            }
            catch (FileNotFoundException e) {
                RESPONSE_MESSAGE.setText("Файл не найден");
            } catch (IOException e) {
                RESPONSE_MESSAGE.setText("Не удалось прочитать файл");
            }
        }
    }
    private void handleSolveEquationChooseFileButton(DoubleUnaryOperator f, double leftRoot, double rightRoot) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 3) {
                        double leftBorder = Double.parseDouble(values[0]);
                        double rightBorder = Double.parseDouble(values[1]);
                        double epsilon = Double.parseDouble(values[2]);
                        if (rightBorder <= leftBorder) {
                            RESPONSE_MESSAGE.setText("Правая граница должна быть больше левой");
                            return;
                        }
                        if (epsilon <= 0) {
                            RESPONSE_MESSAGE.setText("Точность должна быть положительным числом");
                            return;
                        }
                        handleSolveEquationButton(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2]), f, leftRoot, rightRoot);
                    }
                    else {
                        RESPONSE_MESSAGE.setText("Неверный формат файла. \nИспользуйте формат: \n<Левая граница, Правая граница, Точность>");
                    }
                }
                reader.close();
            } catch (NumberFormatException e) {
                RESPONSE_MESSAGE.setText("Численные значения введены некорректно");
            }
            catch (FileNotFoundException e) {
                RESPONSE_MESSAGE.setText("Файл не найден");
            } catch (IOException e) {
                RESPONSE_MESSAGE.setText("Не удалось прочитать файл");
            }
        }
    }
    private void handleSolveSystemChooseFileButton(BiFunction<Double, Double, Double> f, BiFunction<Double, Double, Double> dfdx, BiFunction<Double, Double, Double> dfdy, BiFunction<Double, Double, Double> g, BiFunction<Double, Double, Double> dgdx, BiFunction<Double, Double, Double> dgdy) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 2) {
                        double xInit = Double.parseDouble(values[0]);
                        double yInit = Double.parseDouble(values[1]);
                        HashMap<String, Number> solution = EquationSystemSolver.newtonSolve(xInit, yInit, f, dfdx, dfdy, g, dgdx, dgdy);
                        RESPONSE_MESSAGE.setText(getSystemSolution(solution));
                       }
                    else {
                        RESPONSE_MESSAGE.setText("Неверный формат файла. \nИспользуйте формат: \n<Начальное приближение х, Начальное приближение у>");
                    }
                }
                reader.close();
            } catch (ConvergenceException e) {
                RESPONSE_MESSAGE.setText(e.getMessage());
            } catch (NumberFormatException e) {
                RESPONSE_MESSAGE.setText("Численные значения введены некорректно");
            }
            catch (FileNotFoundException e) {
                RESPONSE_MESSAGE.setText("Файл не найден");
            } catch (IOException e) {
                RESPONSE_MESSAGE.setText("Не удалось прочитать файл");
            }
        }
    }
}

