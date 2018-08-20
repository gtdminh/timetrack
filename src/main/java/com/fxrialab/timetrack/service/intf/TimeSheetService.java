package com.fxrialab.timetrack.service.intf;

import com.fxrialab.timetrack.model.TimeRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by Minh T. on 6/11/2018.
 */
public interface TimeSheetService {
    List<TimeRecord> getByDay(Date date);
    List<TimeRecord> getByWeek(Date date);
    List<TimeRecord> getByMonth(Date date);
    TimeRecord get(Long id);
    TimeRecord add(Date start, int duration);
    TimeRecord save(TimeRecord record);
    TimeRecord copy(TimeRecord record, Date newStart);
}
