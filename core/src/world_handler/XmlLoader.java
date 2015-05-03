package world_handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
















import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlLoader {

	 public static final String MyCopyright = "Copyright 2014 by Jonathan Simeon Kumamoto"; 
	public static ArrayList<String[]> main(String[] args) { //to me this looks a bit messy
		 																					//maybe look into making it shorter??? :D
		int level = Integer.parseInt(args[0]);
		ArrayList<String[]>hello= new ArrayList<String[]>();
		DocumentBuilderFactory builderFactory  = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dbuilder = builderFactory.newDocumentBuilder();
			Document document = dbuilder.parse(XmlLoader.class.getResourceAsStream("Levels.xml"));
			document.normalize();
			NodeList LevelsList = document.getElementsByTagName("Levels"); //this makes a list 
			///////////////////////////////////////BELOW is: a structure tree of anything with the tag <Levels>
			
			for(int structure_tree = 0; structure_tree < LevelsList.getLength(); structure_tree++){
				Node Root4Level = LevelsList.item(structure_tree);
				Element Element4Level = (Element) Root4Level;
				NodeList Level_Floor = Element4Level.getElementsByTagName("Level_floor");//this is the child of tag "<Levels>"
				
				/////////////////////////////////////BELOW is:a structure tree of anything with the tag <Level_floor>
				for(int i = 0; i < Level_Floor.getLength(); i++){
					Node level_floor_root = Level_Floor.item(i);
					Element Element_Level_floor = (Element) level_floor_root;
					if(level == Integer.parseInt(Element_Level_floor.getAttribute("key"))){ //now this reaches they <key = "1">
						NodeList object_methods = Element_Level_floor.getElementsByTagName("Object");
						
						////////////////////////////////BELOW is: a structure tree of anything with the tag <Object>
						for(int manager_methods = 0; manager_methods < object_methods.getLength(); manager_methods++){
							//INSIDE HERE IS IMPORTANT. this forloop goes through the <Objects> tag of a specific
							//floor level. So organize data carefully and implement them to be called.
							Node object_calls = object_methods.item(manager_methods);
							Element EC = (Element) object_calls; //NOW WE CAN ACQUIRE DATA
							//important information regarding the Elements
							// <objectMethod> = 1 means its addObject   (platform)
							//<objectMethod> = 2 means its addS_Object (stairs)
							//<objectMethod> = 3 means its addK_Object  (saws)
							if(Integer.parseInt(EC.getAttribute("objectMethod")) == 1  //entity, saw, block, jump
									|| Integer.parseInt(EC.getAttribute("objectMethod")) == 3 
									|| Integer.parseInt(EC.getAttribute("objectMethod")) == 6
									|| Integer.parseInt(EC.getAttribute("objectMethod")) == 7){
							String[] method_constructor = {EC.getAttribute("objectMethod"), EC.getAttribute("Dimension"),
									EC.getAttribute("Xpos"), EC.getAttribute("Ypos"), EC.getAttribute("width"),
									EC.getAttribute("height")}; //end of the method_constructor now add it to the array
							hello.add(method_constructor);
							}else if (Integer.parseInt(EC.getAttribute("objectMethod")) == 2){//means its a triangle
								String[] method_constructor = {EC.getAttribute("objectMethod"), EC.getAttribute("Dimension"),
										EC.getAttribute("Xpos"), EC.getAttribute("Ypos"), EC.getAttribute("vx"), 
										EC.getAttribute("vy"), EC.getAttribute("v3"), EC.getAttribute("v4"),
										EC.getAttribute("v5"), EC.getAttribute("v6")};
								hello.add(method_constructor);
							}else if(Integer.parseInt(EC.getAttribute("objectMethod")) == 4){ //means image for level
								String[] method_constructor = {EC.getAttribute("objectMethod"), EC.getAttribute("location"),
										EC.getAttribute("Xpos"),EC.getAttribute("Ypos"), EC.getAttribute("width"),
										EC.getAttribute("height"),EC.getAttribute("type")};
								hello.add(method_constructor);
							}else if(Integer.parseInt(EC.getAttribute("objectMethod")) == 5){ //means image for level
								String[] method_constructor = {EC.getAttribute("objectMethod"),
										EC.getAttribute("x"), EC.getAttribute("y")};
								hello.add(method_constructor);
							}
						}//by the time this forloop ends, all data from levels.xml should be ready to be called
					
					}
				}
			}
			
		return hello;
		//below the { is the end of the Try{		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hello;
	}
	

	

}
