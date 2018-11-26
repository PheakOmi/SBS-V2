package com.ThreadClasses;

import com.DaoClasses.userDaoImpl;
import com.ModelClasses.Schedule_Model;

public class SendReqKITAdminThread implements Runnable {

    private Thread t;

    Schedule_Model schedule_model;
    public SendReqKITAdminThread(Schedule_Model schedule_model) {

        this.schedule_model = schedule_model;

    }

    public void run(){
        try {

            new userDaoImpl().email_schedule_create(schedule_model);
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