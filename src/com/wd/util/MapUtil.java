package com.wd.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 地图工具类
 */
public class MapUtil {
    //返回地图二维数组
    public static int[][] initMap(String fileName){
        int[][] data = new int[15][20];
        List<String> lines = getLineData(fileName);
        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i).split(",");
            for (int j = 0; j < line.length; j++) {
                data[i][j] = Integer.parseInt(line[j]);
            }
        }
        return data;
    }

    private static List<String> getLineData(String fileName){
        List<String> list = new ArrayList<>();
        String path = "map/"+fileName;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line = null;
            while((line=br.readLine())!=null){
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] map = new MapUtil().initMap("");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
