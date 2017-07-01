package service;

import java.util.List;

import cn.lxd.Student;

public interface Studentservice {
	public Student findsbyid(int id)throws Exception;
	public Student findsbyname(String name)throws Exception;
	public List<Student> findas()throws Exception;
	public void inserts(Student st)throws Exception;
	public void deletes(int id)throws Exception;
	public void updates(Student st)throws Exception;
}
