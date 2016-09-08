package com.example.sunnyweather.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/6.
 */
public class Bean {

    /**
     * aqi : {"city":{"aqi":"33","co":"0","no2":"16","o3":"67","pm10":"33","pm25":"14","qlty":"优","so2":"2"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2016-09-02 16:52","utc":"2016-09-02 08:52"}}
     * daily_forecast : [{"astro":{"sr":"05:43","ss":"18:44"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-09-02","hum":"33","pcpn":"0.0","pop":"0","pres":"1002","tmp":{"max":"28","min":"19"},"vis":"10","wind":{"deg":"309","dir":"无持续风向","sc":"微风","spd":"6"}},{"astro":{"sr":"05:44","ss":"18:42"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2016-09-03","hum":"34","pcpn":"0.0","pop":"31","pres":"1006","tmp":{"max":"29","min":"20"},"vis":"10","wind":{"deg":"190","dir":"无持续风向","sc":"微风","spd":"10"}},{"astro":{"sr":"05:45","ss":"18:41"},"cond":{"code_d":"300","code_n":"101","txt_d":"阵雨","txt_n":"多云"},"date":"2016-09-04","hum":"61","pcpn":"1.4","pop":"67","pres":"1010","tmp":{"max":"29","min":"19"},"vis":"10","wind":{"deg":"127","dir":"无持续风向","sc":"微风","spd":"6"}},{"astro":{"sr":"05:46","ss":"18:39"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2016-09-05","hum":"34","pcpn":"0.0","pop":"3","pres":"1007","tmp":{"max":"29","min":"21"},"vis":"10","wind":{"deg":"200","dir":"无持续风向","sc":"微风","spd":"8"}},{"astro":{"sr":"05:47","ss":"18:38"},"cond":{"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"},"date":"2016-09-06","hum":"24","pcpn":"0.1","pop":"10","pres":"1003","tmp":{"max":"30","min":"22"},"vis":"10","wind":{"deg":"306","dir":"无持续风向","sc":"微风","spd":"10"}},{"astro":{"sr":"05:48","ss":"18:36"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2016-09-07","hum":"31","pcpn":"1.4","pop":"11","pres":"1002","tmp":{"max":"29","min":"18"},"vis":"10","wind":{"deg":"335","dir":"无持续风向","sc":"微风","spd":"1"}},{"astro":{"sr":"05:49","ss":"18:34"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2016-09-08","hum":"38","pcpn":"6.7","pop":"20","pres":"1005","tmp":{"max":"30","min":"19"},"vis":"10","wind":{"deg":"192","dir":"无持续风向","sc":"微风","spd":"1"}}]
     * hourly_forecast : [{"date":"2016-09-02 19:00","hum":"53","pop":"0","pres":"1003","tmp":"28","wind":{"deg":"307","dir":"西北风","sc":"微风","spd":"9"}},{"date":"2016-09-02 22:00","hum":"61","pop":"0","pres":"1004","tmp":"25","wind":{"deg":"307","dir":"西北风","sc":"微风","spd":"8"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"28","hum":"40","pcpn":"0","pres":"1002","tmp":"27","vis":"10","wind":{"deg":"50","dir":"西南风","sc":"3-4","spd":"11"}}
     * status : ok
     * suggestion : {"comf":{"brf":"较舒适","txt":"白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"sport":{"brf":"较适宜","txt":"天气较好，户外运动请注意防晒。推荐您进行室内运动。"},"trav":{"brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
     */

    private List<HeWeatherDataServiceBean> HeWeather_data_service;

    public static List<Bean> arrayBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<Bean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public List<HeWeatherDataServiceBean> getHeWeather_data_service() {
        return HeWeather_data_service;
    }

    public void setHeWeather_data_service(List<HeWeatherDataServiceBean> HeWeather_data_service) {
        this.HeWeather_data_service = HeWeather_data_service;
    }

    public static class HeWeatherDataServiceBean {
        /**
         * city : {"aqi":"33","co":"0","no2":"16","o3":"67","pm10":"33","pm25":"14","qlty":"优","so2":"2"}
         */

        private AqiBean aqi;
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.904000
         * lon : 116.391000
         * update : {"loc":"2016-09-02 16:52","utc":"2016-09-02 08:52"}
         */

        private BasicBean basic;
        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : 28
         * hum : 40
         * pcpn : 0
         * pres : 1002
         * tmp : 27
         * vis : 10
         * wind : {"deg":"50","dir":"西南风","sc":"3-4","spd":"11"}
         */

        private NowBean now;
        private String status;
        /**
         * comf : {"brf":"较舒适","txt":"白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"}
         * sport : {"brf":"较适宜","txt":"天气较好，户外运动请注意防晒。推荐您进行室内运动。"}
         * trav : {"brf":"适宜","txt":"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。"}
         * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
         */

        private SuggestionBean suggestion;
        /**
         * astro : {"sr":"05:43","ss":"18:44"}
         * cond : {"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"}
         * date : 2016-09-02
         * hum : 33
         * pcpn : 0.0
         * pop : 0
         * pres : 1002
         * tmp : {"max":"28","min":"19"}
         * vis : 10
         * wind : {"deg":"309","dir":"无持续风向","sc":"微风","spd":"6"}
         */

