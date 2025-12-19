import java.util.*;
import java.io.*;
public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
            "July","August","September","October","November","December"};
    static int[][][] DatesAndCommodities = new int[12][28][5];


    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
            for (int i = 0; i < months.length; i++) {

                String filePath = "Data_Files/" + months[i] + ".txt";
                Scanner sc = null;

                try {
                    sc = new Scanner(new File(filePath));

                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        String[] parts = line.split(",");

                            if (parts.length != 3) { // Data files düzgün olmasına rağmen ne olur olmaz ekledik bi aksaklık çıkmaması için.
                                continue;
                            }

                        int day = Integer.parseInt(parts[0]);
                        String commodityName = parts[1];
                        int profit = Integer.parseInt(parts[2]);

                        if (day < 1 || day > 28) {
                            continue;
                        }

                        int commodityIndex = -1;
                        for (int c = 0; c < commodities.length; c++) {
                            if (commodities[c].equals(commodityName)) {
                                commodityIndex = c;
                                break;
                            }
                        }
                        if (commodityIndex == -1) {
                            continue;
                        }
                        DatesAndCommodities[i][day - 1][commodityIndex] = profit;
                    }

                }  catch (Exception e) {
                }  finally {
                if (sc != null) {
                    sc.close();
                }
                }
            }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        if  (month < 0 || month > 11) {
            return "INVALID_MONTH";
        }
        int maxProfit = Integer.MIN_VALUE;
        int maxCommodityIndex = -1;

        for (int c = 0; c < COMMS; c++) { // her 5 commoditi'ye bakıyor
            int totalProfit = 0;

            for (int d = 0; d < DAYS; d++) {
                totalProfit += DatesAndCommodities[month][d][c];
            }

            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                maxCommodityIndex = c;
            }
        }

        return commodities[maxCommodityIndex] + " " + maxProfit;
    }
    public static int totalProfitOnDay(int month, int day) {
        if ( month < 0 || month > 11 || day < 1 || day > 28) { // geçersiz gün ve aylar için
            return -99999;
        }

        int totalProfit = 0;

        for ( int c = 0; c < COMMS; c++) {
            totalProfit += DatesAndCommodities[month][c][day-1];
        }
        return totalProfit;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        if (from < 1 || from > 28 || to < 1 || to > 28 || from > to) {
            return -99999;
        }


        int commodityIndex = -1; // girilen Commodity'nin indexini bulmak için
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(commodity)) {
                commodityIndex = c;
                break;
            }
        }
        if (commodityIndex == -1) {
            return -99999;
        }

        int totalProfit = 0;


        for (int m = 0; m < MONTHS; m++) {
            for (int d = from - 1; d <= to - 1; d++) { // array 0-index
                totalProfit += DatesAndCommodities[m][d][commodityIndex];
            }
        }
        return totalProfit;
    }

    public static int bestDayOfMonth(int month) {
        if ( month < 0 || month > 11) {
            return -1;
        }
        int bestDayOfMonth = 1;
        int maxProfit = Integer.MIN_VALUE;

        for (int d = 0; d < DAYS; d++) {
            int totalProfit = 0;
             for  (int c = 0; c < COMMS; c++) {
                totalProfit += DatesAndCommodities[month][d][c];
             }
             if (totalProfit > maxProfit) {
                 maxProfit = totalProfit;
                 bestDayOfMonth = d+1;
             }
        }
        return bestDayOfMonth;
    }

    public static String bestMonthForCommodity(String comm) {
            int commodityIndex = -1;
            for (int c = 0; c < COMMS; c++) {
                if (commodities[c].equals(comm)) {
                    commodityIndex = c;
                    break;
                }
            }
            if (commodityIndex == -1) {
                return "INVALID_COMMODITY";
            }
            int maxProfit = Integer.MIN_VALUE;
            int bestMonth = 0;

            for (int m = 0; m < MONTHS; m++) {
                int monthlyProfit = 0;

                for (int d = 0; d < DAYS; d++) {
                    monthlyProfit += DatesAndCommodities[m][d][commodityIndex];
                }

                if (monthlyProfit > maxProfit) {
                    maxProfit = monthlyProfit;
                    bestMonth = m;
                }
            }
            return months[bestMonth];
    }

    public static int consecutiveLossDays(String comm) {
        return 1234;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

    public static int biggestDailySwing(int month) {
        return 1234;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }

    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}