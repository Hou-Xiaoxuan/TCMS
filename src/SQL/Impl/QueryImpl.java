/*
 * @Author: xv_rong
 * @Date: Fri Jul 09 2021 23:45:56
 * @LastEditors: xv_rong
 * @LastEditTime: 2021-07-14 09:13:56
 * @Description: 
 * @FilePath: \TCMS\src\SQL\Impl\QueryImpl.java
 */
package SQL.Impl;

import java.util.ArrayList;

import SQL.Query;
import Tool.Education;
import dao.impl.CourseDaoImpl;
import dao.impl.ManagerDaoImpl;
import dao.impl.StudentDaoImpl;
import dao.impl.TClassDaoImpl;
import dao.impl.TeacherDaoImpl;
import entity.Course.Course;
import entity.Person.Student;
import entity.Person.Teacher;
import entity.TClass.TClass;

public class QueryImpl implements Query {

    @Override

    // ok
    public ArrayList<Course> queryCourse(Education edu, boolean courseState) {
        String sql = "select * from course where education=? and state=?";
        CourseDaoImpl tmp = new CourseDaoImpl();
        Object arr[] = new Object[2];
        arr[0] = edu.ordinal();
        arr[1] = courseState;
        return tmp.selectCourse(sql, arr);
    }

    @Override
    public ArrayList<TClass> queryTClass(int courseId, boolean tClassState) {
        String sql = "select * from class where courseID=? and state=?";
        TClassDaoImpl tmp = new TClassDaoImpl();
        Object arr[] = new Object[2];
        arr[0] = courseId;
        arr[1] = tClassState;
        return tmp.selectTClass(sql, arr);
    }

    @Override
    public ArrayList<TClass> queryTClassByStudent(int studentId, boolean tClassState) {
        String sql = "SELECT * FROM class t WHERE t.classID IN (SELECT class.classID FROM student LEFT JOIN taking ON student.studentID=taking.studentID LEFT JOIN class ON taking.classID=class.classID WHERE student.state=TRUE AND taking.state=TRUE AND class.state=? AND student.studentID=?)";
        TClassDaoImpl tmp = new TClassDaoImpl();
        Object arr[] = new Object[2];
        arr[0] = tClassState;
        arr[1] = studentId;
        return tmp.selectTClass(sql, arr);
    }

    // 149
    @Override
    public ArrayList<Student> queryStudent(int tClassId, boolean studentState) {
        String sql = "select * from student t where t.studentID in (SELECT studentID FROM student natural JOIN taking WHERE classID=? and state=?)";
        Object arr[] = new Object[2];
        arr[0] = tClassId;
        arr[1] = studentState;
        StudentDaoImpl tmp = new StudentDaoImpl();
        return tmp.selectStudent(sql, arr);
    }

    @Override
    public Teacher queryTeacher(int tClassId, boolean teacherState) {
        String sql = "select * from teacher where state=? and teacherID=(select teacherID from class where classID=?)";
        TeacherDaoImpl tmp = new TeacherDaoImpl();
        Object arr[] = new Object[2];
        arr[1] = tClassId;
        arr[0] = teacherState;
        return tmp.selectTeacher(sql, arr).get(0);
    }

    @Override
    public ArrayList<Teacher> queryTeacher(boolean teacherState) {
        String sql = "select * from teacher where state=?";
        TeacherDaoImpl tmp = new TeacherDaoImpl();
        Object arr[] = new Object[1];
        arr[0] = teacherState;
        return tmp.selectTeacher(sql, arr);
    }

    @Override
    public ArrayList<Student> queryStudentByTeacher(int teacherId, boolean studentState) {
        String sql = "select * from student where studentID in (select studentID from taking where classID in (select classID from class where teacherID=?)) and state=?";
        Object arr[] = new Object[2];
        arr[0] = teacherId;
        arr[1] = studentState;
        StudentDaoImpl tmp = new StudentDaoImpl();
        return tmp.selectStudent(sql, arr);
    }

    @Override
    public Student queryStudentByStudentID(int studentId, boolean studentState) {
        String sql = "select * from student where studentID=? and state=?";
        Object arr[] = new Object[2];
        arr[0] = studentId;
        arr[1] = studentState;
        StudentDaoImpl tmp = new StudentDaoImpl();
        return tmp.selectStudent(sql, arr).get(0);

    }

    @Override
    public ArrayList<TClass> queryTClass(boolean tClassState) {
        String sql = "select * from class where state=?";
        TClassDaoImpl tmp = new TClassDaoImpl();
        Object arr[] = new Object[1];
        arr[0] = tClassState;
        return tmp.selectTClass(sql, arr);

    }

