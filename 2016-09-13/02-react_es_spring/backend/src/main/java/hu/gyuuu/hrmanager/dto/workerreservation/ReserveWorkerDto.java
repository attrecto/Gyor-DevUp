package hu.gyuuu.hrmanager.dto.workerreservation;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import hu.gyuuu.hrmanager.Const;

public class ReserveWorkerDto {

    @NotNull
    @JsonFormat(shape=Shape.STRING, timezone=Const.TIMEZONE, pattern=Const.DATE_FORMAT)
    private Date startDate;
    @NotNull
    @JsonFormat(shape=Shape.STRING, timezone=Const.TIMEZONE, pattern=Const.DATE_FORMAT)
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
