package com.tgyt.common.tools.core;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class TgOpenSessionInViewFilter extends OpenSessionInViewFilter {

	/* (non-Javadoc)
	 * <p>Title: closeSession</p> 
	 * <p>Description: </p> 
	 * @param session
	 * @param sessionFactory 
	 * @see org.springframework.orm.hibernate3.support.OpenSessionInViewFilter#closeSession(org.hibernate.Session, org.hibernate.SessionFactory) 
	 */
	
	@Override
	protected void closeSession(Session session, SessionFactory sessionFactory) {
		// TODO Auto-generated method stub
	       session.setFlushMode(FlushMode.NEVER); 
		super.closeSession(session, sessionFactory);
	}

	/* (non-Javadoc)
	 * <p>Title: getSession</p> 
	 * <p>Description: </p> 
	 * @param sessionFactory
	 * @return
	 * @throws DataAccessResourceFailureException 
	 * @see org.springframework.orm.hibernate3.support.OpenSessionInViewFilter#getSession(org.hibernate.SessionFactory) 
	 */
	
	@Override
	protected Session getSession(SessionFactory sessionFactory)
			throws DataAccessResourceFailureException {
		Session session = SessionFactoryUtils.getSession(sessionFactory, true); 
	       session.setFlushMode(FlushMode.AUTO); 
	       return session; 
	}

}
