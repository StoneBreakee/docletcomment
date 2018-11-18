 package com.cmrh.aml.esb;

import java.util.Map;

import com.cmrh.sf.esb.EsbMethod;

public interface AMLBlacklistServiceEsb {

    /**
     * 执行内容：黑名单/政要名单人员匹配查询 黑名单匹配规则： （1）对于有证件号码的，若证件号码一致时，进行拦截 （2）对于无证件号的，姓名+性别+出生年月+国籍一致时进行拦截
     * （3）不足四项要素的，姓名+出生年月+性别一致时进行拦截 （4）不足三项要素的，姓名+性别一致时拦截 （5）姓名+出生年月一致时拦截 （6）姓名+国籍一致的进行拦截 （7）只有姓名的，姓名一致时进行拦截
     * 返回标记：p_blacklis_flag BLK ---黑名单人员； PEP ---政要名单人员 N ---非名单人员
     */
    @EsbMethod(esbServiceId = "com.cmrh.aml.blacklistCheck")
    public Map<String, Object> blacklistCheck(Map<String, Object> paramMap)
        throws Exception;

}
