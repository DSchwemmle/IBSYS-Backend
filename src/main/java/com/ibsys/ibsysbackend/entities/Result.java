package com.ibsys.ibsysbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    private General general;
    private DefectiveGoods defectiveGoods;
    private NormalSale normalSale;
    private DirectSale directSale;
    private MarketplaceSale marketplaceSale;
    private Summary summary;

    public static class General {
        private Capacity capacity;

        @JsonProperty("possiblecapacity")
        private PossibleCapacity possibleCapacity;

        @JsonProperty("relpossiblenormalcapacity")
        private RelPossibleNormalCapacity relPossibleNormalCapacity;

        @JsonProperty("productivetime")
        private ProductiveTime productiveTime;

        private Efficiency efficiency;

        @JsonProperty("sellwish")
        private SellWish sellWish;

        @JsonProperty("salesquantity")
        private SalesQuantity salesQuantity;

        @JsonProperty("deliveryreliability")
        private DeliveryReliability deliveryReliability;

        @JsonProperty("idletime")
        private IdleTime idleTime;

        @JsonProperty("idletimecosts")
        private IdleTimeCosts idleTimeCosts;

        @JsonProperty("storevalue")
        private StoreValue storeValue;

        @JsonProperty("storagecosts")
        private StorageCosts storageCosts;
    }

    public static class DefectiveGoods {
        private int quantity;
        private double costs;
    }

    public static class NormalSale {

        @JsonProperty("salesprice")
        private double salesPrice;
        private double profit;

        @JsonProperty("profitperunit")
        private double profitPerUnit;
    }

    public static class DirectSale {
        private double profit;

        @JsonProperty("contractpenalty")
        private double contractPenalty;
    }

    public static class MarketplaceSale {
        private double profit;
    }

    public static class Summary {
        private double profit;
    }

    public static class Capacity {
        private int current;
        private double average;
        private int all;
    }

    public static class PossibleCapacity {
        private int current;
        private double average;
        private int all;
    }

    public static class RelPossibleNormalCapacity {
        private String current;
        private String average;
        private String all;
    }

    public static class ProductiveTime {
        private int current;
        private double average;
        private int all;
    }

    public static class Efficiency {
        private String current;
        private String average;
        private String all;
    }

    public static class SellWish {
        private int current;
        private double average;
        private int all;
    }

    public static class SalesQuantity {
        private int current;
        private double average;
        private int all;
    }

    public static class DeliveryReliability {
        private String current;
        private String average;
        private String all;
    }

    public static class IdleTime {
        private int current;
        private double average;
        private int all;
    }

    public static class IdleTimeCosts {
        private double current;
        private double average;
        private double all;
    }

    public static class StoreValue {
        private double current;
        private double average;
        private double all;
    }

    public static class StorageCosts {
        private double current;
        private double average;
        private double all;
    }
}