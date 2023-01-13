package com.example.dataloaderportal.LoginDomainTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import com.example.dataloaderportal.Logindomain.Login;



@SpringBootTest
public class LoginTest {



 Login login=new Login();
 @Test
 public void testLogin() {
 login.setPassword("agarwal@123");
 login.setUsername("vidur@gmail.com");
 assertThat(login.getPassword()).isEqualTo("agarwal@123");
 assertThat(login.getUsername()).isEqualTo("vidur@gmail.com");
 }
}
 