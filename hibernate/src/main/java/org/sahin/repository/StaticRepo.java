package org.sahin.repository;

import javax.persistence.EntityManager;

import org.sahin.persistence.EntityManagerDao;

public class StaticRepo {
	public static EntityManagerDao entityManagerDao;
	public static  EntityManager entityManager;
}
