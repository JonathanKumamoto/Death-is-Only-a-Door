# Death-is-Only-a-Door
**This was my very first mobile project which started on July 1, 2014 and ended October 1, 2014**

A repository for a game engine programmed in Java for Libgdx

A unique aspect of this respository is the powerful use of an xml parser to create a game engine which will
"load" and "unload" images in the "text" asset folder. An xml game engine, that is manipulatable for multiple objects 
in multiple dimensions is achieved. While not shown in the direct code of the xml page, a "stair" or "stairs" object can
be implemented inside the Libgdx game (using Java) through the use of the game engine, specifying the stair position, size,
and facing the left/right direction.

All objects in the DOD(Death is Only a Door) game engine, are able to be in different or both dimensions of the game-state. More importantly,
the stairs object will "kill" the main character if it changes dimensions and the character is still inside the "stairs"
object.

libGDX Multi-Platform library: Library used.
https://libgdx.badlogicgames.com

**History of app (was published to the Google Play Store):**

1. Publish to the Google Play store on October 1, 2014
2. By February 1, 2015 the app had 5,000-10,000 downloads marked. The app's rating was at 4.3 stars with 27 reviews total.
3. App was removed on due to lack of start menu which did not follow Google Play's publishing requirements.
4. End of app's life, and lesson learned.

## Code Example

The main portion of what makes this project so useful is the XmlLoader class
```java
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
```

## Motivation

There are many different functions that the character is able to do, in which any user or game programmer would find useful.
I created this game engine as well as the objects such as "stairs" because the provided libaries for libgdx
and object and game representation did not do what I wanted (Box2d), and this 'engine' provided the freedom I needed to express my creativity and thought. 

For libgdx users and java game developers concerning character movement and object collision, I have figured out the algorithm and implemention for characters to jump/bounce, stick (to an object), slide (while stuck on an object), die, level-completion, and climb stairs (using a tringle/polygon object collision algorithm) for multiple dimensions. 

For libgdx users and java game developers  concerning memory management, I have created an xml parser so that levels are able to be created on 1-sheet, "loading" and "unloading" system integrated with xml to reduce mobile memory/ram consumption, and correctly calling the java "garbage" collector in order to reduce and remove un-needed data. 

## Installation

*Work in progress*

## Contributors

Jonathan Simeon Kumamoto: Creator,designer, and editor.

## License

Grant of Copyright License. Subject to the terms and conditions of this License, each Contributor hereby grants to You a perpetual, worldwide, non-exclusive, no-charge, royalty-free, irrevocable copyright license to reproduce, prepare Derivative Works of, publicly display, publicly perform, sublicense, and distribute the Work and such Derivative Works in Source or Object form.

http://www.apache.org/licenses/LICENSE-2.0

