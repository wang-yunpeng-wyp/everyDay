package cn.ofpp;

import cn.ofpp.calendarist.Calendarist;
import cn.ofpp.calendarist.pojo.LunarDate;
import cn.ofpp.calendarist.pojo.SolarDate;
import cn.ofpp.core.GirlFriend;
import cn.ofpp.core.MessageFactory;
import cn.ofpp.core.Wx;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * 启动类
 *
 * 这个理论上只能用测试号 正式的号 个人认证是不支持模板消息的 企业认证的又必须使用微信模板库里的模板 只有测试的可以自定义模板内容
 * <a href="https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index">申请公众号测试应用地址</a>
 *
 * @author DokiYolo
 * Date 2022-08-22
 */
public class Application {

    /**
     * <li>创建配置</li>
     * <li>创建几个 男/女 朋友</li>
     * <li>发消息</li>
     */
    public static void main(String[] args) {


        Bootstrap.init();
        // new 一个 女友
        GirlFriend wang = new GirlFriend("准新郎",
                "北京市",
                "顺义区",
                "1998-03-10",
                "2022-02-04",
                "oaiup5nPe2aGJ24Uc2nQI_sJK7Yw",
                "2022-02-03",
                "", "","");

        GirlFriend zhang = new GirlFriend("准新娘",
                "安阳市-滑县",
                "半坡店乡",
                "1998-03-10",
                "2022-02-04",
                "oaiup5lY17LhWIOqwu5hMBnUKynY",
                "2022-02-03",
                "", "","");

        GirlFriend wei = new GirlFriend("司仪（魏大帅哥）",
                "安阳市-滑县",
                "半坡店乡",
                "1998-03-10",
                "2022-02-04",
                "oaiup5isJ4GTYVhxvOWNt5y6ao9A",
                "2022-02-03",
                "", "","");


        GirlFriend jiu = new GirlFriend("场控（张大帅哥）",
                "安阳市-滑县",
                "半坡店乡",
                "1998-03-10",
                "2022-02-04",
                "oaiup5vkEoWFYMyilyDiK_0Eq6zw",
                "2022-02-03",
                "", "","");


        GirlFriend han = new GirlFriend("摄影师（韩大美女）",
                "安阳市-滑县",
                "半坡店乡",
                "1998-03-10",
                "2022-02-04",
                "oaiup5qYRTi9qjwXDN_Yee6lAi7E",
                "2022-02-03",
                "", "","");


        GirlFriend xiao = new GirlFriend("摄影师（张大美女）",
                "安阳市-滑县",
                "半坡店乡",
                "1998-03-10",
                "2022-02-04",
                "oaiup5lm6bBKzvX1kCrilg9hGcjA",
                "2022-02-03",
                "", "","");

        GirlFriend fan = new GirlFriend("摄影师（樊大美女）",
                "安阳市-滑县",
                "半坡店乡",
                "1998-03-10",
                "2022-02-04",
                "oaiup5t3scWiV5hA88m-74CF-KGc",
                "2022-02-03",
                "", "","");


        ArrayList<GirlFriend> girlFriends = new ArrayList<>();

        girlFriends.add(wang);
        girlFriends.add(zhang);

        girlFriends.add(xiao);
        girlFriends.add(han);
        girlFriends.add(jiu);
        girlFriends.add(wei);
        girlFriends.add(fan);

        for (GirlFriend girlFriend : girlFriends) {
            weixin(girlFriend);
        }


        System.err.println("发送成功");


    }

