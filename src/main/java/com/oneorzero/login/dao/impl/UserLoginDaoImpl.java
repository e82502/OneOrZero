package com.oneorzero.login.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oneorzero.bean.MemberBean;
import com.oneorzero.login.dao.IUserLoginDao;
@Repository
public class UserLoginDaoImpl implements IUserLoginDao {
	
	@Autowired
	SessionFactory factory;
	
	@Override
	public MemberBean checkAccountPassword(String account, String password) {
		Session session = null;
		MemberBean member = null;
		String hql = "FROM MemberBean WHERE email = :email and password = :password";
		session = factory.getCurrentSession();
		try {
			member = (MemberBean) session.createQuery(hql)
					.setParameter("email", account)
					.setParameter("password", password)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
		return member;
	}
}
