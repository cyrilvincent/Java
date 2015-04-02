package formationJEE.controllers;

import formationJEE.adapters.BookTOAdapter;
import formationJEE.entities.Book;
import formationJEE.repositories.IRepository;
import formationJEE.services.IService;
import formationJEE.transportObjects.BookTO;

public class BookTOController extends AbstractController<Book,BookTO,BookTOAdapter,IService<Book,IRepository<Book>>> {

}
