package com.Kimalu.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TeacherTest {
	public static SessionFactory sf = null;

	@BeforeClass
	public static void bc() {
		// sf = new Configuration().configure().buildSessionFactory();
		try {
			sf = new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSchemaExport() {
		new SchemaExport(new AnnotationConfiguration().configure()).create(
				false, true);

	}

	@Test
	public void testSave() {
		Person p = new Person();
		p.setName("Kimalu");
		Team t = new Team();
		t.setName("Kteam");
		p.setTeam(t);
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();

	}

	@Test
	public void testSave2() {
		Person p1 = new Person();
		p1.setName("Kimalu");
		Person p2 = new Person();
		p2.setName("Zoie");
		Set<Person> persons = new HashSet<Person>();
		persons.add(p1);
		persons.add(p2);
		Team t = new Team();
		t.setName("Kteam");
		t.setPersonSet(persons);
		p1.setTeam(t);
		p2.setTeam(t);
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();

	}

	@Test
	public void testRead() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Person p = (Person) session.get(Person.class, 5);
		// System.out.println(p.getTeam().getName());
		session.getTransaction().commit();
	}

	@Test
	public void testRead2() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		// Person p=(Person)session.get(Person.class, 5);
		Team t = (Team) session.get(Team.class, 4);
		// for(Iterator<Person> i=t.getPersonSet().iterator();i.hasNext();){
		// System.out.println(i.next().getName());
		// }
		session.getTransaction().commit();
	}

	@Test
	public void testUpdate() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Person p = (Person) session.get(Person.class, 5);
		p.setName("aaa");
		p.getTeam().setName("aaa");
		session.getTransaction().commit();
	}

	@Test
	public void testUpdate1() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Person p = (Person) session.get(Person.class, 5);
		p.setName("bbb");
		p.getTeam().setName("bbb");
		session.update(p);
		session.getTransaction().commit();
	}

	@Test
	public void testUpdate2() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Person p = (Person) session.get(Person.class, 5);
		session.getTransaction().commit();
		p.setName("aaa");
		p.getTeam().setName("aaa");
		Session session2 = sf.getCurrentSession();
		session2.beginTransaction();
		session2.update(p);
		session2.getTransaction().commit();
	}

	@Test
	public void testUpdate3() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Team t = (Team) session.get(Team.class, 4);
		t.setName("ccc");
		for (Iterator<Person> i = t.getPersonSet().iterator(); i.hasNext();) {
			i.next().setName("tttt");
		}
		session.getTransaction().commit();

	}

	@Test
	public void testUpdate4() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Person p = (Person) session.get(Person.class, 5);
		session.getTransaction().commit();
		p.setName("aaa");
		p.getTeam().setName("aaa");
		Session session2 = sf.getCurrentSession();
		session2.beginTransaction();
		session2.update(p);
		session2.getTransaction().commit();
	}
	 
	@Test
	public void testDel() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Person p = (Person) session.get(Person.class, 6);
		session.delete(p);
		session.getTransaction().commit();
	}

	@Test
	public void testDel2() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Team t=(Team)session.get(Team.class, 5);
		session.delete(t);
		session.getTransaction().commit();
	}
	
	@Test
	public void testDel3() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Person p = (Person) session.get(Person.class, 2);
		p.setTeam(null);
		session.delete(p);
		session.getTransaction().commit();
	}
	@AfterClass
	public static void ac() {
		sf.close();
	}
}
