package data;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;

import java.util.ArrayList;
import java.util.Date;

public class Chart {
    Main main;
    DataBean dataBean;
    public Chart(DataBean dataBean) {
        main = new Main();
        this.dataBean = dataBean;
    }

    public void detailView(Stage stage, District district) {

        TableView<District.DistrictNode> tableView = new TableView();
        TableColumn<District.DistrictNode, Date> columnOne = new TableColumn<>("Datum");
        columnOne.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<District.DistrictNode, String> columnTwo = new TableColumn<>("Bezirk");
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("districtName"));

        TableColumn<District.DistrictNode, Integer> columnThree = new TableColumn<>("Gemeindekennzahl");
        columnThree.setCellValueFactory(new PropertyValueFactory<>("gkz"));

        TableColumn<District.DistrictNode, Integer> columnFour = new TableColumn<>("Einwohner");
        columnFour.setCellValueFactory(new PropertyValueFactory<>("inhabitants"));

        TableColumn<District.DistrictNode, Integer> columnFive = new TableColumn<>("#Inifziert");
        columnFive.setCellValueFactory(new PropertyValueFactory<>("nrInfected"));

        TableColumn<District.DistrictNode, Integer> columnSix = new TableColumn<>("#InifziertSumme");
        columnSix.setCellValueFactory(new PropertyValueFactory<>("nrInfectedSum"));

        TableColumn<District.DistrictNode, Integer> columnSeven = new TableColumn<>("#Infiziert-7-Tage");
        columnSeven.setCellValueFactory(new PropertyValueFactory<>("nrInfectedSevenDays"));

        TableColumn<District.DistrictNode, Double> columnEight = new TableColumn<>("7-Tage-Inzidenz");
        columnEight.setCellValueFactory(new PropertyValueFactory<>("sevenDayIncidence"));

        TableColumn<District.DistrictNode, Integer> columnNine = new TableColumn<>("Tote täglich");
        columnNine.setCellValueFactory(new PropertyValueFactory<>("nrDeathsDaily"));

        TableColumn<District.DistrictNode, Integer> columnTen = new TableColumn<>("Tote Summe");
        columnTen.setCellValueFactory(new PropertyValueFactory<>("nrDeathsSum"));

        TableColumn<District.DistrictNode, Integer> columnEleven = new TableColumn<>("Geheilt täglich");
        columnEleven.setCellValueFactory(new PropertyValueFactory<>("nrHealedDaily"));

        TableColumn<District.DistrictNode, Integer> columnTwelve = new TableColumn<>("Geheilt Summe");
        columnTwelve.setCellValueFactory(new PropertyValueFactory<>("nrHealedSum"));

        tableView.getColumns().add(columnOne);
        tableView.getColumns().add(columnTwo);
        tableView.getColumns().add(columnThree);
        tableView.getColumns().add(columnFour);
        tableView.getColumns().add(columnFive);
        tableView.getColumns().add(columnSix);
        tableView.getColumns().add(columnSeven);
        tableView.getColumns().add(columnEight);
        tableView.getColumns().add(columnNine);
        tableView.getColumns().add(columnTen);
        tableView.getColumns().add(columnEleven);
        tableView.getColumns().add(columnTwelve);

        tableView.setRowFactory(tv -> {
            TableRow<District.DistrictNode> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    lineChartDistrict(district, stage);
                }
            });
            return row;
        });

        for (District.DistrictNode dn : district.getDistrictNodeList()) {
            tableView.getItems().add(dn);
        }
        ArrayList<District.DistrictNode> test = district.getDistrictNodeList();
        BorderPane root = main.createPaneWithMenu(stage);
        root.setCenter(tableView);
        Scene scene = new Scene(root, 1300, 700);
        stage.setScene(scene);
        stage.show();

    }

    public void lineChartDistrict(District district, Stage stage) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setTitle(district.getDistrictName());
        XYChart.Series series = new XYChart.Series();
        series.setName("Aktive Fälle");
        for (District.DistrictNode dn : district.getDistrictNodeList()) {
            series.getData().add(new XYChart.Data(dn.getDate().toString(), dn.getNrInfected()));
        }
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("7-Tage-Inzidenz");
        for (District.DistrictNode dn : district.getDistrictNodeList()) {
            series1.getData().add(new XYChart.Data(dn.getDate().toString(), dn.getSevenDayIncidence()));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Tote");
        for (District.DistrictNode dn : district.getDistrictNodeList()) {
            series2.getData().add(new XYChart.Data(dn.getDate().toString(), dn.getNrDeathsSum()));
        }
        BorderPane root = main.createPaneWithMenu(stage);
        root.setCenter(lineChart);
        Scene scene = new Scene(root, 1300, 700);
        lineChart.getData().add(series);
        lineChart.getData().add(series1);
        lineChart.getData().add(series2);
        stage.setScene(scene);
        stage.show();

    }

    public FederalState showFederalStateStatistics(Stage stage, FederalState federalState) {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setTitle(federalState.getStateName());

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Anzahl in Krankenhäusern");
        for (FederalState.FederalStateNode fn : federalState.getFederalStateNodeList()) {
            series1.getData().add(new XYChart.Data(fn.getDate().toString(), fn.getNrHospital()));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Anzahl ICU");
        for (FederalState.FederalStateNode fn : federalState.getFederalStateNodeList()) {
            series2.getData().add(new XYChart.Data(fn.getDate().toString(), fn.getNrICU()));
        }
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Insgesamte Kapazität");
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("ICU Kapazität");
        for(FederalState.FederalStateNode fn : federalState.getFederalStateNodeList()) {
            series3.getData().add(new XYChart.Data(fn.getDate().toString(), federalState.getHospitalCapacity()));
            series4.getData().add(new XYChart.Data(fn.getDate().toString(), federalState.getIcuCapacity()));
        }

        BorderPane root = main.createPaneWithMenu(stage);
        root.setCenter(lineChart);
        Scene scene = new Scene(root, 1300, 700);
        lineChart.getData().addAll(series1, series2, series3, series4);
        stage.setScene(scene);
        stage.show();
        return federalState;
    }
}