    @Override
    public boolean IsExistCourse(Education education, String courseName, boolean courseState) {
        String sql = "select * from course where education=? and coursename=? and state=?";
        Object arr[] = new Object[3];
        arr[0] = education.ordinal();
        arr[1] = courseName;
        arr[2] = courseState;
        CourseDaoImpl tmp = new CourseDaoImpl();
        if (tmp.selectCourse(sql, arr).isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public ArrayList<Course> queryCourse(boolean CourseState) {
        String sql = "select * from course where state=?";
        Object arr[] = new Object[1];
        arr[0] = CourseState;
        CourseDaoImpl tmp = new CourseDaoImpl();
        return tmp.selectCourse(sql, arr);
    }

    @Override
    public ArrayList<Student> queryStudent(boolean StudentState) {
        String sql = "select * from student where state=?";
        Object arr[] = new Object[1];
        arr[0] = StudentState;
        StudentDaoImpl tmp = new StudentDaoImpl();
        return tmp.selectStudent(sql, arr);
    }

    @Override
    public boolean IsExistStudent(int studentId, boolean state) {

        String sql = "select * from student where studentID=? and state=?";
        Object arr[] = new Object[2];
        arr[0] = studentId;
        arr[1] = state;
        // arr[2] = courseState;
        StudentDaoImpl tmp = new StudentDaoImpl();
        if (tmp.selectStudent(sql, arr).isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean IsExistCourse(int courseId, boolean state) {
        String sql = "select * from course where courseID=? and state=?";
        Object arr[] = new Object[2];
        arr[0] = courseId;
        arr[1] = state;
        // arr[2] = courseState;
        CourseDaoImpl tmp = new CourseDaoImpl();
        if (tmp.selectCourse(sql, arr).isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean IsExistTClass(int tClassId, boolean state) {
        String sql = "select * from class where classID=? and state=?";
        Object arr[] = new Object[2];
        arr[0] = tClassId;
        arr[1] = state;
        // arr[2] = courseState;
        TClassDaoImpl tmp = new TClassDaoImpl();
        if (tmp.selectTClass(sql, arr).isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean IsExistTeacher(int teacherId, boolean state) {
        String sql = "select * from teacher where teacherID=? and state=?";
        Object arr[] = new Object[2];
        arr[0] = teacherId;
        arr[1] = state;
        // arr[2] = courseState;
        TeacherDaoImpl tmp = new TeacherDaoImpl();
        if (tmp.selectTeacher(sql, arr).isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public TClass queryTClassByTClassId(int tClassId, boolean state) {
        String sql = "select * from class where state=? and classID=?";
        TClassDaoImpl tmp = new TClassDaoImpl();
        Object arr[] = new Object[2];
        arr[1] = tClassId;
        arr[0] = state;
        return tmp.selectTClass(sql, arr).get(0);
    }

    @Override
    public TClass queryTClass(int courseId, int StudentId, boolean state) {
        String sql = "select * from (select * from class where courseID=? and state=?) as t where t.classID in (select g.classID from student nature join taking as g where g.studentID=?)";
        TClassDaoImpl tmp = new TClassDaoImpl();
        Object arr[] = new Object[3];
        arr[0] = courseId;
        arr[2] = StudentId;
        arr[1] = state;
        return tmp.selectTClass(sql, arr).get(0);
    }

    @Override
    public Teacher queryTeacherByTeacherId(int TeacherId, boolean state) {
        TeacherDaoImpl tmp = new TeacherDaoImpl();
        String sql = "select * from teacher where teacherID=? and state=?";
        Object arr[] = new Object[2];
        arr[0] = TeacherId;
        arr[1] = state;
        return tmp.selectTeacher(sql, arr).get(0);
    }

    @Override
    public ArrayList<TClass> queryTClassByTeacherId(int TeacherId, boolean state) {
        String sql = "select * from class where teacherID=? and state=?";
        TClassDaoImpl tmp = new TClassDaoImpl();
        Object arr[] = new Object[2];
        arr[0] = TeacherId;
        arr[1] = state;
        // arr[3]=state;
        return tmp.selectTClass(sql, arr);
    }

    @Override
    public boolean isExistStudent(int studentId, String password, boolean state) {
        String sql = "select * from student where StudentID=? and PASSWORD=? and state=?";
        Object arr[] = new Object[3];
        arr[0] = studentId;
        arr[1] = password;
        arr[2] = state;
        StudentDaoImpl tmp = new StudentDaoImpl();
        if (tmp.selectStudent(sql, arr).isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean isExistTeacher(int teacherId, String password, boolean state) {
        String sql = "select * from teacher where teacherID=? and PASSWORD=? and state=?";
        Object arr[] = new Object[3];
        arr[0] = teacherId;
        arr[1] = password;
        arr[2] = state;
        TeacherDaoImpl tmp = new TeacherDaoImpl();
        if (tmp.selectTeacher(sql, arr).isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean isExistManager(String managerId, String password) {
        String sql = "select * from Manager where managerID=? and password=?";
        Object arr[] = new Object[2];
        arr[0] = managerId;
        arr[1] = password;
        ManagerDaoImpl tmp = new ManagerDaoImpl();
        if (tmp.selectManager(sql, arr).isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public ArrayList<Course> queryCourseByStudentId(int studnetId, boolean courseState) {
        String sql = "select * from course where courseID in (select courseID from course natural join student where studentID = ?) and state = ?";
        Object arr[] = new Object[2];
        arr[0] = studnetId;
        arr[1] = courseState;
        CourseDaoImpl tmp = new CourseDaoImpl();
        return tmp.selectCourse(sql, arr);
    }

    @Override
    public ArrayList<Student> queryMaxStduentId() {
        String sql = "select * from student where studentID = (select max(studentID) from student)";
        StudentDaoImpl tmp = new StudentDaoImpl();
        return tmp.selectStudent(sql, null);
    }

    @Override
    public ArrayList<Teacher> queryMaxTeacherId() {
        String sql = "select * from teacher where teacherID = (select max(teacherID) from teacher)";
        TeacherDaoImpl tmp = new TeacherDaoImpl();
        return tmp.selectTeacher(sql, null);
    }

    @Override
    public ArrayList<Course> queryMaxCourseId() {
        String sql = "select * from course where courseID = (select max(courseID) from course)";
        CourseDaoImpl tmp = new CourseDaoImpl();
        return tmp.selectCourse(sql, null);
    }

    @Override
    public ArrayList<TClass> queryMaxTClassId() {
        String sql = "select * from class where classID = (select max(classID) from class)";
        TClassDaoImpl tmp = new TClassDaoImpl();
        return tmp.selectTClass(sql, null);
    }

}