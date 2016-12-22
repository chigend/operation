package com.cookabuy.entity.operation.dto;

import lombok.Getter;
import lombok.Setter;

/**该类表示最高级别管理员修改运营人员信息的表单，包含真实姓名，角色标签，密码，确认密码，
 * 该类包含于{@link UpdateUserForm }
 * @author yejinbiao
 * @create 2016-12-22-17:01
 */
@Setter
@Getter
public class UpdateInfoForm {
    private String realName;
    
    private String roleTag;
    
    private String password;
    
    private String confirmPassword;
}
