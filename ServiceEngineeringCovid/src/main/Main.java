package main;

import csv.CSVReader;
import data.Chart;
import data.DataBean;
import data.District;
import data.FederalState;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;


public class Main extends Application {
    private DataBean dataBean;
    private Chart chart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Covid-Analyse");
        CSVReader csvReader = new CSVReader();
        dataBean = csvReader.setUp();
        chart = new Chart(dataBean);
        BorderPane root = createPaneWithMenu(primaryStage);
        

        TableView<FederalState> tableView = new TableView();

        TableColumn<FederalState, String> tableColumnOne = new TableColumn<>("Bundesland");
        tableColumnOne.setCellValueFactory(new PropertyValueFactory<>("stateName"));

        TableColumn<FederalState, Integer> tableColumnTwo = new TableColumn<>("BundeslandID");
        tableColumnTwo.setCellValueFactory(new PropertyValueFactory<>("stateID"));

        tableView.getColumns().add(tableColumnOne);
        tableView.getColumns().add(tableColumnTwo);

        tableView.setRowFactory(tv -> {
            TableRow<FederalState> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    federalStateList(primaryStage, row.getItem());
                }
            });
            return row;
        });

        for (FederalState federalState : dataBean.getRepublic().getStates()) {
            tableView.getItems().add(federalState);
        }

        root.setCenter(tableView);
        Scene scene = new Scene(root, 1300, 700);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public BorderPane createPaneWithMenu(Stage stage) {
        BorderPane root = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu jMenu = new Menu("Ansicht");
        Menu jMenu2 = new Menu("Tool");
        Menu jMenu3 = new Menu("Chartstyle");


        MenuItem district = new MenuItem("Bezirke");
        MenuItem fs = new MenuItem("Bundesländer");
        MenuItem back = new MenuItem("Start");
        //MenuItem line = new MenuItem("Liniendiagramm");
        MenuItem pie = new MenuItem("Kreisdiagramm");

        //pie.setOnAction(actionEvent -> pieChart(stage, dataBean.getRepublic().getStates().get(3)));

        district.setOnAction(actionEvent -> {
            try {
                districtListView(stage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        fs.setOnAction(actionEvent -> {
            try {
                start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        back.setOnAction(actionEvent -> {
            try {
                start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        jMenu.getItems().addAll(district, fs);
        jMenu2.getItems().add(back);
       /* jMenu3.getItems().addAll(line, pie);*/
        menuBar.getMenus().addAll(jMenu, jMenu2, jMenu3);
        root.setTop(menuBar);
        return root;
    }

    private FederalState federalStateList(Stage stage, FederalState federalState) {
        TableView<FederalState.FederalStateNode> tableView = new TableView();

        TableColumn<FederalState.FederalStateNode, String> tableColumnOne = new TableColumn<>("Bundesland");
        tableColumnOne.setCellValueFactory(new PropertyValueFactory<>("stateName"));

        TableColumn<FederalState.FederalStateNode, Integer> tableColumnTwo = new TableColumn<>("Datum");
        tableColumnTwo.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<FederalState.FederalStateNode, Integer> tableColumnThree = new TableColumn<>("Tests gesamt");
        tableColumnThree.setCellValueFactory(new PropertyValueFactory<>("testSum"));

        TableColumn<FederalState.FederalStateNode, Integer> tableColumnFour = new TableColumn<>("#Krankenhaus");
        tableColumnFour.setCellValueFactory(new PropertyValueFactory<>("nrHospital"));

        TableColumn<FederalState.FederalStateNode, Integer> tableColumnFive = new TableColumn<>("#Intensiv");
        tableColumnFive.setCellValueFactory(new PropertyValueFactory<>("nrICU"));

        tableView.getColumns().add(tableColumnOne);
        tableView.getColumns().add(tableColumnTwo);
        tableView.getColumns().add(tableColumnThree);
        tableView.getColumns().add(tableColumnFour);
        tableView.getColumns().add(tableColumnFive);

        tableView.setRowFactory(tv -> {
            TableRow<FederalState.FederalStateNode> row = new TableRow<>();
            stage.setTitle("Covid-Analyse-State");
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    stage.setTitle("Covid-Analyse-State-Detail");
                    chart.showFederalStateStatistics(stage, federalState);
                    stage.setTitle("Covid-Analyse-State-Detail" + " for " + federalState.getStateName());
                }
            });
            return row;
        });

        for (FederalState.FederalStateNode federalStateNode : federalState.getFederalStateNodeList()) {
            tableView.getItems().add(federalStateNode);
        }
        BorderPane root = createPaneWithMenu(stage);
        root.setCenter(tableView);
        Scene scene = new Scene(root, 1200, 600);
        stage.setScene(scene);
        stage.show();

        return federalState;
    }

    private void districtListView(Stage stage) throws IOException, ParseException {
        TableView<District> tableView = new TableView();

        TableColumn<District, String> tableColumnOne = new TableColumn<>("Bezirk");
        tableColumnOne.setCellValueFactory(new PropertyValueFactory<>("districtName"));

        TableColumn<District, Integer> tableColumnTwo = new TableColumn<>("Gemeindekennzahl");
        tableColumnTwo.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        TableColumn<District, Integer> tableColumnThree = new TableColumn<>("Einwohner");
        tableColumnThree.setCellValueFactory(new PropertyValueFactory<>("inhabitants"));

        TableColumn<District, Integer> tableColumnFour = new TableColumn<>("#Infiziert");
        tableColumnFour.setCellValueFactory(new PropertyValueFactory<>("sumInfected"));

        TableColumn<District, Integer> tableColumnFive = new TableColumn<>("#Tote");
        tableColumnFive.setCellValueFactory(new PropertyValueFactory<>("sumDeath"));

        TableColumn<District, Integer> tableColumnSix = new TableColumn<>("Fälle 7Tage");
        tableColumnSix.setCellValueFactory(new PropertyValueFactory<>("casesLastSevenDays"));
        if(dataBean == null) {
            CSVReader csvReader = new CSVReader();
            dataBean = csvReader.setUp();
            chart = new Chart(dataBean);
        }
        tableView.getColumns().add(tableColumnOne);
        tableView.getColumns().add(tableColumnTwo);
        tableView.getColumns().add(tableColumnThree);
        tableView.getColumns().add(tableColumnFour);
        tableView.getColumns().add(tableColumnFive);
        tableView.getColumns().add(tableColumnSix);

        tableView.setRowFactory(tv -> {
            TableRow<District> row = new TableRow<>();
            stage.setTitle("Covid-Analyse-Districts");
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    stage.setTitle("Covid-Analyse-Districts-Detail");
                    chart.detailView(stage, row.getItem());
                    stage.setTitle("Covid-Analyse-Districts-Detail" + " for " + row.getItem().getDistrictName());
                }
            });
            return row;
        });
        for (FederalState federalState : dataBean.getRepublic().getStates()) {
            for (District district : federalState.getDistricts()) {
                tableView.getItems().add(district);
            }
        }

        tableColumnSix.setCellFactory(e -> new TableCell<District, Integer>() {
            @Override
            public void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.toString());
                setGraphic(null);
                if (!isEmpty()) {
                    if (item >= 200) {
                        this.setStyle("-fx-background-color: red;");
                    } else if (100 < item && item < 200) {
                        this.setStyle("-fx-background-color: orange;");
                    } else if (50 < item && item <= 100) {
                        this.setStyle("-fx-background-color: yellow;");
                        this.setTextFill(Color.BLACK);
                    } else {
                        this.setStyle("-fx-background-color: green;");
                    }
                }

            }
        });

        BorderPane root = createPaneWithMenu(stage);
        root.setCenter(tableView);
        Scene scene = new Scene(root, 1200, 600);
        stage.setScene(scene);
        stage.show();
    }


   /* private void pieChart(Stage stage, FederalState federalState) {

        BorderPane root = createPaneWithMenu(stage);

        PieChart pieChart = new PieChart();
        PieChart.Data slice1 = new PieChart.Data("Krankenhaus gesamt", federalState.getFederalStateNodeList().get(federalState.getFederalStateNodeList().size() - 1).getNrHospital()
                + federalState.getFederalStateNodeList().get(federalState.getFederalStateNodeList().size() - 1).getNrICU());
        PieChart.Data slice2 = new PieChart.Data("ICU", federalState.getFederalStateNodeList().get(federalState.getFederalStateNodeList().size()-1).getNrICU());

        pieChart.getData().addAll(slice1, slice2);
        pieChart.setLegendSide(Side.LEFT);
        root.setCenter(pieChart);
        Scene scene = new Scene(root, 1400, 700);
        stage.setScene(scene);
        stage.show();

    }*/


    public static void main(String[] args) {
        launch(args);
    }



}
