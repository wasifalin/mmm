package com.example.xml_json_parser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
    }
    public void parsexml(View view){
        try{
            InputStream is = getAssets().open("city.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(is);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("XML Data");
            stringBuilder.append("\n----------------------\n");
            NodeList nodeList = document.getElementsByTagName("place");
            for(int i=0;i<nodeList.getLength();i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    stringBuilder.append("\n City name: ").append(getValue("name",element));
                    stringBuilder.append("\n Latitude: ").append(getValue(("lati"),element));
                    stringBuilder.append("\n Longitude: ").append(getValue("long",element));
                    stringBuilder.append("\n Temperature: ").append(getValue("temp",element));
                    stringBuilder.append("\n Humidity: ").append(getValue("humid",element));
                    stringBuilder.append("\n");
                }
            }
            display.setText((stringBuilder.toString()));
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,"Error in Reading XML File",Toast.LENGTH_SHORT).show();
        }
    }
    private String getValue(String tag, Element element){
        return element.getElementsByTagName(tag).item(0).getChildNodes().item(0).getNodeValue();
    }
    public void parsejson(View view){
        String json;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = getAssets().open("city.json");
            int size = is.available();
            byte[] Buffer = new byte[size];
            is.read(Buffer);
            json = new String(Buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);
            stringBuilder.append("JSON Data");
            stringBuilder.append("\n-----------------\n");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                stringBuilder.append("\n City name: ").append(jsonObject.getString("name"));
                stringBuilder.append("\n Latitude: ").append(jsonObject.getString("lati"));
                stringBuilder.append("\n Longitude: ").append(jsonObject.getString("long"));
                stringBuilder.append("\n Temperature: ").append(jsonObject.getString("temp"));
                stringBuilder.append("\n Humidity: ").append(jsonObject.getString("humid"));
                stringBuilder.append("\n");
            }
            display.setText(stringBuilder.toString());
            is.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Error in Reading XML File",Toast.LENGTH_SHORT).show();
        }
    }
}