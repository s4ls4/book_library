//package Domain;
//
//
//import domain.Book;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static junit.framework.TestCase.assertEquals;
//
//public class BookTest {
//    private static final Long ID = 1L;
//    private static final Long NEW_ID = 2L;
//    private static final String SERIAL_NUMBER = "sn01";
//    private static final String NEW_SERIAL_NUMBER = "sn02";
//    private static final String AUTHOR = "authorName";
//    private static final String NEW_AUTHOR = "new_authorName";
//    private static final String NAME = "name";
//    private static final String NEW_NAME = "new_name";
//    private static final int PRICE = 123;
//    private static final int NEW_PRICE = 124;
//
//    private Book book;
//
//    @Before
//    public void setUp() throws Exception {
//        book = new Book(SERIAL_NUMBER,NAME,AUTHOR,PRICE);
//        this.book.setId(ID);
//    }
//
//    @After
//    public void tearDown() throws Exception{
//        book = null;
//    }
//
//    @Test
//    public void testGetSerialNumaber() throws Exception{
//        assertEquals("Serial numbers should be equal",SERIAL_NUMBER,book.getSerialNumber());
//    }
//
//    @Test
//    public void testSetSerialNumber() throws Exception{
//        book.setSerialNumber(NEW_SERIAL_NUMBER);
//        assertEquals("Serial numbers should be equal",NEW_SERIAL_NUMBER,book.getSerialNumber());
//    }
//
//    @Test
//    public void testGetName() throws Exception {
//        assertEquals("Names should be equal", NAME, book.getName());
//    }
//
//    @Test
//    public void testSetName() throws Exception {
//        book.setName(NEW_NAME);
//        assertEquals("Name should be equal", NEW_NAME,book.getName());
//    }
//
//    @Test
//    public void testGetAuthor() throws Exception {
//        assertEquals("Authors should be equal", AUTHOR, book.getAuthor());
//    }
//
//    @Test
//    public void testSetAuthor() throws Exception {
//        book.setAuthor(NEW_AUTHOR);
//        assertEquals("Author should be equal", NEW_AUTHOR,book.getAuthor());
//    }
//
//    @Test
//    public void testGetPrice() throws Exception {
//        assertEquals("Prices should be equal", PRICE, book.getPrice());
//    }
//
//    @Test
//    public void testSetPrice() throws Exception {
//        book.setPrice(NEW_PRICE);
//        assertEquals("Price should be equal", NEW_PRICE,book.getPrice());
//    }
//
//    @Test
//    public void testGetID() throws Exception {
//        assertEquals("IDs should be equal", ID, book.getId());
//    }
//
//    @Test
//    public void testSetId() throws Exception {
//        book.setId(NEW_ID);
//        assertEquals("IDs should be equal", NEW_ID,book.getId());
//    }
//}
