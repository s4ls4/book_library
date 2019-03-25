//package Domain;
//
//
//import domain.Client;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static junit.framework.TestCase.assertEquals;
//
//public class ClientTest {
//    private static final Long ID = 1L;
//    private static final Long NEW_ID = 2L;
//    private static final String SERIAL_NUMBER = "sn01";
//    private static final String NEW_SERIAL_NUMBER = "sn02";
//    private static final String NAME = "name";
//    private static final String NEW_NAME = "new_name";
//    private static final int SPENT = 123;
//    private static final int NEW_SPENT = 124;
//
//    private Client client;
//
//    @Before
//    public void setUp() throws Exception {
//        client = new Client(SERIAL_NUMBER,NAME,SPENT);
//        this.client.setId(ID);
//    }
//
//    @After
//    public void tearDown() throws Exception{
//        client = null;
//    }
//
//    @Test
//    public void testGetSerialNumaber() throws Exception{
//        assertEquals("Serial numbers should be equal",SERIAL_NUMBER,client.getSerialNumber());
//    }
//
//    @Test
//    public void testSetSerialNumber() throws Exception{
//        client.setSerialNumber(NEW_SERIAL_NUMBER);
//        assertEquals("Serial numbers should be equal",NEW_SERIAL_NUMBER,client.getSerialNumber());
//    }
//
//    @Test
//    public void testGetName() throws Exception {
//        assertEquals("Names should be equal", NAME, client.getName());
//    }
//
//    @Test
//    public void testSetName() throws Exception {
//        client.setName(NEW_NAME);
//        assertEquals("Name should be equal", NEW_NAME,client.getName());
//    }
//
//    @Test
//    public void testGetSpent() throws Exception {
//        assertEquals("spent amount should be equal", SPENT, client.getSpent());
//    }
//
//    @Test
//    public void testSetPrice() throws Exception {
//        client.setSpent(NEW_SPENT);
//        assertEquals("Spent amount should be equal", NEW_SPENT,client.getSpent());
//    }
//
//    @Test
//    public void testGetID() throws Exception {
//        assertEquals("IDs should be equal", ID, client.getId());
//    }
//
//    @Test
//    public void testSetId() throws Exception {
//        client.setId(NEW_ID);
//        assertEquals("IDs should be equal", NEW_ID,client.getId());
//    }
//}
