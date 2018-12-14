package com.MainController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.EntityClasses.Booking_Master;
import com.ModelClasses.ID_Class;
import com.ModelClasses.Schedule_Model;
import com.ThreadClasses.SendBookingDetailRoundThread;
import com.ThreadClasses.SendBookingDetailThread;
import com.ThreadClasses.SendCreateThread;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.DaoClasses.Set_Student_Schedule;
import com.DaoClasses.StudentDao;
import com.DaoClasses.StudentDaoImpl;
import com.ModelClasses.Student_Booking;

@Controller
public class StudentController {
    StudentDao studentDao = new StudentDaoImpl();

    @RequestMapping(value = "/student/**")
    public String customer_home() {
        return "student/home";
    }

    @RequestMapping(value = "/student_info", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> studentInfo() {
        return studentDao.student_info();
    }

    @RequestMapping(value = "/location_info", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> location_Info() {
        return studentDao.location_info();
    }

    @RequestMapping(value = "/student_booking", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> student_booking(@RequestBody Student_Booking book_data) {
        Map<String,Object> map = new HashMap<String,Object>();
        map = studentDao.student_booking(book_data);
        if(map.get("status").equals("1"))
        {
            if(map.get("choice").equals("2"))
            {
                SendBookingDetailRoundThread t2 = new SendBookingDetailRoundThread((Booking_Master) map.get("dbooking"), (Booking_Master) map.get("rbooking"));
                t2.start();
            }
            else
            {
                SendBookingDetailThread t1 = new SendBookingDetailThread((Booking_Master) map.get("dbooking"));
                t1.start();
            }
        }


        return map;
//        return studentDao.student_booking(book_data);
    }


    @RequestMapping(value = "/list_booking_date", method = RequestMethod.GET)
    @ResponseBody public List<Object> list_booking_date() {
        return studentDao.list_booking_date();
    }


    @RequestMapping(value = "/student_history", method = RequestMethod.GET)
    @ResponseBody public List<Map<String, Object>> getHistory() throws ParseException {

        //System.out.println(studentDao.getHistory());
        List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
        map = studentDao.getHistory();
        System.out.println(map);
        return map;

    }


    @RequestMapping(value = "/customer_history_all", method = RequestMethod.GET)
    @ResponseBody public Map<String,Object> customerHistory(){ return studentDao.customerHistory(); }


    @RequestMapping(value = "/list_enable", method = RequestMethod.GET)
    @ResponseBody public List<Integer> listBookedDate(){ return studentDao.listBookedDate(); }

    @RequestMapping(value = "/remaining_ticket", method = RequestMethod.GET)
    @ResponseBody public Map<String,Object> remainingTicket(){ return studentDao.remainingTicket(); }

    @RequestMapping(value = "/student_cancel", method = RequestMethod.POST)
    @ResponseBody public Map<String,Object> studentCancel(@RequestBody ID_Class id_class) throws ParseException
    { return studentDao.cancel_ticket(id_class);}

    @RequestMapping(value="/notification", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> Notification() {
        return studentDao.Notification();
    }

    //@Scheduled(cron="*/5 * * * * *")
    //public void updateEmployeeInventory() {
       // System.out.println("Started cron job 1");
//    @Scheduled(cron="0 2 * * * *")
//    public void updateEmployeeInventory() throws ParseException{
//        System.out.println("Started create schedule for studen");
//        Set_Student_Schedule c =new Set_Student_Schedule();
//        c.createSchedule();
//        System.out.println("End");
//    }
    
    public static void main(String args[]) throws ParseException{
    	Set_Student_Schedule c =new Set_Student_Schedule();
        c.createSchedule();
        System.out.println("End");

//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        f.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
//        System.out.println(f.format(new Date()));
//        Date dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(f.format(new Date()));
//        Calendar c = Calendar.getInstance();
//        c.setTime(dt);
//        c.add(Calendar.DATE, 1);
//        dt = c.getTime();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String tmrDateTimeString = sdf.format(dt);
//        System.out.println(tmrDateTimeString);

    }


}
