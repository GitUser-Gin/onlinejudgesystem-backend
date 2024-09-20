package com.adrian.onlinejudgesystem.service;

import com.adrian.onlinejudgesystem.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.adrian.onlinejudgesystem.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.adrian.onlinejudgesystem.model.entity.QuestionSubmit;
import com.adrian.onlinejudgesystem.model.entity.User;
import com.adrian.onlinejudgesystem.model.vo.QuestionSubmitVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit,  User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage,  User loginUser);
}
