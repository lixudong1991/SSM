package service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.lxd.Student;
import dao.Studendao;
import service.Studentservice;
@Service
public class Studentserviceimpl implements Studentservice {
	@Resource
	private Studendao stdao;
	@Override
	public Student findsbyid(int id) throws Exception {
		// TODO Auto-generated method stub
		return stdao.findsbyid(id);
	}

	@Override
	public Student findsbyname(String name) throws Exception {
		// TODO Auto-generated method stub
		return stdao.findsbyname(name);
	}

	@Override
	public List<Student> findas() throws Exception {
		// TODO Auto-generated method stub
		return stdao.findas();
	}

	@Override
	public void inserts(Student st) throws Exception {
		// TODO Auto-generated method stub
			 stdao.inserts(st);
	}

	@Override
	public void deletes(int id) throws Exception {
		// TODO Auto-generated method stub
	  stdao.deletes(id);
	}

	@Override
	public void updates(Student st) {
		// TODO Auto-generated method stub
		
	}

}
