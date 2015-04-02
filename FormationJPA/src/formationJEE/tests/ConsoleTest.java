package formationJEE.tests;

import javax.persistence.*;

import formationJEE.entities.*;

public class ConsoleTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormationJPA");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
		Book b = em.find(Book.class,1);
		System.out.println(b.getTitle());
	}

}
