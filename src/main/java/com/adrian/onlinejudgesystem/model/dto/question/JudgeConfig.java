package com.adrian.onlinejudgesystem.model.dto.question;

import lombok.Data;

/*
 * Date: 2024/9/17 20:48
 * Author: Adrian
 * Version: 1.0
 * Description: 题目配置
 * */
@Data
public class JudgeConfig {

    /**
     * 时间限制(ms)
     */
    private Long timeLimit;

    /**
     * 内存限制(KB)
     */
    private Long memoryLimit;

    /**
     * 堆栈限制(KB)
     */
    private Long stackLimit;
}
