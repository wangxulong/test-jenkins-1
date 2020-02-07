//package com.example.testjenkins;
//
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.util.ResourceUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles(value = "localtest",resolver = SystemPropertyActiveProfileResolver.class)
//public abstract class BaseUnitTest {
//    public String readFile(String path) throws IOException {
//        File file = ResourceUtils.getFile("classpath:" + path);
//        return Files.readString(file.toPath());
//    }
//}
