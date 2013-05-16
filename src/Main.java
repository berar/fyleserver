

import org.fyleserver.db.HibernateUtil;
import org.fyleserver.db.entity.Friend;
import org.fyleserver.db.entity.User;
import org.fyleserver.db.entity.UserSecurity;
import org.fyleserver.presenter.impl.PresenterImpl;
import org.fyleserver.server.Server;
import org.hibernate.Session;


public class Main {
	public static void main(String[] args) {
		Server server = new Server(6789);
		server.run();
		PresenterImpl pres = new PresenterImpl(server);
//		System.out.println("Maven + Hibernate + Oracle");
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		java.util.Date dt = new java.util.Date();
//		java.text.SimpleDateFormat sdf = 
//		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String currentTime = sdf.format(dt);
//		
//		session.beginTransaction();
//		
//		UserSecurity userSecurity = new UserSecurity();
//		User user = new User();
//		user.setUsername("aca");
//		user.setPassword("asdasd");
//		user.setRegistrationDate(currentTime);
//		user.setUserSecurity(userSecurity);
//		userSecurity.setUser(user);
//		session.save(user);
//		
//		Friend friend = new Friend();
//		friend.setUsername("acko");
//		friend.setUser(user);
//		
//		Friend friend2 = new Friend();
//		friend2.setUsername("acko2");
//		friend2.setUser(user);
//		
//		user.getFriends().add(friend);
//		user.getFriends().add(friend2);
//		session.save(friend2);
//		session.save(friend);
//		
//		session.getTransaction().commit();
//		System.out.println("asdf");
	}
}