        private List<DailyForecastBean> daily_forecast;
        /**
         * date : 2016-09-02 19:00
         * hum : 53
         * pop : 0
         * pres : 1003
         * tmp : 28
         * wind : {"deg":"307","dir":"西北风","sc":"微风","spd":"9"}
         */

        private List<HourlyForecastBean> hourly_forecast;

        public static List<HeWeatherDataServiceBean> arrayHeWeatherDataServiceBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<HeWeatherDataServiceBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyForecastBean> getHourly_forecast() {
            return hourly_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastBean> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }

        public static class AqiBean {
            /**
             * aqi : 33
             * co : 0
             * no2 : 16
             * o3 : 67
             * pm10 : 33
             * pm25 : 14
             * qlty : 优
             * so2 : 2
             */

            private CityBean city;

            public static List<AqiBean> arrayAqiBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<AqiBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public static class CityBean {
                private String aqi;
                private String co;
                private String no2;
                private String o3;
                private String pm10;
                private String pm25;
                private String qlty;
                private String so2;

                public static List<CityBean> arrayCityBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<CityBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }
        }

        public static class BasicBean {
            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            /**
             * loc : 2016-09-02 16:52
             * utc : 2016-09-02 08:52
             */

            private UpdateBean update;

            public static List<BasicBean> arrayBasicBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<BasicBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                private String loc;
                private String utc;

                public static List<UpdateBean> arrayUpdateBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<UpdateBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class NowBean {
            /**
             * code : 101
             * txt : 多云
             */

            private CondBean cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            /**
             * deg : 50
             * dir : 西南风
             * sc : 3-4
             * spd : 11
             */

            private WindBean wind;

            public static List<NowBean> arrayNowBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<NowBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class CondBean {
                private String code;
                private String txt;

                public static List<CondBean> arrayCondBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<CondBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBean {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public static List<WindBean> arrayWindBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<WindBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class SuggestionBean {
            /**
             * brf : 较舒适
             * txt : 白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。
             */

            private ComfBean comf;
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            private CwBean cw;
            /**
             * brf : 热
             * txt : 天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。
             */

            private DrsgBean drsg;
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，无明显降温过程，发生感冒机率较低。
             */

            private FluBean flu;
            /**
             * brf : 较适宜
             * txt : 天气较好，户外运动请注意防晒。推荐您进行室内运动。
             */

            private SportBean sport;
            /**
             * brf : 适宜
             * txt : 天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。
             */

            private TravBean trav;
            /**
             * brf : 弱
             * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
             */

            private UvBean uv;

            public static List<SuggestionBean> arraySuggestionBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SuggestionBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public ComfBean getComf() {
                return comf;
            }

            public void setComf(ComfBean comf) {
                this.comf = comf;
            }

            public CwBean getCw() {
                return cw;
            }

            public void setCw(CwBean cw) {
                this.cw = cw;
            }

            public DrsgBean getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgBean drsg) {
                this.drsg = drsg;
            }

            public FluBean getFlu() {
                return flu;
            }

            public void setFlu(FluBean flu) {
                this.flu = flu;
            }

            public SportBean getSport() {
                return sport;
            }

            public void setSport(SportBean sport) {
                this.sport = sport;
            }

            public TravBean getTrav() {
                return trav;
            }

            public void setTrav(TravBean trav) {
                this.trav = trav;
            }

            public UvBean getUv() {
                return uv;
            }

            public void setUv(UvBean uv) {
                this.uv = uv;
            }

            public static class ComfBean {
                private String brf;
                private String txt;

                public static List<ComfBean> arrayComfBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<ComfBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CwBean {
                private String brf;
                private String txt;

                public static List<CwBean> arrayCwBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<CwBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class DrsgBean {
                private String brf;
                private String txt;

                public static List<DrsgBean> arrayDrsgBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<DrsgBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class FluBean {
                private String brf;
                private String txt;

                public static List<FluBean> arrayFluBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<FluBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class SportBean {
                private String brf;
                private String txt;

                public static List<SportBean> arraySportBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<SportBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class TravBean {
                private String brf;
                private String txt;

                public static List<TravBean> arrayTravBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<TravBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class UvBean {
                private String brf;
                private String txt;

                public static List<UvBean> arrayUvBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<UvBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * sr : 05:43
             * ss : 18:44
             */

            private AstroBean astro;
            /**
             * code_d : 101
             * code_n : 101
             * txt_d : 多云
             * txt_n : 多云
             */

            private CondBean cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            /**
             * max : 28
             * min : 19
             */

            private TmpBean tmp;
            private String vis;
            /**
             * deg : 309
             * dir : 无持续风向
             * sc : 微风
             * spd : 6
             */

            private WindBean wind;

            public static List<DailyForecastBean> arrayDailyForecastBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DailyForecastBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                private String sr;
                private String ss;

                public static List<AstroBean> arrayAstroBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<AstroBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBean {
                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public static List<CondBean> arrayCondBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<CondBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                private String max;
                private String min;

                public static List<TmpBean> arrayTmpBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<TmpBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBean {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public static List<WindBean> arrayWindBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<WindBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class HourlyForecastBean {
            private String date;
            private String hum;
            private String pop;
            private String pres;
            private String tmp;
            /**
             * deg : 307
             * dir : 西北风
             * sc : 微风
             * spd : 9
             */

            private WindBean wind;

            public static List<HourlyForecastBean> arrayHourlyForecastBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<HourlyForecastBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class WindBean {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public static List<WindBean> arrayWindBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<WindBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
