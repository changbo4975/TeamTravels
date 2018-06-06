package com.travelfoots.ntitreetravelfoots;

import com.travelfoots.ntitreetravelfoots.domain.TravelRecord;
import com.travelfoots.ntitreetravelfoots.util.SaveLoad;

public class TravelStart {

    public TravelRecord start() {
        TravelRecord travelRecord = new TravelRecord();

        travelRecord.setStartDate(Long.toString(System.currentTimeMillis()));

        SaveLoad s = new SaveLoad();

        return travelRecord;
    }
}
