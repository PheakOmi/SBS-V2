package com.ThreadClasses;

import com.DaoClasses.userDaoImpl;
import com.EntityClasses.Location_Master;
import com.EntityClasses.Schedule_Master;
import com.ModelClasses.Schedule_Model;

import java.util.ArrayList;
import java.util.List;

public class SendMonthlyThread implements Runnable {

    private Thread t;

    List<Schedule_Master> schedules  = new ArrayList<Schedule_Master>();
    public SendMonthlyThread(List<Schedule_Master> schedules) {

        this.schedules = schedules;

    }

    public void run(){
        try {

            new userDaoImpl().email_monthly_schedule(schedules);
        }
        catch (Exception e){

        }
    }

    public void start () {
        System.out.println("THREAD STARTED");
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

}