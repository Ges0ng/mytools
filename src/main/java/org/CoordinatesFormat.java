package org;

import java.io.*;
import java.util.Date;

import static org.JsonParseDemo.getFileEncode;

/**
 * @author zhanggh
 * @description: 坐标格式转换
 * @date 2021/7/12 10:11
 */

public class CoordinatesFormat {

    /**
     * 读取的文件地址
     */
    private static final String READ_FILEPATH = "qywl.md";

    public static void main(String[] args) {
        System.out.println(simpleReplace("[[[[120.97468802591791,30.97659722605858],[120.97485473250495,30.975553662386979],[120.97499750016519,30.974954038213419],[120.97516882135781,30.974068878719272],[120.97528303548589,30.973811896930677],[120.97566850816887,30.973826173696757],[120.97685347974985,30.974083155485353],[120.97723895243271,30.974097432251433],[120.97735316656102,30.973869003994828],[120.97736744332715,30.973526361609977],[120.97721039890078,30.973140888927055],[120.97718184536862,30.972798246542148],[120.97725322919885,30.972626925349751],[120.97809555839493,30.97259837181776],[120.98042267125859,30.972626925349751],[120.98107940249614,30.972569818285599],[120.98153625900923,30.972469880923377],[120.98163619637151,30.972526987987528],[120.98169330343561,30.972941014202611],[120.98153625900923,30.973911834292899],[120.98112223279421,30.975182466470137],[120.98103657219806,30.975582215918969],[120.98079386717544,30.975810644175574],[120.98045122479076,30.976281777454808],[120.98039411772643,30.976638696605569],[120.98008002887369,30.977381088439422],[120.97973522591772,30.977864606058638],[120.97959914591766,30.977852546058507],[120.9794565859178,30.977837876058523],[120.97932014591768,30.977816816058585],[120.97919990591768,30.977799716058605],[120.97910018591793,30.977798186058472],[120.97896842591766,30.977795126058545],[120.97884782591771,30.977772086058629],[120.9787261459177,30.977756336058526],[120.97860950591783,30.977745536058542],[120.97849790591764,30.977741396058555],[120.97839602591776,30.977712416058473],[120.97827866591766,30.977700626058557],[120.97816958591764,30.977690636058419],[120.97806338591764,30.977689646058543],[120.97793810591773,30.977706836058537],[120.97781786591773,30.977713136058533],[120.97768610591768,30.977692076058538],[120.9775673059178,30.977678486058501],[120.97747694591774,30.977657966058594],[120.97738838591775,30.977639246058637],[120.97723358591773,30.977596226058616],[120.97715690591772,30.977572466058412],[120.97708562591768,30.977561306058597],[120.9769747459176,30.977554916058587],[120.97686242591774,30.97753727605852],[120.97672274591767,30.977530616058523],[120.97659062591779,30.977523956058526],[120.97647722591765,30.977526656058565],[120.97639910591775,30.977522246058534],[120.97629362591775,30.977495516058475],[120.97619138591782,30.977467436058426],[120.97608230591769,30.977450246058602],[120.97597754591769,30.97745060605849],[120.97588106591766,30.977410286058507],[120.97579070591772,30.977374016058555],[120.97570646591763,30.977342156058576],[120.97562906591762,30.977305796058552],[120.97555670591763,30.977266196058515],[120.9754897459176,30.977223806058539],[120.97543178591766,30.977184566058504],[120.97528346591767,30.977105006058487],[120.97518050591762,30.977035436058607],[120.97516574591762,30.977029676058642],[120.97500914591765,30.976909976058501],[120.97499870591767,30.976902866058431],[120.97489178591789,30.976792706058582],[120.97487774591767,30.976778846058501],[120.97480790591774,30.976702256058566],[120.97468802591791,30.97659722605858]]]]"));

    }

    /**
     * 优化后的格式转换
     * @param coordinates
     * @return
     */
    public static String simpleReplace(String coordinates){
        return coordinates.replace(",", " ")
                .replace("] [", ",")
                .replace("[[[", "MULTIPOLYGON(((")
                .replace("]]]", ")))")
                .replace("[", "")
                .replace("]", "")
                ;
    }

    /**
     *
     * 格式转换
     * @param coordinates
     * @return
     */
    public static String replace(String coordinates){
        return coordinates.replace('[', '(')
                .replace(']', ')')
                .replace("))),(((", "));((")
                .replace("),(", ");(")
                .replace(",", " ")
                .replace(";", ",")
                .replace("(((", "MULTIPOLYGON(((")
                .replace("((((", "MULTIPOLYGON(((")
                .replace("MULTIPOLYGONMULTIPOLYGON(((", "MULTIPOLYGON(((")
                .replace("))))", ")))")
                .replace("),(", ",")
                .replace("),(", ",");
    }

    /**
     * 读取文件内容
     * @param filePath
     * @return
     */
    public static String readFileContent(String filePath)  {
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();
        //判断文件是否存在
        if (file.isFile() && file.exists()) {
            InputStreamReader read = null;
            try {
                String encoding = getFileEncode(file);
//                String encoding = "ISO-8859-1";
//                String encoding = "ASCII";
                read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt + "\n");
                }
                System.out.println(new Date() + "：文件读取完成,编码格式为" + encoding);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    assert read != null;
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new NullPointerException("文件不存在");
        }
        return sb.toString();
    }

}
