package com.adrian.onlinejudgesystem.service;

import com.adrian.onlinejudgesystem.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.adrian.onlinejudgesystem.model.entity.QuestionSubmit;
import com.adrian.onlinejudgesystem.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 28945
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-09-17 18:01:45
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 点赞
     *
     * @param questionSubmitAddRequest 题目提交信息
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
