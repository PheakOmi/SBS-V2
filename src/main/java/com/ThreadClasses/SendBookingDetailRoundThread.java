package com.ThreadClasses;

import com.DaoClasses.QR_Image_Gemerator;
import com.EntityClasses.Booking_Master;
import com.HibernateUtil.HibernateUtil;
import org.hibernate.Session;

public class SendBookingDetailRoundThread implements Runnable {

    private Thread t;

    Booking_Master dbooking;
    Booking_Master rbooking;

    public SendBookingDetailRoundThread(Booking_Master dbooking, Booking_Master rbooking) {

        this.dbooking = dbooking;
        this.rbooking = rbooking;

    }

    public void run(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("In RUNNNNNNNNNNNNNNN");
            QR_Image_Gemerator.sendEmailQRCode2(session, dbooking, rbooking);
            session.flush();
            session.close();
            System.out.println("Below RUNNNNNNNNNNNNNNN");
        }
        catch (Exception e){

        }
    }

    public void start () {
        System.out.println("THREAD STARTEDDDDDDDDD");
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

}