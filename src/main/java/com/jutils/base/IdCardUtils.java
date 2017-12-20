package com.jutils.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 15位身份证号码：第7、8位为出生年份(两位数)，第9、10位为出生月份，第11、12位代表出生日期，第15位代表性别，奇数为男，偶数为女。
 * 18位身份证号码每一位含义
 * 1-2位省、自治区、直辖市代码；
 * 3-4位地级市、盟、自治州代码；
 * 5-6位县、县级市、区代码；
 * 7-14位出生年月日，比如19670401代表1967年4月1日；
 * 15-17位为顺序号，其中17位男为单数，女为双数；
 * 18位为校验码，0-9和X，由ISO 7064:1983,MOD 11-2校验码系统产生
 *
 * 前6位统称地址码，通常指的是公民常住户口所在县（市、镇、区）的行政区划代码，比如110102是北京市西城区
 * 第7到14位共8位为出生日期
 *
 * 校验码计算方法
 * 1. 将身份证号码从右往左依次标记为 a1， a2，...，a18，其中 a1 为需要计算的校验码
 * 2. 每一位对应的权重系数 Wi = 2^(i-1) mod 11 ，得到：
 * i	18	17	16	15	14	13	12	11	10	9	8	7	6	5	4	3	2	1
 * Wi	7	9	10	5	8	4	2	1	6	3	7	9	10	5	8	4	2	1
 *
 * 3. 然后计算从 a2 到 a18 加权和，也就是身份证号从左往右从第一位开始到第十七位依次乘上加权系数
 * "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"
 *
 * 4. 在得到加权求和结果 S 之后 a1 = (12 - (S mod 11)) mod 11，这样余数只能为 0 到 10 共 11 个数字，10 用X表示。
 *
 * 举例 某男性的身份证号码是340523198001010013
 * 得出前17位的乘积和：
 * (3*7+4*9+0*10+5*5+2*8+3*4+1*2+9*1+8*6+0*3+0*7+1*9+0*10+1*5+0*8+0*4+1*2) = 185
 * 然后再求余：
 * 185 % 11 = 9
 * 最后通过对应规则就可以知道余数9对应的数字是3。所以，可以判定这是一个合格的身份证号码。
 *
 * https://gist.github.com/Jiezhi/9e4c546da2211fc24be77c92a835357a
 */
public class IdCardUtils {

