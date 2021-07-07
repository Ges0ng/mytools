package org.example;

import com.aliyun.odps.udf.UDF;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

public class AreaCoorsCenter extends UDF {

    public static void main(String[] args) {
        String s = "[[[[106.379706040158183,29.394512282710835],[106.379915341520956,29.394191325586316],[106.380805662810204,29.393778017330504],[106.381329426808662,29.393549419520777],[106.381589764882406,29.392816272555546],[106.381535638118464,29.392037786810356],[106.381219121585772,29.391213012208976],[106.380954673265478,29.390297235453765],[106.381267078198107,29.3893360140321],[106.381580511442451,29.388877070123318],[106.381997059919129,29.387915942354752],[106.382151190746882,29.386404244500117],[106.382305354138524,29.385441852641225],[106.38235637439675,29.384571411456744],[106.382247111420355,29.382831322853843],[106.382034757507213,29.38205267544858],[106.38135171233759,29.381274559427876],[106.381245541725619,29.381046323514148],[106.381034175437847,29.380175615763513],[106.381137283913745,29.379443302864221],[106.381135233437917,29.37866385638608],[106.381814169611147,29.377655956453619],[106.382389965251292,29.377060176105317],[106.382387926086295,29.376464833160771],[106.382281726227234,29.375686284142979],[106.382280702463873,29.375320072889952],[106.382593107886777,29.374632978402001],[106.38322095880838,29.374037228882674],[106.38374465896328,29.373579449324584],[106.383793656760147,29.372433833150286],[106.383687471184928,29.371792365227638],[106.383895716257783,29.371105157758151],[106.384575615722738,29.370692528854622],[106.385046205674158,29.370051569647007],[106.385202378910094,29.369501385336164],[106.385095186957969,29.368677813944828],[106.385199299522512,29.368264663466956],[106.385983269342944,29.367394821343893],[106.386611030952423,29.366752962698811],[106.386818219033742,29.365561433219355],[106.386868223100038,29.364599910075352],[106.387076454929442,29.364141807266993],[106.38765214119806,29.363775042124779],[106.388070619570172,29.363316091885416],[106.388329854157433,29.36207955883544],[106.388640137600973,29.360704977527853],[106.389162688077917,29.359742786871859],[106.38963216393546,29.358735521136769],[106.390101646927178,29.358001407949196],[106.390519057857844,29.357177197695204],[106.391144642587278,29.355664703254568],[106.391455911594832,29.354657298445098],[106.391455894802206,29.354382134073287],[106.390563919917795,29.35410839800381],[106.389462675217217,29.353880512180787],[106.387993933052599,29.353561248841494],[106.38652510219471,29.353286930461735],[106.385686036727606,29.353471345356045],[106.38442939801817,29.353929529400002],[106.381443256081155,29.355122423881539],[106.38086641842726,29.355398007783315],[106.38023645403473,29.355123196841699],[106.380131280409898,29.354893950696539],[106.379868880210552,29.354894676686552],[106.378820268804787,29.355123693409048],[106.378138181314156,29.354986863568641],[106.377872681486309,29.353749829139343],[106.377923729981262,29.353200557430775],[106.377556121729683,29.352925980815709],[106.376876058905069,29.35365864525059],[106.376301159940169,29.354483472229365],[106.375514842908117,29.354483544215004],[106.375460726442924,29.354071233260974],[106.376141836462651,29.353521710810448],[106.376661597805381,29.351872327733663],[106.376816780862697,29.350681791539852],[106.376814749704906,29.350223513476994],[106.37702304103361,29.34939925609628],[106.377126170542113,29.348757987194599],[106.377177223231271,29.34825474158373],[106.377228281301953,29.34784155006113],[106.377175167031595,29.347384213023407],[106.376598209727334,29.347201439538726],[106.375862972839343,29.346972440721029],[106.375179791578944,29.346514348639126],[106.37491631742374,29.346102781109792],[106.37449557009964,29.345690018332956],[106.373130148472242,29.345094956768499],[106.372132346343349,29.344774484038041],[106.371502189872913,29.344546523215453],[106.371294858260782,29.345324722934947],[106.371243798047189,29.345873988805675],[106.370405270263603,29.346195065271214],[106.369460500565609,29.34619578194603],[106.368515686606841,29.345920306334389],[106.367990665173437,29.345737457756819],[106.367728145796164,29.345553974804776],[106.367658351284732,29.351188277968539],[106.369599269342928,29.354569010201754],[106.371187930215441,29.366243045516981],[106.369364899193414,29.372265085215897],[106.366043059061568,29.382297108978015],[106.366178453805759,29.384371479429042],[106.366179467819251,29.384569592641654],[106.365981095893105,29.384941512214816],[106.366011184341204,29.385412821707359],[106.366096374841646,29.385760141774554],[106.366125449117931,29.386032337337653],[106.366269780747871,29.38677696627138],[106.36618666539475,29.387595304054848],[106.366019403496239,29.388760711931411],[106.365950294972194,29.38924087904028],[106.365790005569437,29.389740922959376],[106.365311123572866,29.391018928457335],[106.365214970519062,29.391685156386089],[106.365185942317737,29.39218439060339],[106.366796161958575,29.392408873289707],[106.367162873440748,29.392271324652544],[106.368001482602153,29.391905314859951],[106.369469256740615,29.391401077850006],[106.370518212299885,29.391263425005679],[106.371305669388207,29.39135452396286],[106.372409715435296,29.392087370724845],[106.373039867775574,29.392545430474932],[106.373723097097965,29.392957518897624],[106.374984328691852,29.393781525231351],[106.375509225947368,29.393872205936798],[106.376610067483426,29.393917525147554],[106.377659794526366,29.394146849833916],[106.379023974600898,29.394374478444242],[106.379706040158183,29.394512282710835]]]]";
        String replace =
                s.replace('[', '(')
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
        System.out.println(replace);
        System.out.println(evaluate(replace));
    }

    /**
     * 计算区域中心点坐标
     * @param boundPoly
     * @return
     */
    public static String evaluate(String boundPoly) {
        boundPoly=boundPoly.replace("MULTIPOLYGON(((","").replace(")))","").replace(")),((",";");
        String[] boundPolys= boundPoly.split(";");
        ArrayList<String> centers=new ArrayList<>();
        for (String boundPoly1 : boundPolys) {
            double centerLng = 0.0D;
            double centerLat = 0.0D;
            int cnt = 0;
            String[] bounds = boundPoly1.split(",");

            for (String bound : bounds) {
                String[] coors = bound.split(" ");
                centerLng += Double.valueOf(coors[0]);
                centerLat += Double.valueOf(coors[1]);
                cnt += 1;
            }
            centers.add(centerLng / cnt + "," + centerLat / cnt);
        }
        return StringUtils.join(centers,";");
    }
}