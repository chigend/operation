package test;

import com.cookabuy.repository.service.ItemRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.data.jpa.domain.Specifications.*;
import static com.cookabuy.repository.service.specification.ItemSpecifications.*;

/**
 * 2016/12/7
 */
public class TestItemRepository extends AbstractJpaTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testJoinStore(){
       String storeName =  itemRepository.findOne(541970543672l).getStore().getStoreName();
        System.out.print(storeName);
    }

    @Test
    public void testFindByMarketName(){
        String marketName = "大西豪";
        int size = itemRepository.findAll(where(findByMarket(marketName)).and(findByCategoryId(50008898l))).size();
        System.out.println(size);
    }
}
