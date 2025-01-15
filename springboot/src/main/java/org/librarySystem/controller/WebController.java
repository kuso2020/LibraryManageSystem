package org.librarySystem.controller;

import org.librarySystem.common.Result;
import org.librarySystem.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class WebController {

    @GetMapping("/hello")
    public Result hello() {
        return Result.success("hello");
    }

    @GetMapping("/weather")
    public Result weather() {
        return Result.success("今天天气：25摄氏度 晴");
    }

//    @GetMapping("/count")
////    @ResponseBody
//    public Integer count() {
////        System.out.println("Returning count: 15");
//        return 15;
//    }
    @GetMapping("/count")

    public Result count() {
        return Result.success(5);
    }
    @GetMapping("/test")
    public Result test(){
        int a = 1/0;
        return Result.success();
    }

    @GetMapping("/test2")
    public Result test2() {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("count", 15);
//        return Result.success(map);
//        throw new RuntimeException("错误，禁止请求");
        throw new CustomException("400", "业务错误");
    }

}
