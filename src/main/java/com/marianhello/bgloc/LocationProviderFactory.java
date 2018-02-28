/*
According to apache license

This is fork of christocracy cordova-plugin-background-geolocation plugin
https://github.com/christocracy/cordova-plugin-background-geolocation

This is a new class
*/

package com.marianhello.bgloc;

import android.content.Context;
import com.marianhello.bgloc.data.DAOFactory;
import com.marianhello.bgloc.LocationProvider;
import com.tenforwardconsulting.bgloc.DistanceFilterLocationProvider;
import com.marianhello.bgloc.ActivityRecognitionLocationProvider;
import java.lang.IllegalArgumentException;

/**
 * LocationProviderFactory
 */
public class LocationProviderFactory {

    private LocationService mLocationService;
    private Config mConfig;

    public LocationProviderFactory(LocationService locationService, Config config) {
        this.mLocationService = locationService;
        this.mConfig = config;
    };

    public LocationProvider getInstance (Integer locationProvider) {
        LocationProvider provider;
        switch (locationProvider) {
            case Config.DISTANCE_FILTER_PROVIDER:
                provider = new DistanceFilterLocationProvider(mLocationService, mConfig);
                break;
            case Config.ACTIVITY_PROVIDER:
                provider = new ActivityRecognitionLocationProvider(mLocationService, mConfig);
                break;
            case Config.RAW_PROVIDER:
                provider = new RawLocationProvider(mLocationService, mConfig);
                break;
            default:
                throw new IllegalArgumentException("Provider not found");
        }

        return provider;
    }
}
