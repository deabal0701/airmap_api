package com.kt.airmap.base.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
* 클래스명    : 날짜 관련 유틸리티.
* @ClassDesc  : 날짜 관련 유틸리티.
* @FileName   : LGDateUtil.java
*
* <pre>
 [time pattern]

 Symbol   Meaning                 Presentation        Example
 ------   -------                 ------------        -------
 G        era designator          (Text)              AD
 y        year                    (Number)            1996
 M        month in year           (Text & Number)     July & 07
 d        day in month            (Number)            10
 h        hour in am/pm (1~12)    (Number)            12
 H        hour in day (0~23)      (Number)            0
 m        minute in hour          (Number)            30
 s        second in minute        (Number)            55
 S        millisecond             (Number)            978
 E        day in week             (Text)              Tuesday
 D        day in year             (Number)            189
 F        day of week in month    (Number)            2 (2nd Wed in July)
 w        week in year            (Number)            27
 W        week in month           (Number)            2
 a        am/pm marker            (Text)              PM
 k        hour in day (1~24)      (Number)            24
 K        hour in am/pm (0~11)    (Number)            0
 z        time zone               (Text)              Pacific Standard Time
 '        escape for text         (Delimiter)
 ''       single quote            (Literal)           '

* </pre>
*
* @see java.util.SimpleDateFormat
* @TableName  :
* @SpecDesc   : N/A
* @Author     : 
* @history 수정일자 / 수정자 / 수정내용
* 2004.10.06 /  / 최초작성
*/
public class DateUtil {
	/** 생성자 */
	public DateUtil() {}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 오늘날짜를 pattern 포맷으로 생성한다.
	* @param pattern date format pattern
	* @return formatted current date
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getSystemDate(String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);
		return formatter.format(new Date());
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 현재년월에서입력받은 수에 해당하는 만큼 뒤의 년월을 구한다.
	* @param gapDate 기간
	* @return formatted date
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String advance( int gapDate) {
		Date today = new Date() ;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String srcDate = (String)formatter.format(today) ;
		formatter.getCalendar().set(Integer.parseInt(srcDate.substring(0,4)),
						Integer.parseInt(srcDate.substring(4,6))-1,
						Integer.parseInt(srcDate.substring(6,8)),0,0,0);
		formatter.getCalendar().add(Calendar.MONTH, gapDate) ;
		long _time = formatter.getCalendar().getTime().getTime();
		Date _retDay = new Date(_time) ;
		String _f = (String)formatter.format(_retDay) ;
		String retDate = _f.substring(0, 8) ;
		return (retDate) ;
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : originDate 날짜로부터 flag[year, month, day]에 대한
	*  gap 만큼의 날짜 구하기
	* @param originDate 기준이 되는 날짜 (YYYYMMDD 포맷)
	* @param flag 0 : day 기준, 1 : month 기준,  2 : year 기준
	* @param gapValue the data that has to be decrypted
	* @return YYYYMMDD
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String advance(String originDate, int flag, int gapValue) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		formatter.getCalendar().set(Integer.parseInt(originDate.substring(0,4)),
						Integer.parseInt(originDate.substring(4,6))-1,
						Integer.parseInt(originDate.substring(6,8)),0,0,0);
		if( flag == 0 ) // 일 계산
			formatter.getCalendar().add(Calendar.DATE, gapValue) ;
		else if ( flag == 1 ) // 월 계산 
			formatter.getCalendar().add(Calendar.MONTH, gapValue) ;
		else if ( flag == 2 ) // 년 계산 
			formatter.getCalendar().add(Calendar.YEAR, gapValue) ;
		else if ( flag == 3 ) // 시간 계산 
			formatter.getCalendar().add(Calendar.HOUR, gapValue) ;
		else if ( flag == 4 ) // 분 계산 
			formatter.getCalendar().add(Calendar.MINUTE, gapValue) ;
		else if ( flag == 5 ) // 초 계산 
			formatter.getCalendar().add(Calendar.SECOND, gapValue) ;
		
		long _time = formatter.getCalendar().getTime().getTime();
		Date _retDay = new Date(_time) ;
		String _f = (String)formatter.format(_retDay) ;
		String retDate = _f.substring(0, 8) ;
		return (retDate) ;
	}
	
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : originDate 날짜로부터 flag[year, month, day]에 대한
	*  gap 만큼의 날짜 구하기
	* @param originDate 기준이 되는 날짜 (YYYYMMDD 포맷)
	* @param flag 0 : day 기준, 1 : month 기준,  2 : year 기준
	* @param gapValue the data that has to be decrypted
	* @return YYYYMMDD
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String advanceDash(String originDate, int flag, int gapValue) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.getCalendar().set(Integer.parseInt(originDate.substring(0,4)),
						Integer.parseInt(originDate.substring(5,7))-1,
						Integer.parseInt(originDate.substring(8,10)),0,0,0);
		if( flag == 0 ) // 일 계산
			formatter.getCalendar().add(Calendar.DATE, gapValue) ;
		else if ( flag == 1 ) // 월 계산 
			formatter.getCalendar().add(Calendar.MONTH, gapValue) ;
		else if ( flag == 2 ) // 년 계산 
			formatter.getCalendar().add(Calendar.YEAR, gapValue) ;
			
