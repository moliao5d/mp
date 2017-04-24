import com.jichong.dao.IOfficeAccountDAO;
import com.jichong.dao.impl.OfficeAccountDAO;
import com.jichong.domain.OfficeAccount;
import com.jichong.util.Constants;
import com.jichong.util.Page;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Test;

/**
 * Created on 2017/4/23.
 */
public class FooTest {

    @Test
    public void test001() throws Exception {

        System.out.println(Constants.MongoHost);
        System.out.println(Constants.MongoPort);


    }

    @Test
    public void testSave() throws Exception {
        Class.forName("com.jichong.util.Constants");

        OfficeAccount oa=new OfficeAccount();
        oa.setAesKey("xx");
        oa.setAppid("apppppp");
        oa.setAppSecret("ssssssssssss");
        oa.setOriginal_id("ghgh");
        oa.setStatus(2);
        oa.setToken("token");
        OfficeAccountDAO dao=new OfficeAccountDAO();
        String save = dao.save(oa);
        System.out.println(save);

    }


    @Test
    public void testObj2Doc() throws Exception {
        OfficeAccount oa=new OfficeAccount();
        oa.setAesKey("xx");
        oa.setAppid("apppppp");
        oa.setAppSecret("ssssssssssss");
        oa.setOriginal_id("ghgh");
        oa.setStatus(2);
        oa.setToken("token");
        System.out.println(oa.toDoc(false));
    }

    @Test
    public void fDoc() throws Exception {


        OfficeAccount oa=new OfficeAccount();
        oa.setAesKey("xx");
        oa.setAppid(null);
        oa.setAppSecret("ssssssssssss");
        oa.setOriginal_id("ghgh");
        oa.setStatus(2);
        oa.setToken("token");
        Document d=oa.toDoc(false);

        Document doc = new Document();
        doc.put("_id","x333");
        doc.put("status",99);
        doc.put("xo",new Document("_id","x00000"));
        OfficeAccount officeAccount = new OfficeAccount();
        System.out.println(officeAccount.doc2Domain(d));
    }

    @Test
    public void testgetPage() throws Exception {
        IOfficeAccountDAO dao = new OfficeAccountDAO();
        Page page = dao.getPage(Filters.exists("_id"), 1, 10);
        System.out.println(page.getListData());

    }

    @Test
    public void testdel() throws Exception {
        IOfficeAccountDAO dao = new OfficeAccountDAO();
        OfficeAccount a = new OfficeAccount();
        a.setAesKey("xxx222");
        a.setOriginal_id("ooooooooooo");
        dao.updateById("58fdc4e516ccdd73dcb902cb",a);       // dffff
    }


}
