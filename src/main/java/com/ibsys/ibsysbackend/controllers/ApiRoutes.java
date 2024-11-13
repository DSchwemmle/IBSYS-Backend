package com.ibsys.ibsysbackend.controllers;

public abstract class ApiRoutes {

    // Prognose
    public static final String FORECAST = "/forecast";

    // Lagerbestände
    public static final String WAREHOUSE_STOCK = "/warehousestocks";

    // Articles
    public static final String ARTICLES = "/articles";

    // Orders
    public static final String ORDERS = "/orders";

    // Work places
    public static final String WORK_PLACES = "/workplaces";

    // Waiting lists
    public static final String WAITING_LISTS = "/waitinglists";

    // Batches
    public static final String BATCHES = "/batches";



    // New Paths
    public static final String SALE_AND_PRODUCTION_PROGRAM = "sale-and-production-program";
    public static final String PRODUCTION_ORDERS = "production-orders";

    public static final String IMPORT = "import";
    public static final String CAPACITY_PLAN = "capacity-plan";
    public static final String CAPACITY_PLAN_SUM_UP = "capacity-plan-sum-up";
    public static final String DB_CONFIG = "db-config";
    public static final String MATERIAL_PLAN = "material-plan";


    private ApiRoutes() {}
}