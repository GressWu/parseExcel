package org.example;

import com.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.entity.Account;
import org.example.entity.Cat;
import org.example.mapper.AccountMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {



//        System.out.println("Hello world!");
//
//       Cat cat = new Cat("张三",12);
//        Cat cat1 = new Cat("李四",13);
//        Cat cat2 = new Cat("王五",12);
//        Cat cat3 = new Cat("张六",18);
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("1");
//        strings.add("1");
//        ArrayList<Cat> cats = new ArrayList<>();
//        cat.setLists(strings);
//        cats.add(cat);
//        cats.add(cat1);
//        cats.add(cat2);
//        cats.add(cat3);
//        ExcelUtils.exportSheet(cats);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {

            AccountMapper accountMapper = session.getMapper(AccountMapper.class);

            List<Account> accounts = accountMapper.selectAll();

            System.out.println(accounts);
        }








    }
}