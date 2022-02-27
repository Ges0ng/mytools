package odps.utils;

import com.aliyun.odps.udf.UDF;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * @author zhanggh
 * @description: TODO
 * @date 2022/1/17 15:08
 */

public class PinYinChange extends UDF {

    public String evaluate(String str) {
        String shortPinyin = "";
        try {
            shortPinyin = PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITHOUT_TONE) + ";" + PinyinHelper.getShortPinyin(str);

        } catch (PinyinException e) {
            e.printStackTrace();
        }
        return shortPinyin;
    }

    public static void main(String[] args) {
        String s = "嘉善公交站";
        PinYinChange pinYinChange = new PinYinChange();
        System.out.println(pinYinChange.evaluate(s));
    }
}
