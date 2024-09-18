package com.adrian.onlinejudgesystem.service.impl;

import com.adrian.onlinejudgesystem.common.ErrorCode;
import com.adrian.onlinejudgesystem.exception.BusinessException;
import com.adrian.onlinejudgesystem.mapper.QuestionSubmitMapper;
import com.adrian.onlinejudgesystem.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.adrian.onlinejudgesystem.model.entity.Question;
import com.adrian.onlinejudgesystem.model.entity.QuestionSubmit;
import com.adrian.onlinejudgesystem.model.entity.User;
import com.adrian.onlinejudgesystem.model.enums.QuestionSubmitLanguageEnum;
import com.adrian.onlinejudgesystem.model.enums.QuestionSubmitStatusEnum;
import com.adrian.onlinejudgesystem.service.QuestionService;
import com.adrian.onlinejudgesystem.service.QuestionSubmitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 28945
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2024-09-17 18:01:45
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {
    @Resource
    private QuestionService questionService;

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 校验编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"编程语言错误");
        }
        // 是否已题目提交
        long userId = loginUser.getId();
        // 每个用户串行题目提交
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        // todo 设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if (!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据插入失败");
        }
        return questionSubmit.getId();
    }
    
}




