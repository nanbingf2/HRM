package com.rogercw.testng;

import com.rogercw.po.User;
import com.rogercw.service.LoginService;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * Created by 1 on 2018/5/25.
 */
public class LoginServiceTest {

    @Mock
    private LoginService loginMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginTest() {
        when(loginMock.login("zhangsan","123456")).thenReturn(new User("zhangsan","123456"));
        User user=loginMock.login("zhangsan","123456");
        assertEquals(user.getLoginname(),"zhangsan");
        assertEquals(user.getPassword(),"123456");
    }
}
