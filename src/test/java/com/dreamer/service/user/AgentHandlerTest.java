package com.dreamer.service.user;

import com.dreamer.repository.user.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by huangfei on 29/04/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class AgentHandlerTest {
    @Autowired
    private UserDAO userDAO;
    @Test
    @Transactional
    public void selfRegister() throws Exception {
        System.out.println("===="+userDAO.findById(3).getId());
    }

}