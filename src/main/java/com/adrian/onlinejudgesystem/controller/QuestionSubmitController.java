package com.adrian.onlinejudgesystem.controller;

import com.adrian.onlinejudgesystem.common.BaseResponse;
import com.adrian.onlinejudgesystem.common.ErrorCode;
import com.adrian.onlinejudgesystem.common.ResultUtils;
import com.adrian.onlinejudgesystem.exception.BusinessException;
import com.adrian.onlinejudgesystem.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.adrian.onlinejudgesystem.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.adrian.onlinejudgesystem.model.entity.Question;
import com.adrian.onlinejudgesystem.model.entity.QuestionSubmit;
import com.adrian.onlinejudgesystem.model.entity.User;
import com.adrian.onlinejudgesystem.model.vo.QuestionSubmitVO;
import com.adrian.onlinejudgesystem.service.QuestionSubmitService;
import com.adrian.onlinejudgesystem.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 点赞 / 取消点赞
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 提交记录的id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
                                               HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        Long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }


    /**
     * 分页获取题目提交列表 (除了管理员,普通用户只能看到非答案,提交代码等公开信息)
     *
     * @param questionSubmitQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
    }

}
