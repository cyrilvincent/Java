package formationJEE.controllers;

import formationJEE.adapters.BookViewModelAdapter;
import formationJEE.entities.Book;
import formationJEE.repositories.IRepository;
import formationJEE.services.BookService;
import formationJEE.services.IService;
import formationJEE.viewModels.BookViewModel;

//Limitation Java 7 : public class BookController extends AbstractController<Book,BookViewModel,BookAdapter,BookService> {
public class BookController extends AbstractController<Book,BookViewModel,BookViewModelAdapter,IService<Book,IRepository<Book>>> {

	public BookService getBookService() {
		// Limitation Java 7
		return (BookService)(Object)service;
	}
	
	
}
