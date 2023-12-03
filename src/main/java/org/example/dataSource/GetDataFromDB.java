package org.example.dataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.entity.Account;
import org.example.mapper.AccountMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GetDataFromDB implements ParseInterface {
    @Override
    public List getDate() throws IOException {
        List<Account> objects = new ArrayList<>();
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = session.getMapper(AccountMapper.class);
            objects = accountMapper.selectAll();
        }
        return objects;
    }
}
