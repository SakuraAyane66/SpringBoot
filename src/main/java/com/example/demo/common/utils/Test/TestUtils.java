package com.example.demo.common.utils.Test;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.math.BigDecimal;

public class TestUtils {
    public static void main(String[] args) {
        //各种测试main代码
        //生成随机数代码
//        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
//        Double min=3.4;
//        Double max=4.0;
//        Random random = new Random();
//        List<String> list = new ArrayList<>();
//        for(int i=0;i<3000;i++){
//            if(list.size()>=100){
//                break;
//            }
//            double r = random.nextDouble()*3+8;
//            if(r<=9.83&&r>=9.45){
//                list.add(df.format(r));
//            }
//
//        }
//        System.out.println("?"+list.size());
//        for(int j=0;j<list.size();j++){
//            System.out.print(list.get(j)+",");
//        }
        /*   js执行代码
        String jsName = "C:\\Users\\One Piece\\Desktop\\重构之前的项目\\alice\\test.js";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
           //读取js
        try {
        FileReader fileReader = new FileReader(jsName);
        //执行指定脚本
            String s = (String)engine.eval(fileReader);
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        */

//此处写要测试的代码


        GlobalCoordinates source = new GlobalCoordinates(27.924215295, 120.685995023);
        GlobalCoordinates target = new GlobalCoordinates(27.924163593, 120.685911759);

//        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
//        double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);

//        System.out.println("Sphere坐标系计算结果："+meter1 + "米");
//        System.out.println("WGS84坐标系计算结果："+meter2 + "米");

        double dd=GPSHelper.GetDistance(120.685995023,27.924215295,120.685911759,27.924163593);

    }
    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){

        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);

        return geoCurve.getEllipsoidalDistance();
    }

    //米勒算法，gps转xy坐标系
    public static double[] MillierConvertion( double lon,double lat)
    {
        double L = 6381372 * Math.PI * 2;//地球周长
        double W=L;// 平面展开后，x轴等于周长
        double H=L/2;// y轴约等于周长一半
        double mill=2.3;// 米勒投影中的一个常数，范围大约在正负2.3之间
        double x = lon * Math.PI / 180;// 将经度从度数转换为弧度
        double y = lat * Math.PI / 180;// 将纬度从度数转换为弧度
        y=1.25 * Math.log( Math.tan( 0.25 * Math.PI + 0.4 * y ) );// 米勒投影的转换

        // 弧度转为实际距离
        x = ( W / 2 ) + ( W / (2 * Math.PI) ) * x;
        y = ( H / 2 ) - ( H / ( 2 * mill ) ) * y;

        double[] result=new double[2];
        result[0]=x;
        result[1]=y;
        return result;
    }

    //返回两个Double做减法的值，d1 - d2
    public static double getSub(Double d1,Double d2){
        BigDecimal bd1 = new BigDecimal(d1.toString());
        BigDecimal bd2 = new BigDecimal(d2.toString());
        return bd1.subtract(bd2).doubleValue();
    }

    //提炼公共方法，传入3个点，前一个点，后一个点（2点），gps点（需要判断的点）
    //输出左幅还是右幅 ,1为左幅，0是右幅
    public static int getPointLeftOrRight(Double[] jkyRoadDouble,Double[] nextJkyRoadDouble,Double[] gpsDouble){
        boolean direction;
        //算斜率的时候，分母不能为0，如果x1,和x2相同的情况，单独讨论
        if(nextJkyRoadDouble[0].compareTo(jkyRoadDouble[0])==0){
            //判断正负方向,此处因为x相同，只能判断y轴的方向,规定y轴向上，x轴向右（都是正数）为正方向
            if((getSub(nextJkyRoadDouble[1],jkyRoadDouble[1]))>0){
                direction = true;
            }else {  //y再次相同的情况直接无视
                direction =false;
            }
            //判断左右幅,
            //第一个TF 是getsub()>0 ,第二个 TF 是direction
            // T T返回右，T F ,F T 返回左， F F返回右 简化为 两者逻辑相等返回右
            if(getSub(gpsDouble[0],jkyRoadDouble[0])>0==direction){
                return 0; //返回右幅
            }else {
                return 1; //返回左幅
            }
            //上面都是x1=x2的情况
        }
        //斜率存在的时候，直接求y坐标,
        //a = (y1 - y2)/(x1 - x2)
        //b = (x1 * y2 - x2 * y1)/(x1 - x2)
        double y3 = getSub(jkyRoadDouble[1],nextJkyRoadDouble[1])/getSub(jkyRoadDouble[0],nextJkyRoadDouble[0])*gpsDouble[0]+
                (jkyRoadDouble[0]*nextJkyRoadDouble[1]-nextJkyRoadDouble[0]*jkyRoadDouble[1])/getSub(jkyRoadDouble[0],nextJkyRoadDouble[0]);
        //判断direction
        if(getSub(nextJkyRoadDouble[0],jkyRoadDouble[0])>0){
            direction = true;
        }else {
            direction =false;
        }
        System.out.println("direction is "+direction);
        System.out.println("y3 is ?="+y3);
        System.out.println("左右幅？"+getSub(gpsDouble[1],y3));
        //判断左右幅
        if(getSub(gpsDouble[1],y3)>0==direction){
            return 1;
        }else {
            return 0;
        }
    }


}

class GPSHelper {
    // 圆周率
    public static final double PI = 3.14159265358979324;
    // 赤道半径(单位m)
    private static final  double EARTH_RADIUS = 6378137;

    /**
     * 转化为弧度(rad)
     * */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,
     * 计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
     * @param lon1 第一点的经度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的经度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位km
     * */
    public static double GetDistance(double lon1,double lat1,double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        return s;
    }
}