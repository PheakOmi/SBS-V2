package com.ServiceClasses;

import com.DaoClasses.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService{

    StudentDao studentDao;

    public Map<String,Object> student_info(){
        return studentDao.student_info();
    }
}
