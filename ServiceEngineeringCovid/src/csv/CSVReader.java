package csv;

import data.DataBean;
import data.District;
import data.FederalState;

import java.io.*;
import java.net.URL;
import java.nio.Buffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class CSVReader {
    private DataBean dataBean;

    public CSVReader() {
        dataBean = new DataBean();
    }

    private void readCSVDistricts() throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new URL("https://covid19-dashboard.ages.at/data/CovidFaelle_GKZ.csv").openStream());
        FileOutputStream fileOutputStream = new FileOutputStream("CovidFaelle_GKZ.csv");
        byte bytes[] = new byte[1024];
        int byteContent;
        while((byteContent = inputStream.read(bytes,0,1024)) != -1) {
            fileOutputStream.write(bytes,0, byteContent);
        }
        String row = "";
        BufferedReader csvReader = new BufferedReader(new FileReader("CovidFaelle_GKZ.csv"));
        int counter = 0; //otherwise you'll get a NumberFormatException, since you're trying to parse the headers of each column
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";");
            for (FederalState federalState : dataBean.getRepublic().getStates()) {
                if (counter != 0 && (Integer.parseInt(data[1]) / 100) == federalState.getStateID()) {
                    federalState.addDistrict(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]));
                }
            }
            counter++;
        }
    }

    private void readCSVTimeLine() throws IOException, ParseException {
        BufferedInputStream inputStream = new BufferedInputStream(new URL("https://covid19-dashboard.ages.at/data/CovidFaelle_Timeline_GKZ.csv").openStream());
        FileOutputStream fileOutputStream = new FileOutputStream("CovidFaelle_Timeline_GKZ.csv");
        byte bytes[] = new byte[1024];
        int byteContent;
        while((byteContent = inputStream.read(bytes,0,1024)) != -1) {
            fileOutputStream.write(bytes,0, byteContent);
        }
        String row = "";
        BufferedReader csvReader = new BufferedReader(new FileReader("CovidFaelle_Timeline_GKZ.csv"));
        int counter = 0; //otherwise you'll get a NumberFormatException, since you're trying to parse the headers of each column
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";");
            for (FederalState federalState : dataBean.getRepublic().getStates()) {
                if (counter != 0 && (Integer.parseInt(data[2]) / 100 == federalState.getStateID())) {
                    for (District district : federalState.getDistricts()) {
                        if (district.getPostalCode() == Integer.parseInt(data[2])) {
                            district.addDistrictNode(df.parse(data[0]), data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                                    Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), Double.parseDouble(data[7].replace(',','.')),
                                    Integer.parseInt(data[8]), Integer.parseInt(data[9]), Integer.parseInt(data[10]), Integer.parseInt(data[11]));
                        }
                    }
                }
            }
            counter++;
        }
    }

    private void readCSVCaseNumbers() throws IOException, ParseException {
        BufferedInputStream inputStream = new BufferedInputStream(new URL("https://covid19-dashboard.ages.at/data/CovidFallzahlen.csv").openStream());
        FileOutputStream fileOutputStream = new FileOutputStream("CovidFallzahlen.csv");
        byte bytes[] = new byte[1024];
        int byteContent;
        while((byteContent = inputStream.read(bytes,0,1024)) != -1) {
            fileOutputStream.write(bytes,0, byteContent);
        }
        String row = "";
        BufferedReader csvReader = new BufferedReader(new FileReader("CovidFallzahlen.csv"));
        int counter = 0; //otherwise you'll get a NumberFormatException, since you're trying to parse the headers of each column
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(";");
            for (FederalState federalState : dataBean.getRepublic().getStates()) {
                if (counter != 0 && (Integer.parseInt(data[7]) == federalState.getStateID())) {
                    federalState.addFederalStateNode(df.parse(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), data[8]);
                }
            }
            counter++;
        }
    }

    public DataBean setUp() throws IOException, ParseException {
        readCSVDistricts();
        readCSVTimeLine();
        readCSVCaseNumbers();
        return this.dataBean;
    }


}
