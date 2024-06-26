package com.onesty.services.search.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

@UtilityClass
public class DistanceUtils {

    public static Double haversineDistance(Point point1, Point point2) {
        if (point1 == null || point2 == null) {
            return null;
        }
        double dLat = Math.toRadians(point2.getY() - point1.getY());
        double dLong = Math.toRadians(point2.getX() - point1.getX());

        double startLat = Math.toRadians(point1.getY());
        double endLat = Math.toRadians(point2.getY());

        double a = haversine(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversine(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Metrics.KILOMETERS.getMultiplier() * c;
    }

    private static double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

}