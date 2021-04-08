package com.qiniuyun;

import com.qiniu.common.QiniuException;
import com.qiniuyun.service.IQiniuService;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.UUID;

@SpringBootTest
class QiniuyunApplicationTests {

    @Autowired
    private IQiniuService qiniuService;

    @Test
    public void testUpload() throws QiniuException {
        String result = qiniuService.uploadFile(new File("D:\\Download\\picture\\view\\1.jpg"), "upload");
        System.out.println("访问地址： " + result);
    }

    @Test
    public void testDelete() throws QiniuException {
        String result = qiniuService.delete("lizuhu");
        System.out.println(result);
    }

    @Test
    public void testRandom(){
        int i = RandomUtils.nextInt();
        System.out.println(i);
    }

    @Test
    public void test() {
        System.out.println(UUID.randomUUID());
    }
}
