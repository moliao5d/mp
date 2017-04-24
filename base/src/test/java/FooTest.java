import com.jichong.dao.impl.OfficeAccountDAOImpl;
import com.jichong.domain.OfficeAccount;
import org.junit.Test;

/**
 * Created on 2017/4/23.
 */
public class FooTest {

    @Test
    public void test001() throws Exception {

//        System.out.println(Constants.MongoHost);
//        System.out.println(Constants.MongoPort);


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
        OfficeAccountDAOImpl dao=new OfficeAccountDAOImpl();
        String save = dao.save(oa);
        System.out.println(save);

    }
}
