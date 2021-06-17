
import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HistogramMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Histogram");
        Group r = new Group();
        Pane root = new Pane();
        Canvas canvas = new Canvas(800, 400);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

        double v[] = {Math.random() * 10 + 1, 6.5, 3.2, 2.9, 8, 9, 1, 5};

        Histogram h = new Histogram(canvas.getGraphicsContext2D(), canvas.getWidth(), canvas.getHeight(), v);

        h.draw();
        
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                canvas.setWidth(new_val.intValue());
                Histogram h = new Histogram(canvas.getGraphicsContext2D(), canvas.getWidth(), canvas.getHeight(), v);
                h.draw();
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                canvas.setHeight(new_val.intValue());
                Histogram h = new Histogram(canvas.getGraphicsContext2D(), canvas.getWidth(), canvas.getHeight(), v);
                h.draw();
            }
        });
        
        
    }

    public class Histogram {

        final GraphicsContext gc;
        private final double w, h, rh, cw;

        public double getCw() {
            return cw;
        }
        double r, g, b;
        private double values[];
        Random rand = new Random();

        public Histogram(GraphicsContext gc, double w, double h, double v[]) {
            this.gc = gc;
            this.w = w;
            this.h = h;
            this.rh = h / 10;
            this.cw = w / (v.length);
            this.values = v;
        }

        public void draw() {

            for (int i = 0, j = -1; i < 10; i++, j = -j) {

                if (j == 1) {
                    gc.setFill(Color.WHITE);
                } else {
                    gc.setFill(Color.GAINSBORO);
                }

                gc.fillRect(0, i * rh, w, rh);

            }
            for (int i = 0; i < values.length; i++) {

                r = rand.nextFloat();
                g = rand.nextFloat();
                b = rand.nextFloat();
                gc.setFill(Color.color(r, g, b));

                gc.fillRect(i * cw, Math.abs(values[i] - 10) * rh, cw, values[i] * rh);

            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
