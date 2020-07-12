/**
 * @Project ego-parent
 * @Package PACKAGE_NAME
 * @author Administrator
 * @date 2020/7/12 21:19
 * @version V1.0
 */

import com.ego.common.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.dubbo.service.impl.TbItemDubboServiceImpl;
import org.junit.Test;

/**
 * @author Administrator
 * @ClassName TestDao
 * @Description 测试
 * @date 2020/7/12 21:19
 **/

public class TestDao {


    private TbItemDubboService tbItemDubboServiceImpl = new TbItemDubboServiceImpl();


    @Test
    public void showItemTest() {
        EasyUIDataGrid dataGrid = tbItemDubboServiceImpl.showByPage(1, 2);
        System.out.println(dataGrid.getRows());
    }

}
