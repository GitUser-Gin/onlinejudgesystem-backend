package com.adrian.onlinejudgesystem.model.dto.question;

import lombok.Data;

/*
 * Date: 2024/9/17 20:48
 * Author: Adrian
 * Version: 1.0
 * Description: 题目用例
 * */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
