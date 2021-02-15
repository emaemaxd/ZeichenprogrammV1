import javafx.animation.*;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.MotionBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class Controller {

    public Pane drawLine;
    public TextField lineWidth;
    public ColorPicker colorRec;
    public Button drawLineButton;
    public ComboBox comboboxText;
    public Pane drawText;
    public TextField textSize;
    public TextField textArt;
    public Pane drawCircle;
    public ColorPicker colorCircle;
    public TextField circleRadius;
    public TextField textRad;
    public TextField textAng;
    public TextField lineDur;
    public TextField circleAni;
    Text t;
    Circle circle;

    Line line = new Line();
    Group root = new Group();

    private ObservableList<String> textList = FXCollections.observableArrayList("BOLD", "MEDIUM", "LIGHT");

    public void initialize(){
        comboboxText.setItems(textList);
        t = new Text(0, 200,"Text zum verändern");
        drawText.getChildren().add(t);
    }

    public void drawLine(ActionEvent actionEvent) {
        StrokeTransition stroke = new StrokeTransition();

        stroke.setDuration(Duration.millis(Double.parseDouble(lineDur.getText())));
        stroke.setFromValue(Color.color(1,1,1));
        stroke.setToValue(colorRec.getValue());

        line = new Line(100,100, 250, 200);

        line.setStrokeWidth(Double.parseDouble(lineWidth.getText()));
     // line.setStroke(colorRec.getValue());
        drawLine.getChildren().clear();
        drawLine.getChildren().add(line);
        stroke.setShape(line);
        stroke.play();
    }

    public void drawText(ActionEvent actionEvent) {
        MotionBlur motion = new MotionBlur();
        String s = comboboxText.getValue().toString();
        switch (s) {        // ändere werte in combobox => andere FontWeight
            case "BOLD":
                t.setFont(Font.font(textArt.getText(), FontWeight.BOLD , Double.parseDouble(textSize.getText())));
                break;
            case "MEDIUM":
                t.setFont(Font.font(textArt.getText(), FontWeight.MEDIUM , Double.parseDouble(textSize.getText())));
                break;
            case "LIGHT":
                t.setFont(Font.font(textArt.getText(), FontWeight.LIGHT , Double.parseDouble(textSize.getText())));
                break;
        }

        motion.setAngle(Double.parseDouble((textAng.getText())));
        motion.setRadius(Double.parseDouble((textRad.getText())));
        t.setEffect(motion);
        drawText.getChildren().clear();
        drawText.getChildren().add(t);
    }

    public void drawCircle(ActionEvent actionEvent) {
        Timeline timeline = new Timeline();

         circle = new Circle(150, 100, Double.parseDouble(circleRadius.getText()));
         circle.setFill(colorCircle.getValue());
         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.setAutoReverse(true);
         KeyValue kv = new KeyValue(circle.centerXProperty(), 200);
         KeyFrame kf = new KeyFrame(Duration.millis(Double.parseDouble(circleAni.getText())), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
         drawCircle.getChildren().clear();
         drawCircle.getChildren().add(circle);
    }
}
