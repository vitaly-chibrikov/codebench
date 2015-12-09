package stack;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test11 {



        public static void main(String[] args) throws Exception {


        //map elements
         Map map = new HashMap(); 
          map.put("Test1", "file:///C://Screenshots//1.jpg");
          map.put("Mahnaz", "C:\\Screenshots\\2.jpg");
          map.put("Ayan", "C:\\Screenshots\\3.jpg");
          map.put("Daisy", "C:\\Screenshots\\2.jpg");


          htmlGenerator(map);

        //map elements


                    }

    public static  String latex(String tex) {
        String url = "c://" + tex;
        System.out.println("<img src=\"" + url + "\"/>");
        System.out.println("============================================");
        return "<img src=\"" + url + "\"/>";

    }

    public static void htmlGenerator(Map map) throws Exception
    {

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("</head>");
        sb.append("<table>");
        sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> StepName");
        sb.append("</th>");
        sb.append("<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> image");
        sb.append("</th>");


        String imgWidth = " \"500\" ";
        String imgHeight = " \"500\" ";
        //String img = "<img src="/https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/HTML5_logo_and_wordmark.svg/2000px-HTML5_logo_and_wordmark.svg.png"/ width="/500"/ height="/500"/ />";
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
        //  System.out.println("The key is: " + mapEntry.getKey()
                //+ ",value is :" + mapEntry.getValue());


            sb.append("<tr>");
            sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + mapEntry.getKey());
            sb.append("</td>");
            sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " +"<image src="+" \" "+ mapEntry.getValue()+ " \" " +"width="+ imgWidth+"height ="+imgHeight+"</image>");
            sb.append("</td>");     
        }
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");

        //System.out.println(sb.toString());
        // writing to file
        File file = new File("C://firstReport.html");

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString());
        bw.close();

        System.out.println("Done");


    }
    }