package com.ThreadClasses;

import com.DaoClasses.QR_Image_Gemerator;
import com.DaoClasses.userDaoImpl;
import com.EntityClasses.Booking_Master;
import com.HibernateUtil.HibernateUtil;
import org.hibernate.Session;

public class SendBookingDetailThread implements Runnable {

    private Thread t;

    Booking_Master booking_master;
    public SendBookingDetailThread(Booking_Master booking_master) {

        this.booking_master = booking_master;

    }

    public void run(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("In RUNNNNNNNNNNNNNNN");
            QR_Image_Gemerator.sendEmailQRCode(session, booking_master);
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