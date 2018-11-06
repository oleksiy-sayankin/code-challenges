package net.javacogito.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation") public final class HibernateUntil {
  private static final SessionFactory sessionFactory;

  static {
    try {
      sessionFactory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable e) {
      System.out.println("Session factory could not be created" + e);
      throw new ExceptionInInitializerError(e);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}