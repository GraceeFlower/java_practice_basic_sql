package com.thoughtworks;

public class FormatChecker {

    private final String[] registerMsg;

    public FormatChecker(String[] registerMsg) {
        this.registerMsg = registerMsg;
    }

    public boolean isCorrect() {
        return checkUserName() &&
            checkPhoneNum() &&
            checkEmail() &&
            checkPassword();
    }

    public String getFirstErrorMsg() {
        if (!isCorrect()) {
            if (!checkUserName()) {
                return "用户名";
            } else if (!checkPhoneNum()) {
                return "手机号";
            } else if (!checkEmail()) {
                return "邮箱";
            } else {
                return "密码";
            }
        }
        return null;
    }

    private boolean checkUserName() {
        return registerMsg[0].matches(".{2,10}");
    }

    private boolean checkPhoneNum() {
        return registerMsg[1].matches("^1[0-9]{10}");
    }

    private boolean checkEmail() {
        return registerMsg[2].matches(".*@.*");
    }

    private boolean checkPassword() {
        String regex = "(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{8,16}$";
        return registerMsg[3].matches(regex);
    }
}