    /**
     * 身份证最小年份
     */
    public static final int MIN = 1930;
    /**
     * 中国公民身份证号码最小长度。
     */
    public static final int CHINA_ID_MIN_LENGTH = 15;
    /**
     * 中国公民身份证号码最大长度。
     */
    public static final int CHINA_ID_MAX_LENGTH = 18;
    /**
     * 每位加权因子
     */
    private static int Wi[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
     * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少？
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
     * 2。
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     *
     * @param idCard 待验证的身份证
     * @return
     */
    public static boolean isValidate(String idCard) {
        String Ai = "";
        // 非18, 15位为假，校验长度
        if (idCard.length() != 18 && idCard.length() != 15) {
            return false;
        }
        if (idCard.length() == 18) {
            Ai = idCard.substring(0, 17);
        } else if (idCard.length() == 15) {
            idCard = convert15CardTo18(idCard);
            if (!StringUtils.isEmpty(idCard)) {
                Ai = idCard.substring(0, 17);
            }
        }

        // 校验前面数字
        if (!isDigital(Ai)) {
            return false;
        }

        String year = Ai.substring(6, 10);
        String month = Ai.substring(10, 12);
        String day = Ai.substring(12, 14);

        String idDate = year.concat("-").concat(month).concat("-").concat(day);
        if (!isDataFormat(idDate)) {
            return false;
        }

        if (!validateDate(year, month, day)) {
            return false;
        }

        Hashtable<String, String> areaCode = getAreaCode();
        if (areaCode.get(Ai.substring(0, 2)) == null) {
            return false;
        }

        String code = getVerifyCode18(Ai);

        if (idCard.length() == 18) {
            return Ai.concat(String.valueOf(code)).equalsIgnoreCase(idCard);
        }
        return true;
    }

    /**
     * 验证日期字符串是否是YYYY-MM-DD格式
     *
     * @param str
     * @return
     */
    public static boolean isDataFormat(String str) {
        boolean flag = false;
        //String regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 省，直辖市代码表： { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
     * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
     * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
     * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
     * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
     * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
     */
    private static Hashtable<String, String> getAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 18位身份证号码的基本数字和位数验校
     *
     * @param idcard 待验证的身份证
     * @return
     */
    public static boolean is18Idcard(String idcard) {
        return Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$", idcard);
    }

    /**
     * 数字验证
     *
     * @return 字符串是否纯数字
     */
    private static boolean isDigital(String str) {
        return !(str == null || "".equals(str)) && str.matches("^[0-9]*$");
    }

    /**
     * 将字符数组转换成数字数组
     *
     * @param chars 字符数组
     * @return 数字数组
     */
    public static int[] convertCharToInt(char[] chars) {
        int[] iArr = new int[chars.length];
        try {
            for (int i = 0; i < chars.length; i++) {
                iArr[i] = Integer.parseInt(String.valueOf(chars[i]));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return iArr;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit 身份证前 17 位
     * @return 加权和
     */
    private static int getPowerSum(int[] bit) {
        int sum = 0;
        if (Wi.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            sum = sum + bit[i] * Wi[i];
        }
        return sum;
    }

    public static boolean validateDate(String year, String month, String day) {
        return validateDate(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    /**
     * 验证小于当前日期 是否有效
     *
     * @param year 待验证日期(年)
     * @param month 待验证日期(月 1-12)
     * @param day 待验证日期(日)
     * @return 是否有效
     */
    public static boolean validateDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        int curYear = cal.get(Calendar.YEAR);
        int datePerMonth;
        if (year < MIN || year >= curYear) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                datePerMonth = 30;
                break;
            case 2:
                boolean dm = ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
                        && (year > MIN && year < curYear);
                datePerMonth = dm ? 29 : 28;
                break;
            default:
                datePerMonth = 31;
        }
        return (day >= 1) && (day <= datePerMonth);
    }

    /**
     * @param idno
     * @return 身份证信息中代表性别的数值
     */
    public static int getUserSex(String idno) {
        String sex = "1";
        if (idno != null) {
            if (idno.length() > 15) {
                sex = idno.substring(16, 17);
            }
        }

        return Integer.parseInt(sex) % 2 == 0 ? 0 : 1;
    }

    /**
     * 将15位身份证号码转换为18位
     *
     * @param idCard 15位身份编码
     * @return 18位身份编码
     */
    public static String convert15CardTo18(String idCard) {
        String idCard18;
        if (idCard.length() != CHINA_ID_MIN_LENGTH) {
            return null;
        }
        if (isDigital(idCard)) {
            // 获取出生年月日
            String birthday = idCard.substring(6, 12);
            Date birthDate = null;
            try {
                birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            if (birthDate != null) {
                cal.setTime(birthDate);
            }
            // 获取出生年(完全表现形式,如：2010)
            String sYear = String.valueOf(cal.get(Calendar.YEAR));
            idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
            // 转换字符数组
            String sVal = getVerifyCode18(idCard18);
            if (sVal.length() > 0) {
                idCard18 += sVal;
            } else {
                return null;
            }
        } else {
            return null;
        }
        return idCard18;
    }

    private static String getVerifyCode18(String last17Str) {
        int[] ints = convertCharToInt(last17Str.toCharArray());
        int powerSum = getPowerSum(ints);
        return getCheckCode18(powerSum);
    }

    /**
     * 通过前17位和，计算得到最后一位
     *
     * @param sum 前17和
     * @return 最后一位
     */
    private static String getCheckCode18(int sum) {
        int mod = (12 - (sum % 11)) % 11;
        if (mod == 10) {
            return "x";
        }
        return String.valueOf(mod);
    }

    /**
     * 根据身份号获取年龄
     *
     * @param idCard 身份编号
     * @return 年龄，如果为 -1 则表示 ID 不合法
     */
    public static int getAgeByIdCard(String idCard) {
        int iAge = 0;
        if (!isValidate(idCard)) {
            return -1;
        }
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        String year = idCard.substring(6, 10);
        int iCurrYear = Calendar.getInstance().get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        return iAge;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
            return idCard.substring(6, 14);
        }
        return "";
    }

    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static Short getYearByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
            return Short.valueOf(idCard.substring(6, 10));
        }
        return null;
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard 身份编号
     * @return 生日(MM)
     */
    public static Short getMonthByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard 身份编号
     * @return 生日(dd)
     */
    public static Short getDateByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        return Short.valueOf(idCard.substring(12, 14));
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static String getGenderByIdCard(String idCard) {
        String sGender = "N";
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = "M";
        } else {
            sGender = "F";
        }
        return sGender;
    }

    /**
     * 根据身份编号获取户籍省份
     *
     * @param idCard 身份编码
     * @return 省级编码。
     */
    public static String getProvinceByIdCard(String idCard) {
        int len = idCard.length();
        String sProvince = null;
        String sProvinNum = "";
        if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
            sProvinNum = idCard.substring(0, 2);
        }
        sProvince = getAreaCode().get(sProvinNum);
        return sProvince;
    }

    /**
     * 验证身份证是否合法
     *
     * @param idcard
     * @return
     */
    @SuppressWarnings("static-access")
    public boolean isValidatedAllIdcard(String idcard) {
        return this.isValidate(idcard);
    }
}
