package test;

import com.cookabuy.entity.po.Prop;
import com.cookabuy.repository.PropRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 2016/12/7
 */
public class TestPropRepository extends AbstractJpaTest{
    @Autowired
    private PropRepository propRepository;
    @Test
    public void test(){
        Prop prop  = new Prop();
        prop.setPid(20609l);
        prop.setCid(50008900l);
        prop.setName("helloworld");
        prop.setKeyProp(false);
        prop.setSaleProp(true);
        prop.setColorProp(false);
        prop.setEnumProp(false);
        prop.setItemProp(false);
        prop.setInputProp(false);
        propRepository.save(prop);
    }
}
