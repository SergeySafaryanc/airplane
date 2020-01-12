package ru.sfedu.airplane.api;

public class Fill {

    int maxGen = 100;
    int genId;

    /**
     * The First name.
     */
//    Pilot
    static String[] firstName = {"Ivan", "Kirill", "Sergey", "Daniil", "Denis", "Dmitry", "Yevgeny", "Anatoliy", "Anton", "Vladimir"};
    /**
     * The Last name.
     */
    static String[] lastName = {"Parfenov", "Medvedev", "Yeremin", "Raykin", "Zhirnov", "Shalyapin", "Shchitov", "Kuznetsov", "Tsvetaeva", "Hasymov"};
    /**
     * The Type.
     */
    static String[] type = {"BOMBER", "PASSENGER", "FIREAIRCRAFT", "FITHER", "FREIGHT", "AGRICULTURAL", "BOMBER", "PASSENGER", "FIREAIRCRAFT", "FITHER"};
    /**
     * The Bomb model.
     */
//    Bomber
    static String[] BombModel = {"95MC", "22M3", "160", "1B", "52H", "22M", "16", "4"};
    /**
     * The Bomb prod.
     */
    static String[] BombProd = {"Tu", "Tu", "Tu", "B", "B", "Tu", "Tu", "Tu"};
    /**
     * The Max distance bomb.
     */
    static int[] maxDistanceBomb = {10000, 15000, 20000, 25000, 11000, 12000, 13000, 14000};
    /**
     * The Num bomb.
     */
    static int[] numBomb = {25, 25, 20, 13, 14, 15, 19, 20};
    /**
     * The Type bomb.
     */
    static String[] typeBomb = {"OF", "BB", "OF", "BB", "OF", "BB", "OF", "BB"};
    /**
     * The Fither model.
     */
//    Fither
    static String[] FitherModel = {"35", "27", "30", "29", "31", "34", "25"};
    /**
     * The Fither prod.
     */
    static String[] FitherProd = {"Su", "Su", "Su", "Mig", "Mig", "Su", "Su"};
    /**
     * The Max distance fither.
     */
    static int[] maxDistanceFither = {16000, 11000, 21000, 22000, 17000, 12500, 13800};
    /**
     * The Num fither.
     */
    static int[] numFither = {10, 9, 10, 8, 9, 11, 12};
    /**
     * The Type fither.
     */
    static String[] typeFither = {"OD", "LG", "OD", "LG", "OD", "LG", "OD", "LG"};
    /**
     * The Agr model.
     */
//    Agricultural&FireAircraft
    static String[] AgrModel = {"2", "2"};
    /**
     * The Agr prod.
     */
    static String[] AgrProd = {"Po", "An"};
    /**
     * The Fa model.
     */
    static String[] FAModel = {"76", "32"};
    /**
     * The Fa prod.
     */
    static String[] FAProd = {"Il", "An"};
    /**
     * The Max distance agr.
     */
    static int[] maxDistanceAgr = {10000, 10500};
    /**
     * The Disp.
     */
    static int[] disp = {500, 400};
    /**
     * The Spray.
     */
    static int[] spray = {20, 15};
    /**
     * The Freight model.
     */
//    Freight
    static String[] FreightModel = {"20", "12", "22", "40", "77", "122", "124", "225"};
    /**
     * The Freight prod.
     */
    static String[] FreightProd = {"An", "An", "An", "An", "An", "An", "An", "An"};
    /**
     * The Max distance freight.
     */
    static int[] maxDistanceFreight = {5100, 5200, 5300, 5400, 5500, 5600, 5700, 5800};
    /**
     * The Max w.
     */
    static int[] maxW = {21, 22, 23, 24, 25, 26, 27, 28};
    /**
     * The Pas model.
     */
//    Passenger
    static String[] PasModel = {"A320", "A330", "A340", "B737"};
    /**
     * The Pas prod.
     */
    static String[] PasProd = {"Airbus", "Airbus", "Airbus", "Boeing"};
    /**
     * The Max distance pas.
     */
    static int[] maxDistancePas = {5100, 5200, 5300, 5400};
    /**
     * The Num pass.
     */
    static int[] numPass = {320, 330, 300, 350};
    /**
     * The Serv.
     */
    static String[] serv = {"All", "All", "All", "All"};
    /**
     * The Air id.
     */
//    Fly
    static long[] airId = {1, 1, 1, 1, 1, 1, 2, 2, 2, 3};
    /**
     * The Time fly.
     */
    static int[] timeFly = {60, 120, 180, 60, 120, 180, 60, 120, 180, 240};
    /**
     * The Pilot id.
     */
    static long[] pilotId = {1, 2, 3, 4, 5, 6, 7, 6, 2, 1};
    /**
     * The Type pilot.
     */
    static String[] typePilot = {"BOMBER", "PASSENGER", "FIREAIRCRAFT", "FITHER", "FREIGHT", "AGRICULTURAL", "BOMBER", "AGRICULTURAL", "PASSENGER", "BOMBER"};

}
