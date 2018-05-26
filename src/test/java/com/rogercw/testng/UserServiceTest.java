package com.rogercw.testng;

import com.rogercw.po.User;
import com.rogercw.service.UserService;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

/**
 * Created by 1 on 2018/5/25.
 */
public class UserServiceTest {

    @Mock
    private UserService userMock;

    @Before
    public void inti(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserByIdTest() {
        when(userMock.findUserById(1)).thenReturn(new User("zhangsan","123456"));
        User user=userMock.findUserById(1);
        User user2=userMock.findUserById(2);
        assertEquals("zhangsan",user.getLoginname());
        assertEquals("123456",user.getPassword());
        assertNull(user2);
    }

    @Test
    public void deleteUserTest() {

    }
}
