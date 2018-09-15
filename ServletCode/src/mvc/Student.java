package mvc;
/**
 * 完成单表的增删改查操作
 * 要求：
 * 	1) 各个增删改查功能相互独立，不用考虑把他们一下子整合到一起
 * 	2) 修改流程分为两步：
 * 		a. 根据编号查询出旧的数据，显示在表单中
 * 		b. 表单中添加修改后的值，完成更新
 * 	3) 删除流程，根据编号进行删除，删除完毕后跳转到一个jsp显示删除成功
 * 	4) 添加流程，展示添加表单，提交后到servlet执行添加，添加完毕后跳转至jsp显示添加成功
 * 	5) 查询功能可以在查询所有的基础上思考如何根据一个条件进行查询，例如部门名称
 * 	6) 修改和添加时进行必要的验证，防止无效数据加入数据库
 */

import java.util.Date;

public class Student {
    private int sid;
    private String sname;
    private Date birthday;
    private String sex;

    public Student(int sid, String sname, Date birthday, String sex) {
        this.sid = sid;
        this.sname = sname;
        this.birthday = birthday;
        this.sex = sex;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
