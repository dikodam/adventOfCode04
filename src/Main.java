import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 07.12.2016.
 */
public class Main extends Application {

    private Stage window;
    private TextField tfOutput;
    private List<String> lines;
    private List<Room> inputRooms;
    private int idSum = 0;

    public static void main(String[] args) {
        launch(args);
    }

    // GUI setup
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);


        tfOutput = new TextField();
        Button btnGo = new Button("GO");
        btnGo.setOnAction(e -> processInputFile());

        layout.getChildren().addAll(btnGo, tfOutput);

        StackPane root = new StackPane();
        root.getChildren().add(layout);
        window.setScene(new Scene(root, 300, 300));
        window.show();
    }

    // business code
    private void processInputFile() {
        readLines();
        parseLines();
        compareRooms();
        tfOutput.setText("sum of IDs of legit rooms: " + idSum);
    }

    private void readLines() {
        lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")))) {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputRooms = new ArrayList<>(lines.size());
    }

    private void parseLines() {
        for (String line : lines) {
            String[] lineWithoutId = line.split("\\d{3}");
            String nameWithHymens = lineWithoutId[0];
            String inputCheckSumWithBrackets = lineWithoutId[1];
            String idString = line.replace(nameWithHymens, "").replace(inputCheckSumWithBrackets, "");
            int id = Integer.parseInt(idString);
            String name = nameWithHymens.replace("-", "");
            String inputChecksum = inputCheckSumWithBrackets.replace("[", "").replace("]", "");
            inputRooms.add(new Room(name, id, inputChecksum));
        }
    }

    private void compareRooms() {
        for (Room room : inputRooms) {
            if (room.equals(new Room(room.getName(), room.getId()))) {
                idSum += room.getId();
            }
        }
    }
}
