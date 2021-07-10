# 不同Window的功能设计

## 登录

1. 学生
2. 老师
3. 管理员

## 教师

> 一个老师能教多门课

1. 个人信息

   > Teacher.showInfo()
   >
   > 打印 自己的ID号 姓名，性别，年龄，工资

2. 查询现在自己所教的所有班级

   > 格式 学历 年级 课程名 

3. 查询某门自己所教课程的所有学生

   > CourseManager中，(TeacherID) -\>ArrayList\<\<name, studentID\>\> 
   >
   > 打印 姓名 学号

4. 查询教过的历史班级(是否实现待定)

   > TeacherManager中
   >
   > 格式 学历 年级 课程名 年份

5. 查询单独一个学生的信息

   > (StudentID) -\> void 打印处学生的信息；对于非自己的学生，返回报错。
   >
   > 打印学号，姓名，性别，年龄

## 学生

1. 个人信息

   > Student.showInfo()实现
   >
   > 打印
   >
   > 学生ID，姓名，性别，年龄

2. 查询自己的班级

   > StudentManager中，(StudentID) -\>ArrayList\<Course> 
   >
   > 输入学生ID，打印学生的课程及对应班级列表
   >
   > 格式 学历 年级 课程名

   

3. 查询自己的历史班级

   > 格式 学历 年级 课程名 年份

4. 选课

   > 实现一个小windows，实现增加当前课程的操作
   >
   > 1. 根据Education打印所有可选课程及对应班级（去除自己已选，没有开设班级的，或者所有班级都满的）
   > 2. 根据序号选择课程及班级
   > 3. 选择课程后调用Manager方法写入数据库

## 管理员

实现三个不同的功能模块：

- 老师管理
- 学生管理
- 课程管理
- 单独功能：结束本学期

### 学生管理

1. 查询所有在读学生

   > 打印在读的所有
   >
   > 学号 姓名
   >
   > 调用数据库接口 无参数 返回一个 学号 姓名的列表
   >
   > 调用打印函数 参数为 学号（string）姓名（string）对的列表 打印出来

2. 根据当年的学历+课程+班级查询所有的的学生

   > 学号 姓名
   >
   > 调用一个选择学历的函数，无输入，输出为一个学历枚举量（Education）
   >
   > 调用一个选择课程的函数，输入为学历枚举量（Education），输出为课程名列表（string）
   >
   > 调用一个选择班级的函数，输入为一个学历枚举量（Education）+课程名（string），输出为班级序号列表（int）
   >
   > 调用数据库接口 输入为 学历 （Education）课程名（string） 班级 （int）返回一个学生 学号 （string）和 姓名 的列表
   >
   > 调用打印函数 参数为 学号（string）姓名（string）对的列表 打印出来

3. 根据老师查询学生

   > 调用一个输入老师工号函数，无输入，输出为老师工号（String）
   >
   > 调用一个查询学生的函数，输入为老师工号（String），输出为 学号（string）姓名（string）对的列表
   >
   > 调用打印函数 参数为 学号（string）姓名（string）对的列表 打印出来

4. 查询单个学生信息详细信息

   > 函数 输入学号 输出 学号（String） 姓名（String） 性别（String） 年龄（int）

   1. 查询一个学生的当前课程

   >  格式 学历 年级 课程名

6. 查询一个学生的历史课程

   > 格式 学历 年级 课程名 年份

7. 增加学生

   > 输入姓名，性别，年龄
   >
   > 自动生成学号ID 格式参考我们的学号

8. 开除学生

   > 输入学号，
   >
   > 需要保存所有数据
   >
   > 将正在进行的课程状态置为未完成

9. 调整学生班级

   > 策略 
   >
   > 1. 如果这个学生有相同的课程，则删除原来的班级信息，课程状态置为未完成，调入新的班级
   > 2. 如果这个学生没有此课程，则直接调入先班级

### 老师管理

1. 查询所有老师

   > 工号 + 姓名

2. 根据学历+课程+班级查询任课老师

   > 工号 + 姓名

3. 查询单个老师的详细信息

   > 打印 自己的ID号 姓名，性别，年龄，工资

4. 查询单个老师所有的当前任教班级

   > 格式 学历 年级 课程名 班级号

5. 查询单个老师所有的历史任教班级

   > 格式 学历 年级 课程名  班级号年份

6. 增加老师

   > 姓名，性别，年龄，工资
   >
   > 自动生成工号ID 格式参考我们的学号

7. 删除老师

   > 老师有任教课程时不可删除

8. 删除任教班级

   > 输入工号，打印所有老师教的班级
   >
   > 输入序号删除

9. 选择任教班级

   > 输入老师工号
   >
   > 打印所有没有老师的班级列表
   >
   > 输入序号选择

8. 调整老师工资

   输入工号，打印当前工资，输入需求工资

### 课程管理

1. 查询当前所有课程

   > 课程名

2. 查询特定课程所有班级

   > 选择学历 
   >
   > 打印所有课程
   >
   > 选择课程序号
   >
   > 输出 年级 班级

3. 增加课程

   > 选择学历
   >
   > 输入课程名
   >
   > > 考虑同名处理

4. 删除课程

   > 选择学历 
   >
   > 打印所有课程
   >
   > 选择课程序号
   >
   > 只有没有班级的课程才能被删除

5. 更改课程价格

   > 选择学历 
   >
   > 打印所有课程
   >
   > 选择课程序号
   >
   > 打印现在的课程价格
   >
   > 输入需求价格

### 班级管理

> 一个 班级只能有一个老师，也可能没有

1. 查询当前所有班级

   > 学历 年级 课程名 班级 任教老师

2. 查询某一班级的所有学生

   > 选择 学历 课程名 年级 班级
   >
   > 姓名 学号

3. 查询某一班级的老师

   > 输入老师工号
   >
   > 打印 学历 年级 课程名 班级 

4. 增加班级

   > 选择学历，课程， 年级
   >
   > 自动增加班级序号

5. 删除班级

   >  选择学历，课程， 年级，班级
   >
   > 如果有学生，则无法删除

6. 更改某一班级的老师

   >  选择学历，课程， 年级，班级
   >
   > 打印当前老师
   >
   > ​	无则打印无老师
   >
   > 打印所有老师 
   >
   > 选择老师

7. 删除某一班级内的某一名学生

   > 选择 学历 课程名 年级 班级 打印所有学生
   >
   > 选择学生删除

8. 增加某一班级内的学生

   > 选择 学历 课程名 年级 班级 
   >
   > 输入学生学号
   >
   > ​	注意进行学号检查
