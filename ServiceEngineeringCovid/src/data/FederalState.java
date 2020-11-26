package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;

public class FederalState {
    private ArrayList<District> districtArrayList;
    private ArrayList<FederalStateNode> federalStateList;
    private String stateName;
    private int stateID;
    private int icuCapacity;
    private int hospitalCapacity;

    public FederalState(String name, int stateID, int hospitalCapacity, int icuCapacity) {
        this.stateName = name;
        this.stateID = stateID;
        districtArrayList = new ArrayList<>();
        federalStateList = new ArrayList<>();
        this.icuCapacity = icuCapacity;
        this.hospitalCapacity = hospitalCapacity;
    }

    public int getIcuCapacity() {
        return this.icuCapacity;
    }

    public int getHospitalCapacity() {
        return this.hospitalCapacity;
    }

    public District addDistrict(String districtName, int postalCode, int inhabitants, int sumInfected, int sumDeath, int casesLastSevenDays) {
        District district = new District(districtName, postalCode, inhabitants, sumInfected, sumDeath, casesLastSevenDays);
        districtArrayList.add(district);
        return district;
    }

    public int getStateID() {
        return this.stateID;
    }

    public ArrayList<FederalStateNode> getFederalStateNodeList() {
        return federalStateList;
    }

    public String getStateName() {
        return this.stateName;
    }

    public ArrayList<District> getDistricts() {
        return this.districtArrayList;
    }

    public void addFederalStateNode(Date date, int testSum, int nrHospital, int nrICU, int nrNoMoreHospital, int nrNoMoreICU, String stateName) {
        federalStateList.add(new FederalStateNode(date, testSum,nrHospital, nrICU, nrNoMoreHospital, nrNoMoreICU, stateName));
    }

    public class FederalStateNode {
        Date date;
        int testSum;
        int nrHospital;
        int nrICU;
        int nrNoMoreHospital;
        int nrNoMoreICU;
        String stateName;

        FederalStateNode(Date date, int testSum, int nrHospital, int nrICU, int nrNoMoreHospital, int nrNoMoreICU, String stateName) {
            this.date = date;
            this.testSum = testSum;
            this.nrHospital = nrHospital;
            this.nrICU = nrICU;
            this.nrNoMoreHospital = nrNoMoreHospital;
            this.nrNoMoreICU = nrNoMoreICU;
            this.stateName = stateName;
        }

        public Date getDate() {
            return date;
        }

        public int getTestSum() {
            return testSum;
        }

        public int getNrHospital() {
            return nrHospital;
        }

        public int getNrICU() {
            return nrICU;
        }

        public int getNrNoMoreHospital() {
            return nrNoMoreHospital;
        }

        public int getNrNoMoreICU() {
            return nrNoMoreICU;
        }

        public String getStateName() {
            return stateName;
        }
    }


}
