package com.kt.airmap.etc;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class TownCodeService {

/*
	1) http://www.kma.go.kr/DFSROOT/POINT/DATA/top.json.txt 에서 "서울특별시" 에 해당하는 지역코드(11)를 얻는다.

	2) http://www.kma.go.kr/DFSROOT/POINT/DATA/mdl.11.json.txt 에서 "서초구"에 해당하는 지역코드(11650)를 얻는다.

	3) http://www.kma.go.kr/DFSROOT/POINT/DATA/leaf.11650.json.txt 에서 "반포1동"에 해당하는 동네예보 좌표(x=60,y=125)를 얻는다.
*/
			
	//최상 노드 URL
    private final String topURL = "http://www.kma.go.kr/DFSROOT/POINT/DATA/top.json.txt";
    //중간 노드 URL
    private final String mdlURL = "http://www.kma.go.kr/DFSROOT/POINT/DATA/mdl.%s.json.txt";        
    //최하 노드 URL
    private final String leafURL = "http://www.kma.go.kr/DFSROOT/POINT/DATA/leaf.%s.json.txt";
    
    //KEY정보
    private final String KEY_CODE = "code";
    private final String KEY_VALUE = "value";
    private final String KEY_X = "x";
    private final String KEY_Y = "y";
    
    //각각의 노드를 저장할 리스트
    private List<TownDTO> topList = new ArrayList<TownDTO>();
    private List<TownDTO> mdlList = new ArrayList<TownDTO>();
    private List<TownDTO> leafList = new ArrayList<TownDTO>();
    
    //생성자
    public TownCodeService() {
       // excute();
    }    
    
    
    public void excute() {
        
        JSONArray jsonArray = null;

        //최고 노드
        jsonArray = getJSON(topURL);
        parseJSON(topList, jsonArray, null, null,null,null, false);
        System.out.println("=========");
        
        //중간 노드
        for(TownDTO dto : topList) {
        	jsonArray = getJSON(String.format(mdlURL,  dto.getCode()));
            parseJSON(mdlList, jsonArray, dto.getCode(), dto.getName(),null, null, false);
        }
        
        System.out.println("=========");
        
        //최하 노드
        for(TownDTO dto : mdlList) {
        	jsonArray = getJSON(String.format(leafURL, dto.getCode()));
        	parseJSON(leafList, jsonArray, dto.getCode(), dto.getName(), dto.getParentCode(),dto.getParentName() ,true);
        }     
        
     
    }
 
	
    
    /**
     * 지정된 URL을 기초로 JSON형식으로 취득 
     */
    private JSONArray getJSON(String urlStr) {
        URL url = null;
        HttpURLConnection conn = null;
        StringBuffer jsonHtml = new StringBuffer();
        
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            
            if(conn != null) {
                conn.setConnectTimeout(10000); //접속 응답 대기 시간 , 10초 
                conn.setUseCaches(false); //캐쉬기능 사용안함.
                
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                    
                    String line = null;
                    while ((line = br.readLine())!= null){
                        jsonHtml.append(line + "\n");
                    }
                    br.close();
                }
                conn.disconnect(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("1."+jsonHtml.toString());
        JSONArray jsonObj = (JSONArray) JSONValue.parse(jsonHtml.toString());
        
        return jsonObj;
    }
    
    /**
     * 파싱된 노드 리스트 -> TownDTO 형식으로 변환
     * @param list 변환결과를 담을 리스트 
     * @param array 현재 노드 리스트 
     * @param parentCode 부모 노드 코드 
     * @param parentName 부모 노드 이름 
     * @param isLast 최하노드인가?
     */    
    private List<TownDTO> parseJSON(List<TownDTO> list, JSONArray array, 
                             String parentCode, String parentName, String pParentCode, String pParentName, boolean isLast) {
        
        JSONObject data = null;
        TownDTO town = null;
        for (int i = 0; i < array.size(); i++) {
            data = (JSONObject) array.get(i);
            
            if(!isLast) {
                //최상, 중간 노드
                town = new TownDTO(data.get(KEY_CODE).toString(), 
                                   data.get(KEY_VALUE).toString(),
                                   parentCode, 
                                   parentName, 
                                   pParentCode,
                                   pParentName);
                
            } else {
                //최하 노드
                town = new TownDTO(data.get(KEY_CODE).toString(),
                                   data.get(KEY_VALUE).toString(),
                                   parentCode,
                                   parentName,
                                   pParentCode,
                                   pParentName,
                                   data.get(KEY_X).toString(),
                                   data.get(KEY_Y).toString());
        
                System.out.println("==============> "+ town);
              
            }
            
            System.out.println(town);
        	list.add(town);
            
            	
        }
        return list;
    }
    
    
  //파일 출력
    public void outputFile(String path) {        
        
        if(path == null || path.trim().equals("")) {
           // path = "file//output.txt";  
        	 path = "d:\\output.txt"; 
        }
        
        System.out.println("결과:"
                + topList.size()+"개 /"+mdlList.size()+"개 /"+leafList.size() + "개");
        
        try {            
            PrintStream out = new PrintStream(new FileOutputStream(path));
            
            //최고 노드
            for (TownDTO dto : topList) {
                out.println(dto);
            }
            
            //중간 노드
            for (TownDTO dto : mdlList) {
                out.println(dto);
            }
            
            //최하노드
            for (TownDTO dto : leafList) {
                out.println(dto);
            }
            
            out.close();            
        } catch (Exception e) {            
            e.printStackTrace();
        }
        
    }
    //테스트    
    public static void main(String[] args) {
        TownCodeService t = new TownCodeService();
        t.excute();
    //    t.outputFile(null);
    }

}
