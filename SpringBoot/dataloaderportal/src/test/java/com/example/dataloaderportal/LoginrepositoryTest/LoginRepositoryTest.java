package com.example.dataloaderportal.LoginrepositoryTest;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;




import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;



import com.example.dataloaderportal.Logindomain.Login;
import com.example.dataloaderportal.Loginrepository.LoginRepository;



@SpringBootTest
public class LoginRepositoryTest {
@MockBean
LoginRepository repo;
private Login login=new Login();
//@Test
//void repoTest() {
//    login.setPassword("agarwal@123");
//    login.setUsername("vidur@gmail.com");
//    when(repo.findByUsername("vidur@gmail.com")).thenReturn(login);
//    assertThat(login.getPassword()).isEqualTo("agarwal@123");
//}
}
 