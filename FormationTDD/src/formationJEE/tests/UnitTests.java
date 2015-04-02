package formationJEE.tests;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

import org.easymock.EasyMock;
import org.junit.*;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import formationJEE.entities.*;
import formationJEE.ioc.Spring;
import formationJEE.repositories.*;
import formationJEE.services.*;
import formationJEE.stubs.*;

public class UnitTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void FirstTest() {
		Book b = new Book();
		b.setPrice(new BigDecimal(10));
		Assert.assertEquals(new BigDecimal(10), b.getPrice());
		
	}
	
	@Test
	public void StubTest() {
		BookRepositoryStub stub = new BookRepositoryStub();
		stub.setEntityManager(new EntityManagerStub());
		Book b = stub.getById(0);
		Assert.assertNull(b);
	}
	
	@Test
	public void MockTest() {
		EntityManager em = EasyMock.createMock(EntityManager.class);
		Assert.assertNotNull(em);
	}
	
	@Test
	public void MockExpectTest() {
		EntityManager em = EasyMock.createMock(EntityManager.class);
		Book b = new Book();
		b.setId(1);
		EasyMock.expect(em.find(Book.class, 1)).andReturn(b);
		EasyMock.replay(em);
		b = em.find(Book.class, 1);
		Assert.assertEquals(1,b.getId());
	}
	
	@Test
	public void MockRepositoryTest() {
		EntityManager em = EasyMock.createMock(EntityManager.class);
		Book b = new Book();
		b.setId(1);
		EasyMock.expect(em.find(Book.class, 1)).andReturn(b);
		EasyMock.replay(em);
		BookRepository repo = new BookRepository();
		repo.setEntityManager(em);
		b = repo.getById(1);
		Assert.assertEquals(1,b.getId());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void MockIRepositoryTest() {
		EntityManager em = EasyMock.createMock(EntityManager.class);
		IRepository<Book> repo = EasyMock.createMock(IRepository.class);
		repo.setEntityManager(em);
		Assert.assertNotNull(repo);
	}

	@Test
	public void FindTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormationJPA");
		EntityManager em = emf.createEntityManager();
		Book b = em.find(Book.class,1);
		Assert.assertNotNull(b);
		Assert.assertEquals(1, b.getId());		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void InsertTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormationJPA");
		EntityManager em = emf.createEntityManager();
		List<Book> l = em.createQuery("select b from Book b").getResultList();
		int size = l.size();
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
		Book b = new Book();
    	b.setTitle("JPA");
    	b.setPrice(BigDecimal.valueOf(9.9));
    	em.persist(b);
    	tx.commit();
    	l = em.createQuery("select b from Book b").getResultList();
    	Assert.assertEquals(size + 1, l.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void RemoveTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormationJPA");
		EntityManager em = emf.createEntityManager();
		List<Book> l = em.createQuery("select b from Book b").getResultList();
		int size = l.size();
		Book b = l.get(l.size()-1);
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
    	em.remove(b);
    	tx.commit();
    	l = em.createQuery("select b from Book b").getResultList();
    	Assert.assertEquals(size - 1, l.size());
	}
	
	@Test
	public void UpdateTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormationJPA");
		EntityManager em = emf.createEntityManager();
		Book b = em.find(Book.class,1);
		BigDecimal price = b.getPrice();
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
    	b.setPrice(price.add(BigDecimal.ONE));
    	em.persist(b);
    	tx.commit();
    	b = em.find(Book.class,1);
    	Assert.assertEquals(price.add(BigDecimal.ONE),b.getPrice());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void JPQLTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormationJPA");
		EntityManager em = emf.createEntityManager();
		Book b = em.find(Book.class,1);
		List<Book> l = em.createQuery("select b from Book b where b.id = 1").getResultList();
		Book b2 = l.get(0);
		Assert.assertEquals(b, b2);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void NamedQueryTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormationJPA");
		EntityManager em = emf.createEntityManager();
		List<Book> l = em.createNamedQuery("Book.findAll").getResultList();
		List<Book> l2 = em.createQuery("select b from Book b").getResultList();
		Assert.assertEquals(l.size(), l2.size());
	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void NativeQueryTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormationJPA");
		EntityManager em = emf.createEntityManager();
		List<Book> l = em.createNativeQuery("select * from Book").getResultList();
		List<Book> l2 = em.createQuery("select b from Book b").getResultList();
		Assert.assertEquals(l.size(), l2.size());
	
	}
	
	@Test
	public void OneToManyTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("FormationJPA");
		EntityManager em = emf.createEntityManager();
		Book b = em.find(Book.class,1);
		Publisher p = new Publisher();
		p.setName("Cyril");
		b.setPublisher(p);
		EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(p);
	    em.persist(b);
	    tx.commit();
	    p = b.getPublisher();
	    Assert.assertNotNull(p);
	    tx.begin();
	    b.setPublisher(null);
	    em.remove(p);
	    tx.commit();
	    Assert.assertNull(b.getPublisher());
	}
	
	@Test
	public void EntityManagerSingletonTest() {
		EntityManager em = EntityManagerFactorySingleton.getEntityManager();
		Assert.assertNotNull(em);
	}
	
	@Test
	public void RepositoryGetTest() {
		EntityManager em = EntityManagerFactorySingleton.getEntityManager();
		BookRepository r = new BookRepository();
		r.setEntityManager(em);
		Book b = r.getById(1);
		Assert.assertNotNull(b);
		List<Book> l = r.getAll();
		Assert.assertTrue(l.size() > 0);	
	}
	
	@SuppressWarnings("unused")
	@Test
	public void BookRepositoryTest() {
		BookRepository r = new BookRepository();
		r.setEntityManager(EntityManagerFactorySingleton.getEntityManager());
		List<Book> l1 = r.getByPrice(BigDecimal.valueOf(10));
	}
	
	@Test
	public void RepositorySetTest() {
		BookRepository r = new BookRepository();
		r.setEntityManager(EntityManagerFactorySingleton.getEntityManager());
		Book b = new Book();
    	b.setTitle("Design Patterns");
    	b.setPrice(BigDecimal.valueOf(19.9));
		r.save(b);
		r.commit();
		Publisher p = new Publisher();
		p.setName("Cyril");
		b.setPublisher(p);
		PublisherRepository pr = new PublisherRepository();
		pr.setEntityManager(EntityManagerFactorySingleton.getEntityManager());
		pr.save(p);
		r.save(b);
		r.commit();
		pr.remove(p);
		r.remove(b);
		r.commit();
	}
	
	@Test
	public void SpringFactoryTest() {
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
		Book b = (Book)factory.getBean("Book");	
		Assert.assertNotNull(b);
	}
	
	@Test
	public void SpringBeanTest() {
		Book b = Spring.getBean(Book.class);
		Assert.assertNotNull(b);
	}
	
	@Test
	public void SpringEntityManagerFactoryTest() {
		EntityManagerFactory emf = Spring.getBean(EntityManagerFactory.class);
		Assert.assertNotNull(emf);
		EntityManager em = Spring.getBean(EntityManager.class);
		Assert.assertNotNull(em);
	}
	
	@Test
	public void SpringIoDTest() {
		BookRepository r = Spring.getBean(BookRepository.class);
		Assert.assertNotNull(r.getEntityManager());
		Book b = r.getById(1);
		Assert.assertEquals(1, b.getId());
	}
	
	@Test
	public void ServiceTest() {
		BookService s = Spring.getBean(BookService.class);
		Assert.assertNotNull(s);
		Assert.assertNotNull(s.getRepository());
		Assert.assertNotNull(s.getRepository().getEntityManager());
		Book b = s.getById(1);
		Assert.assertEquals(1, b.getId());
	}
	
	@Test
	public void SpringMockTest() {
		EntityManager em = (EntityManager) Spring.getBean("EntityManagerMock");
		Assert.assertNotNull(em);
	}
	
	@Test
	public void SpringMockRepositoryTest() {
		BookRepository r = (BookRepository) Spring.getBean("BookRepositoryMock");
		Assert.assertNotNull(r.getEntityManager());
	}
}