    public static void weixin(GirlFriend  wypFriend){

        String nextdata= "2024-02-05";//下次见面时间

        //下次见面时间
        wypFriend.setNextTime(nextdata);
        wypFriend = lunarTime(wypFriend);


        int wyp = 0;
        while (true){
            try{
                wyp++;
                Wx.sendTemplateMessage(MessageFactory.resolveMessage(wypFriend));
                break;
            }catch (Exception e){

                System.out.println("王云鹏微信失败;一共执行了 "+wyp+" 次");
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException t){
                    System.out.println("王云鹏休息异常");
                }
            }

        }
    }


    public static GirlFriend lunarTime (GirlFriend friend){

        String subtract = subtract(friend.getNextTime());
        friend.setNextTime(subtract);


        // 获取当前年
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        /**
         * 生日 阴历转阳历
         */
        LunarDate lunarDate =  new LunarDate();
        String birthday = friend.getBirthday();
        //1998-03-10
        String[] split = birthday.split("-");
        String mon = split[1];
        String day = split[2];
        lunarDate.setYear(year);
        lunarDate.setMonth(Integer.valueOf(mon));
        lunarDate.setDay(Integer.valueOf(day));
        Calendarist c = Calendarist.fromLunar(lunarDate);
        SolarDate solarDate = c.toSolar();
        int month = solarDate.getMonth();
        int day1 = solarDate.getDay();
        friend.setBirthday(split[0]+ "-"+month+"-"+day1);





        Date time = calendar.getTime();
        time.setYear(5);
        time.setDate(1);
        Date date = new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String dateTime = df.format(time);
        Date wuyi = null;
        Date shiyi = null;
        Date zqda = null;
        Date yd = null;
        Date chuxidata= null;
        Date duanwu= null;
        try {
            //五一
            wuyi  = df.parse( calendar.get(Calendar.YEAR) + "-05-01");
            Long w = (wuyi.getTime() - date.getTime())/24/60/60/1000;
            if(w < 0 ){
                wuyi  = df.parse( (calendar.get(Calendar.YEAR)+1) + "-05-01");
                w = (wuyi.getTime() - date.getTime())/24/60/60/1000;
            }
            friend.setWuyi(String.valueOf(w));

            //十一
            shiyi  = df.parse( calendar.get(Calendar.YEAR) + "-10-01");
            Long s = (shiyi.getTime() - date.getTime())/24/60/60/1000;
            if (s < 0 ){
                shiyi  = df.parse( (calendar.get(Calendar.YEAR)+1) + "-10-01");
                s = (shiyi.getTime() - date.getTime())/24/60/60/1000;
            }
            friend.setShiyi(String.valueOf(s));
            //中秋
            lunarDate.setYear(year);
            lunarDate.setMonth(8);
            lunarDate.setDay(15);
            Calendarist zq = Calendarist.fromLunar(lunarDate);
            SolarDate zqSolarDate = zq.toSolar();
            zqda = df.parse(zqSolarDate.getYear() + "-" + zqSolarDate.getMonth() + "-" + zqSolarDate.getDay());
            Long z = (zqda.getTime() - date.getTime())/24/60/60/1000;
            if (z < 0 ){
                zqda = df.parse((zqSolarDate.getYear() + 1) + "-" + zqSolarDate.getMonth() + "-" + zqSolarDate.getDay());
                z = (zqda.getTime() - date.getTime())/24/60/60/1000;
            }
            friend.setZhongqiui(String.valueOf(z));

            //端午
            lunarDate.setYear(year);
            lunarDate.setMonth(5);
            lunarDate.setDay(5);
            Calendarist dw = Calendarist.fromLunar(lunarDate);
            SolarDate dwSolarDate = dw.toSolar();
            duanwu = df.parse(dwSolarDate.getYear() + "-" + dwSolarDate.getMonth() + "-" + dwSolarDate.getDay());
            Long dwj = (duanwu.getTime() - date.getTime())/24/60/60/1000;
            if (dwj < 0 ){
                duanwu = df.parse((dwSolarDate.getYear() + 1) + "-" + dwSolarDate.getMonth() + "-" + dwSolarDate.getDay());
                dwj = (duanwu.getTime() - date.getTime())/24/60/60/1000;
            }
            friend.setDuanwu(String.valueOf(dwj));

            //元旦
            yd = df.parse(calendar.get(Calendar.YEAR) + "-01-01");
            Long y =  (yd.getTime() - date.getTime())/24/60/60/1000;
            if ( y < 0 ){
                yd = df.parse((calendar.get(Calendar.YEAR) + 1) + "-01-01");
                y =  (yd.getTime() - date.getTime())/24/60/60/1000;
            }
            friend.setYuandan(String.valueOf(y));
            //除夕
            lunarDate.setYear(year +1 );
            lunarDate.setMonth(1);
            lunarDate.setDay(1);
            Calendarist cx = Calendarist.fromLunar(lunarDate);
            SolarDate cxdata = cx.toSolar();
            chuxidata = df.parse(cxdata.getYear() + "-" + cxdata.getMonth() + "-" + cxdata.getDay());
            Long cc = (chuxidata.getTime() - date.getTime() )/24/60/60/1000;
            friend.setChuxi(String.valueOf(cc));



            System.out.println( "距离==>"+"五一:"+ w + "中秋:"+s+"国庆:"+z + "元旦：" + y + "除夕："+ cc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  friend;
    }

    public static String subtract (String nextdata){

        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date star = new Date();//开始时间
            Date endDay=dft.parse(nextdata);//结束时间
            Long starTime=star.getTime();
            Long endTime=endDay.getTime();
            Long num=endTime-starTime;//时间戳相差的毫秒数
            Long day = num/24/60/60/1000;
            System.out.println("相差天数为："+num/24/60/60/1000);//除以一天的毫秒数
            return String.valueOf(day);
        } catch (ParseException e) {

        }
        return  "";
    }

}
