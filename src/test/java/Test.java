import com.example.demo.DemoApplication;
import com.example.demo.admin.author.domain.Author;
import com.example.demo.admin.author.mapper.AuthorMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author CTL
 * <p>测试类 </p>
 * 创建日期：2021-06-16 09:06
 */
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class Test {
    @Resource
    private AuthorMapper mapper;

    @org.junit.Test
    public void test() throws IOException{
        List<Author> list = mapper.getAll();
        for(Author ll:list){
            System.out.println("ll"+ll);
        }
    }
}