		long _time = formatter.getCalendar().getTime().getTime();
		Date _retDay = new Date(_time) ;
		String _f = (String)formatter.format(_retDay) ;
		String retDate = _f.substring(0, 10) ;
		return (retDate) ;
	}
	
	
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 현재년도를 "yyyy" 포맷으로 생성한다.
	* @return HH
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getYear() {
		return getSystemDate("yyyy");
	}
	
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 현재시간을 "HH" 포맷으로 생성한다.
	* @return HH
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getHour() {
		return getSystemDate("HH");
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 현재시간을 "HHmmss" 포맷으로 생성한다.
	* @return HHmmss
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getTime() {
		return getSystemDate("HHmmss");
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : Date/Time 스트링의 포맷을 바꾼다.
	* @param dateString : Date/Time 스트링
	* @param fromPattern : 변환전 포맷
	* @param toPattern : 변환후 포맷
	* @return 변환된 Date/Time 스트링
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String formatDateTime(String dateTime, 
                                        String fromPattern, 
                                        String toPattern) {
		if (dateTime == null || dateTime.trim().length() == 0) return "";

		SimpleDateFormat fromFormatter = new SimpleDateFormat(fromPattern,Locale.KOREA);
		Date date = null;
		try {
			date = fromFormatter.parse(dateTime);
		} catch(ParseException pe) {
			return dateTime;
		}

		SimpleDateFormat toFormatter = new SimpleDateFormat(toPattern,Locale.KOREA);		
		return toFormatter.format(date);
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : yyyyMMdd 형식 날짜 스트링의 포맷을 바꾼다.
	* @param dateString : yyyyMMdd형식의 날짜 스트링
	* @param toPattern : 변환 포맷
	* @return 변환된 날짜 스트링
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String formatDate(String dateString, String toPattern) {
		return formatDateTime(dateString,"yyyyMMdd",toPattern);
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : HHmmss 형식 시간 스트링의 포맷을 바꾼다.
	* 
	* <pre>
	* ex) formatTime("154533","HH:mm:ss");
	*     15시 45분 33초
	* ex) formatTime("174225","HH:mm");
	*     17시 42분
	* </pre>
	*
	* @param timeString : HHmmss형식의 시간 스트링
	* @param toPattern : 변환 포맷
	* @return 변환된 시간 스트링
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String formatTime(String timeString, String toPattern) {
		return formatDateTime(timeString,"HHmmss",toPattern);
	}
	

	
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 받은날자가 무슨요일인지를 알려준다. 일요일 : 0 ~ 토요일 :6
	* @param dateString Date 
	* @return 요일 표시 숫자
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static int getDay(String dateString) {

		//TODO 
		//return 5;

		if((dateString == null) || (dateString.length() < 8))
			return -1;
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dateString.substring(0,4)), Integer.parseInt(dateString.substring(4,6))-1, Integer.parseInt(dateString.substring(6,8)));
		return cal.get(Calendar.DAY_OF_WEEK);


	}
	



	
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 날짜시간 문자열 포멧으로 넘기기 일요일 : 0 ~ 토요일 :6
	* @param Calendar
	* @return String
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getDateTime(Calendar cal)
	{
		if(cal == null) return "";

		StringBuffer sb = new StringBuffer();
	        int [] getDate = new int[6];

	        getDate[0] = cal.get(Calendar.YEAR);
	        getDate[1] = cal.get(Calendar.MONTH) + 1;
	        getDate[2] = cal.get(Calendar.DAY_OF_MONTH);
	        getDate[3] = cal.get(Calendar.HOUR_OF_DAY);
	        getDate[4] = cal.get(Calendar.MINUTE);
	        getDate[5] = cal.get(Calendar.SECOND);
	
		sb.append(Integer.toString(getDate[0]));
         try{
	        for(int i=1;i<=5;i++) {
			    sb.append(StringUtil.addZero(Integer.toString(getDate[i]), 2));
            }

        }catch(Exception e){}
		return sb.toString();
	} //getDateTime(int mode) 
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 오늘 날짜, 시간 구하기 - YYYYMMDDhhmmss
	* @return YYYYMMDDhhmmss
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getThisTime()
	{
		Calendar cal = Calendar.getInstance();
		String toDay = getDateTime(cal);
		return toDay;
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 오늘 날짜 구하기 - YYYYMMDD
	* @return YYYYMMDD
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getToday()
	{
		return getThisTime().substring(0, 8);
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 받은날자의 요일 구하기
	* @param dateString Date 
	* @return DayOfWeek
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static int getDayOfWeek(String dateString)
	{
		return getDay(dateString);
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 받은 날짜의 그달 마지막 일자 구하기
	* @param dateString Date 
	* @param 기준일자 YYYYMM
	* @return LastDayOfMonth
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static int getLastDayOfMonth(String dateString)
	{
		if(dateString == null) return 0;
		if(dateString.length() != 6) return 0;
		if(!StringUtil.isNumeric(dateString)) return 0;

		int last_day=0, year=0, month=0;

		year  = StringUtil.toInt(dateString.substring(0,4));
		month = StringUtil.toInt(dateString.substring(4,6));

		if( month == 1 ||  month == 3 ||  month == 5  ||  month == 7 ||  month == 8 ||  month == 10 ||  month == 12)
			last_day = 31;
		else if(  month == 4 ||  month == 6 ||  month == 9  ||  month == 11 )
			last_day = 30;
		else if( month == 2) {
			if( (year%4) == 0 && (year%100) != 0 || (year%400) == 0 )
				last_day = 29;
			else
				last_day = 28;
		}
		return last_day;
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 받은 날짜에 +-몇달 값 구하기 YYYYMMDD
	* @param dateString 기준일자
	* @param gapMonth 계산달수
	* @return YYYYMMDD
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getMonthCom(String dateString, int gapMonth)
	{
		if(dateString == null) return "";
		if(dateString.length() != 8) return dateString;
		if(!StringUtil.isNumeric(dateString)) return dateString;

		String date_dd = dateString.substring(6);
		Calendar cal = Calendar.getInstance();
		cal.set(StringUtil.toInt(dateString.substring(0,4)), StringUtil.toInt(dateString.substring(4,6))-1+gapMonth, 1);
		String strDate = getDateTime(cal);
		return strDate.substring(0,6) + date_dd;
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 오늘 날짜에 +-몇달 값 구하기 YYYYMMDD
	* @param gapMonth 계산달수
	* @return YYYYMMDD
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getMonthCom(int gapMonth)
	{
		String dateString = getToday();
		return getMonthCom(dateString, gapMonth);
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 받은 날짜에 +-몇일 값 구하기 YYYYMMDD
	* @param dateString 기준일자
	* @param gapDay 계산달수
	* @return YYYYMMDD
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getDayCom(String dateString, int gapDay)
	{
		if(dateString == null) return "";
		if(dateString.length() != 8) return dateString;
		if(!StringUtil.isNumeric(dateString)) return dateString;

		Calendar cal = Calendar.getInstance();
		cal.set(StringUtil.toInt(dateString.substring(0,4)), StringUtil.toInt(dateString.substring(4,6))-1, StringUtil.toInt(dateString.substring(6,8))+gapDay);
		String strDate = getDateTime(cal);
		return strDate.substring(0,8);
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 오늘날짜에 +-몇일 값 구하기
	* @param gapDay 계산달수
	* @return YYYYMMDD
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getDayCom(int gapDay)
	{
		String dateString = getToday();

		Calendar cal = Calendar.getInstance();
		cal.set(StringUtil.toInt(dateString.substring(0,4)), StringUtil.toInt(dateString.substring(4,6))-1, StringUtil.toInt(dateString.substring(6,8))+gapDay);
		String strDate = getDateTime(cal);
		return strDate.substring(0,8);
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 지정한 일자의 현재기준 남은일자, 지난일자 구하기
	* @param getDay 계산할 일자
	* @return YYYYMMDD
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static int getCompDay(String getDay)
	{
	        Calendar c1 = null, c2 = null;
	        Date date1 = null, date2 = null;
	        int yy, mm, dd;
	        long d1,d2;
	
	        if(getDay.equals("") || getDay == null)
	                return 0;
	        yy = Integer.parseInt(getDay.substring(0, 4));
	        mm = Integer.parseInt(getDay.substring(4, 6)) - 1;
	        dd = Integer.parseInt(getDay.substring(6, 8));
	
	        // 객체 생성
	        c1 = Calendar.getInstance(); //생일
	        c2 = Calendar.getInstance(); //현재일
	
	        // 날짜 지정
	        c1.set(yy,mm,dd);
	
	        // date로 형변환
	        date1 = c1.getTime();
	        date2 = c2.getTime();
	
	        // MilliSecond 로 변환
	        d1 = date1.getTime();
	        d2 = date2.getTime();
	
	        return (int)((d2-d1)/(1000*60*60*24));
	} 

	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 지정된 일자들 사이의 일수 구하기
	* @param getDay 계산할 일자
	* @return YYYYMMDD
	* @Author     : 정혜원
	* @history 수정일자 / 수정자 / 수정내용
	* 2007.08.09 / 정혜원 / 최초작성
	*/   
	public static int getCompDay(String preDay, String nextDay)
	{
	        Calendar c1 = null, c2 = null;
	        Date date1 = null, date2 = null;
	        int yy, mm, dd, yy1, mm1, dd1;
	        long d1,d2;
	
	        if(preDay.equals("") || preDay == null || nextDay.equals("") || nextDay == null)
	                return 0;
	        yy = Integer.parseInt(preDay.substring(0, 4));
	        mm = Integer.parseInt(preDay.substring(4, 6)) - 1;
	        dd = Integer.parseInt(preDay.substring(6, 8));
			yy1 = Integer.parseInt(nextDay.substring(0, 4));
	        mm1 = Integer.parseInt(nextDay.substring(4, 6)) - 1;
	        dd1 = Integer.parseInt(nextDay.substring(6, 8));
	
	        // 객체 생성
	        c1 = Calendar.getInstance(); 
	        c2 = Calendar.getInstance(); 
	
			// 날짜 지정
	        c1.set(yy,mm,dd);
			c2.set(yy1,mm1,dd1);
	
	        // date로 형변환
	        date1 = c1.getTime();
	        date2 = c2.getTime();
	
	        // MilliSecond 로 변환
	        d1 = date1.getTime();
	        d2 = date2.getTime();
	
	        return (int)((d2-d1)/(1000*60*60*24));
	} 
	
	
	/**
	* 함수명 :  달 수 차이
	* @FuncDesc : 지정한 일자의 현재기준 사이의 달수 구하기
	* @param  nextMonth 종료 월(형식 : yyyyMM)
	* @return int
	* @history 수정일자 / 수정내용
	* 2014.07.21 / 최초작성
	*/ 
	public static int getCompMonth(String preMonth)
	{
	        int yy, mm,  yy1, mm1;
	        String nextMonth = getToday();
	
	        if(preMonth.equals("") || preMonth == null)
	                return 0;
	        yy = Integer.parseInt(preMonth.substring(0, 4));
	        mm = Integer.parseInt(preMonth.substring(4, 6));
			yy1 = Integer.parseInt(nextMonth.substring(0, 4));
	        mm1 = Integer.parseInt(nextMonth.substring(4, 6));
	
	       return (yy1 - yy) * 12 + (mm1 - mm);
	} 
	
	/**
	* 함수명 :  달 수 차이
	* @FuncDesc : 지정된 일자들 사이의 달수 구하기
	* @param  preMonth 시작 월(형식 : yyyyMM), nextMonth 종료 월(형식 : yyyyMM)
	* @return int
	* @history 수정일자 / 수정내용
	* 2014.07.21 / 최초작성
	*/ 
	public static int getCompMonth(String preMonth, String nextMonth)
	{
	        int yy, mm,  yy1, mm1;
	
	        if(preMonth.equals("") || preMonth == null || nextMonth.equals("") || nextMonth == null)
	                return 0;
	        yy = Integer.parseInt(preMonth.substring(0, 4));
	        mm = Integer.parseInt(preMonth.substring(4, 6));
			yy1 = Integer.parseInt(nextMonth.substring(0, 4));
	        mm1 = Integer.parseInt(nextMonth.substring(4, 6));
	
	       return (yy1 - yy) * 12 + (mm1 - mm);
	} 

	
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 현재 월에서 +-몇달 값 구하기(월 기준) YYYYMM
   	* @param gapMonth 계산달수
   	* @return YYYYMM
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static String getCalMonth(int gapMonth) {
		String dateString = getToday();
		return getMonthCom(dateString, gapMonth).substring(0,6);
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 유효한 영업시간인지 체크
    * @param strOpen-시작시간, int strOpen-종료시간
    * @return Boolean
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static boolean checkWorkHour( int strOpen , int strClose ) {

		// 현재시간 찾기
		SimpleDateFormat strForm = new SimpleDateFormat("yyMMddkkmm");
		String 	strDate = strForm.format( new java.util.Date() ); 
	 
		int nTime = java.lang.Integer.parseInt( strDate.substring(6) );
		
		if (( nTime >= strOpen ) && ( nTime < strClose )) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 유효한 영업시간인지 체크
    * @param strOpen-시작시간, String strOpen-종료시간
    * @return Boolean
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static boolean checkWorkHour( String strOpen , String strClose ) {

		return checkWorkHour(StringUtil.toInt(strOpen), StringUtil.toInt(strClose));

	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 년, 월, 일을 매개변수로 받아서 그일수가 그월의 
	* 몇번째 주에 위치하는지 반환
	* @param 년도
	* @param 월
	* @param 일
	* @ return 1 - 첫째주, 2 - 둘째주
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static int getWeekOfMonth(int nYear, int nMonth, int nDay)
	{
	   Calendar calendarOBJ;
	   DateFormat dateformatADate;
	
	   dateformatADate = DateFormat.getDateInstance(DateFormat.FULL, Locale.KOREAN);
	   calendarOBJ = dateformatADate.getCalendar();
	   calendarOBJ.set(nYear, nMonth-1, nDay);
	
	   return calendarOBJ.get(Calendar.WEEK_OF_MONTH);
	}
	/**
	* 함수명 :  데이터포맷
	* @FuncDesc : 년, 월, 일을 입력받아 그날이 무슨요일인지 얻는다.
	* 몇번째 주에 위치하는지 반환
	* @param 년도
	* @param 월
	* @param 일
	* @return 1 - 일요일, 2 - 월요일.
	* @Author     : 
	* @history 수정일자 / 수정자 / 수정내용
	* 2004.10.06 /  / 최초작성
	*/   
	public static int getDayOfWeek( int nYear, int nMonth, int nDay)
	{
	   Calendar calendarOBJ;
	   DateFormat dateformatADate;
	
	   dateformatADate = DateFormat.getDateInstance( DateFormat.FULL, Locale.KOREAN);
	   calendarOBJ = dateformatADate.getCalendar();
	   calendarOBJ.set( nYear, nMonth-1, nDay);
	
	   return calendarOBJ.get( Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 현재 날짜(yyyyMMddhhmmssSSS) 반환
	 * @return
	 */
	public static String getTimeStamp() {
		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

	    SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
	    Timestamp ts = new Timestamp(System.currentTimeMillis());

		return sdfCurrent.format(ts.getTime());
 }
	
	/**
	 * 함수명 : 계산된 시간을 돌려 준다.
	 * 반환값예: getCalTime(0,-2, 0) 두시간 전 => "20180101132232"
	 * @param nDate
	 * @param nHour
	 * @param nMinute
	 * @return
	 */
	
	public static String getCalTime(int nDate, int nHour, int nMinute){
		
	      String today = null;
		  Date date = new Date();
		  SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMddHHmmss"); 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.add(Calendar.MINUTE, nMinute);
		  cal.add(Calendar.HOUR, nHour);
		  cal.add(Calendar.DATE, nDate);
		  today = sdformat.format(cal.getTime());  

		  return today;
	}
	
	
	/**
	 * 입력한 날짜가 포멧에 맞는 유효한 날짜인지 확인
	 * @param dateToValidate
	 * @param dateFromat
	 * @return
	 */
	public static boolean isDateValid(String dateToValidate, String dateFromat){
		if(dateToValidate == null){
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		try {
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

}