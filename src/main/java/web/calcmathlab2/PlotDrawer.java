package web.calcmathlab2;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.function.BiFunction;
import java.util.function.DoubleUnaryOperator;

public class PlotDrawer {
    private static final Stage PLOT_STAGE = new Stage();
    private static final NumberAxis X_AXIS = new NumberAxis();
    private static final NumberAxis Y_AXIS = new NumberAxis();
    private static final double STEP = 0.01;
    static {
        PLOT_STAGE.setTitle("График..");
        PLOT_STAGE.setResizable(false);
        PLOT_STAGE.setX(150);
        PLOT_STAGE.setY(400);
        X_AXIS.setLabel("x");
        Y_AXIS.setLabel("y");
    }
    public static void drawEquation(double leftBorder, double rightBorder, DoubleUnaryOperator f) {
        LineChart<Number, Number> lineChart = new LineChart<>(X_AXIS, Y_AXIS);
        lineChart.setCreateSymbols(false);

        XYChart.Series<Number, Number> fSeries = new XYChart.Series<>();
        fSeries.setName("f(x)");

        for (double x = leftBorder; x <= rightBorder; x += STEP) {
            double y = f.applyAsDouble(x);
            fSeries.getData().add(new XYChart.Data<>(x, y));
        }

        lineChart.getData().add(fSeries);

        Scene plotScene = new Scene(lineChart, 480, 240);
        PLOT_STAGE.setScene(plotScene);
        PLOT_STAGE.show();
    }
    public static void drawSystem(double leftBorder, double rightBorder, BiFunction<Double, Double, Double> f, BiFunction<Double, Double, Double> g) {
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(X_AXIS, Y_AXIS);

        XYChart.Series<Number, Number> fSeries = new XYChart.Series<>();
        fSeries.setName("f(x)");
        XYChart.Series<Number, Number> gSeries = new XYChart.Series<>();
        gSeries.setName("g(x)");

        for (double x = leftBorder; x <= rightBorder; x += STEP) {
            for (double y = leftBorder; y <= rightBorder; y += STEP) {
                double fValue = f.apply(x, y);
                if (Math.abs(fValue) < 0.01) {
                    fSeries.getData().add(new XYChart.Data<>(x, y));
                }
                double gValue = g.apply(x, y);
                if (Math.abs(gValue) < 0.01) {
                    gSeries.getData().add(new XYChart.Data<>(x, y));
                }
            }
        }

        scatterChart.getData().add(fSeries);
        scatterChart.getData().add(gSeries);

        for (XYChart.Series<Number, Number> s : scatterChart.getData()) {
            for (XYChart.Data<Number, Number> d : s.getData()) {
                d.getNode().setScaleX(0.15);
                d.getNode().setScaleY(0.15);
            }
        }

        Scene plotScene = new Scene(scatterChart, 480, 240);
        PLOT_STAGE.setScene(plotScene);
        PLOT_STAGE.show();
    }
}
