package sidea.version.nudge.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import sidea.version.nudge.dto.UserDto;

//@WebAppConfiguration
//@MybatisTest
@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MapperTest {

    //단위 테스트를 하고 싶은데 어떻게 해야될지...
    @Autowired
    private LoginMapper loginMapper;

    @Test
    @DisplayName("회원메일주소로 회원내역조회")
    public void selectUserByUserEmailMapper(){
        //given
        String userEmail = "jks@jks.com";
        //when
        UserDto user = loginMapper.selectUserByUserEmail(userEmail);
        //then
        Assertions.assertThat(user.getUserEmail()).isEqualTo(userEmail);
    }
}
