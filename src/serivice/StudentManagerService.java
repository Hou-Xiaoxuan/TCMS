/*
 * @Author: xv_rong
 * @Date: 2021-07-06 15:10:26
 * @LastEditors: xv_rong
 * @LastEditTime: 2021-07-10 20:26:59
 * @Description: Student Manager method
 * @FilePath: \TCMS\src\serivice\StudentManagerService.java
 */
package serivice;

/**
 * 管理学生的方法
 */
public interface StudentManagerService {
    /**
     * @description: show all student infomation, ID + name
     * @param none
     * @return none
     */
    void showAllStudent();

    /**
     * @description: show all student infomation in a class, ID + name
     * @param none
     * @return none
     */
    void showCertainClassStudent();

    /**
     * @description: show all student infomation in a teacher, ID + name
     * @param none
     * @return none
     */
    void showCertainTeacherStudent();

    /**
     * @description: show Student all fromation
     * @param none
     * @return none
     */
    void showCertainStudent();

    /**
     * @description: add a student
     * @param none
     * @return none
     */
    void addStudent();

    /**
     * @description:
     * @param none
     * @return none
     */
    void deleteStudent();

    /**
     * @description: changeStudentclass
     * @param none
     * @return none
     */
    void changeStudentClass();

    /**
     * @description: Show Certain student now class
     * @param none
     * @return none
     */
    void showCertainStudentNowClass();

    /**
     * @description: Show Certain student history class
     * @param none
     * @return none
     */
    void showCertainStudentHistoryClass();
}
