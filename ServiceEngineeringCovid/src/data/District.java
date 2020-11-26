package data;

import java.util.ArrayList;
import java.util.Date;

public class District {
    private String districtName;
    private int postalCode;
    private int inhabitants;
    private int sumInfected;
    private int sumDeath;
    private int casesLastSevenDays;
    private ArrayList<DistrictNode> districtList;

    public  District (String districtName, int postalCode, int inhabitants, int sumInfected, int sumDeath, int casesLastSevenDays){
        this.districtName = districtName;
        this.postalCode = postalCode;
        this.inhabitants = inhabitants;
        this.sumInfected = sumInfected;
        this.sumDeath = sumDeath;
        this.casesLastSevenDays = casesLastSevenDays;
        districtList = new ArrayList<>();
    }

    public int getInhabitants() {
        return this.inhabitants;
    }

    public int getSumInfected() {
        return this.sumInfected;
    }

    public int getSumDeath() {
        return this.sumDeath;
    }

    public int getCasesLastSevenDays() {
        return this.casesLastSevenDays;
    }

    public ArrayList<DistrictNode> getDistrictNodeList() {
        return this.districtList;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public int getPostalCode() {
        return this.postalCode;
    }

    public void addDistrictNode(Date date, String districtName, int gkz, int inhabitants, int nrInfected, int nrInfectedSum, int nrInfectedSevenDays, double sevenDayIncidence, int nrDeathsDaily, int nrDeathsSum, int nrHealedDaily, int nrHealedSum) {
        districtList.add( new DistrictNode(date, districtName, gkz, inhabitants, nrInfected, nrInfectedSum, nrInfectedSevenDays, sevenDayIncidence, nrDeathsDaily, nrDeathsSum, nrHealedDaily, nrHealedSum));
    }

    public class DistrictNode {
        Date date;
        private String districtName;
        private int gkz;
        private int inhabitants;
        private int nrInfected;
        private int nrInfectedSum;
        private int nrInfectedSevenDays;
        private double sevenDayIncidence;
        private int nrDeathsDaily;
        private int nrDeathsSum;
        private int nrHealedDaily;
        private int nrHealedSum;


        public DistrictNode(Date date, String districtName, int gkz, int inhabitants, int nrInfected, int nrInfectedSum, int nrInfectedSevenDays, double sevenDayIncidence, int nrDeathsDaily, int nrDeathsSum, int nrHealedDaily, int nrHealedSum) {
            this.date = date;
            this.districtName = districtName;
            this.gkz = gkz;
            this.inhabitants = inhabitants;
            this.nrInfected = nrInfected;
            this.nrInfectedSum = nrInfectedSum;
            this.nrInfectedSevenDays = nrInfectedSevenDays;
            this.sevenDayIncidence = sevenDayIncidence;
            this.nrDeathsDaily = nrDeathsDaily;
            this.nrDeathsSum = nrDeathsSum;
            this.nrHealedDaily = nrHealedDaily;
            this.nrHealedSum = nrHealedSum;

        }

        public Date getDate() {
            return date;
        }

        public String getDistrictName() {
            return districtName;
        }

        public int getGkz() {
            return gkz;
        }

        public int getInhabitants() {
            return inhabitants;
        }

        public int getNrInfected() {
            return nrInfected;
        }

        public int getNrInfectedSum() {
            return nrInfectedSum;
        }

        public int getNrInfectedSevenDays() {
            return nrInfectedSevenDays;
        }

        public double getSevenDayIncidence() {
            return sevenDayIncidence;
        }

        public int getNrDeathsDaily() {
            return nrDeathsDaily;
        }

        public int getNrDeathsSum() {
            return nrDeathsSum;
        }

        public int getNrHealedDaily() {
            return nrHealedDaily;
        }

        public int getNrHealedSum() {
            return nrHealedSum;
        }
    }
}
