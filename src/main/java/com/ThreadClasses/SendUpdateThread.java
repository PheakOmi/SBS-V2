package com.ThreadClasses;

import com.DaoClasses.userDaoImpl;
import com.ModelClasses.Schedule_Model;

public class SendUpdateThread implements Runnable {

    private Thread t;

    Schedule_Model schedule, new_schedule;
    public SendUpdateThread(Schedule_Model schedule, Schedule_Model new_schedule) {

        this.schedule = schedule;
        this.new_schedule = new_schedule;

    }

    public void run(){
        try {

            new userDaoImpl().email_schedule_update(schedule, new_schedule);
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