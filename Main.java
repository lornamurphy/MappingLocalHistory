import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import elements.BasketGoods1971;
import elements.Births1864_1911;
import elements.CarsAntrim;
import elements.CarsArmagh;
import elements.CarsBelfast;
import elements.CarsDerry;
import elements.CarsDown;
import elements.CarsFermanagh;
import elements.CarsTyrone;
import elements.CountyID;
import elements.Emigration1851_1911;
import elements.EmigrationCounty;
import elements.FilmEvent1900s;
import elements.FilmEvent1910s;
import elements.FilmEvent1920s;
import elements.FilmEvent1930s;
import elements.FilmEvent1940s;
import elements.FilmEvent1950s;
import elements.FilmEvent1960s;
import elements.FilmEvent1970s;
import elements.FilmGross1900s;
import elements.FilmGrossUK2000s;
import elements.IndustryFemaleCounty1971;
import elements.IndustryMaleCounty1971;
import elements.Literacy1841;
import elements.Literacy1851;
import elements.Literacy1861;
import elements.Literacy1871;
import elements.Music;
import elements.Music1910s;
import elements.Music1920s;
import elements.Music1930s;
import elements.Music1940s;
import elements.Music1950s;
import elements.News1800s;
import elements.News1900s;
import elements.News2000s;
import elements.PopNIVitalStats;
import elements.PopulationAntrim;
import elements.PopulationArmagh;
import elements.PopulationBelfast;
import elements.PopulationDerry;
import elements.PopulationDown;
import elements.PopulationFermanagh;
import elements.PopulationNI;
import elements.PopulationTyrone;
import elements.NumOne1950s;
import elements.NumOne1960s;
import elements.NumOne1970s;
import elements.NumOne1980s;
import elements.NumOne1990s;
import elements.NumOne2000s;
import elements.NumOne2010s;
import elements.NumOneSongs;
import elements.OccupationAge1831;
import elements.OccupationAge1841;
import elements.OccupationAge1871;
import elements.OccupationAge1881;
import elements.OccupationAge1891;
import elements.OccupationAge1901;
import elements.OccupationAge1911;
import importexport.TextImportExport;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import views.ExampleView;
import views.TestForm;
import web.Base64;
import web.Page;
import web.VoiceRecognition;
import web.WebInterface;
import web.WebRequest;
import web.WebResponse;



//This program is a webserver, the focus is to reduce complexity while demonstrating the key
//components of a web server suitable for web app development

/** This class loads all data files into program. URLs are declared and data is manipulated to 
 * produce variables within an imported SVG file.
 * Data from CSV and TAB files is processed here.
 * @author Lorna
 */
public class Main {
	//This is a main function, it is the first function that is called when a java program starts
	//The array of strings named commandLineParameters are extra information that can be
	//passed to a program when it is run using the command line
	//this is just one of many ways of varying a programs behaviour by passing information to it
	//for example some programs have config files, which are usually simple text files with name=value settings on each line
	//programs with databases (like this one) can store their settings in the database
	//some programs even read properties stored by the operating system in system variables

	public static void main(String[] commandLineParameters) throws Exception {		

		String templatepage = ""; // creates a String for the page

		PopulationNI niPop = new PopulationNI(); //creates instance of this class
		niPop.load("population_NI_1841_2019.csv");

		PopNIVitalStats niVitals = new PopNIVitalStats();
		niVitals.load("NI_population_vital_stats_1900_2017.csv");

		Music mus1900s = new Music();
		mus1900s.load("songs1900s.csv", "songs1910s.csv", "songs1920s.csv", "songs1930s.csv", "songs1940s.csv", "songs1950s.csv");

		NumOneSongs numOnes = new NumOneSongs();
		numOnes.load("UK_num1_1950s.csv", "UK_num1_1960s.csv", "UK_num1_1970s.csv", "UK_num1_1980s.csv", "UK_num1_1990s.csv", "UK_num1_2000s.csv", "UK_num1_2010s.csv");

		FilmGross1900s filmGross1900s = new FilmGross1900s();
		filmGross1900s.load("highest_gross_film_1900s.csv");

		FilmGrossUK2000s filmGross2000s = new FilmGrossUK2000s();
		filmGross2000s.load("highest_gross_film_uk_2000s.csv");

		Births1864_1911 birthsPerYear = new Births1864_1911();
		birthsPerYear.load("births_ire.csv");

		Emigration1851_1911 emigIreland = new Emigration1851_1911();
		emigIreland.load("emigire.csv");

		News1800s news1800s = new News1800s();
		news1800s.load("worldevents_1801_1900_2.txt");

		News1900s news1900s = new News1900s();
		news1900s.load("worldtimeline_1901_1999.tab");

		News2000s news2000s = new News2000s();
		news2000s.load("worldtimeline_2000_2019.tab");

		PopulationAntrim populationAntrim = new PopulationAntrim();
		populationAntrim.load("population_1821_1971_antrim.csv");

		PopulationArmagh populationArmagh = new PopulationArmagh();
		populationArmagh.load("population_1821_1971_armagh.csv");

		PopulationBelfast populationBelfast = new PopulationBelfast();
		populationBelfast.load("population_1821_1971_belfast.csv");

		PopulationDerry populationDerry = new PopulationDerry();
		populationDerry.load("population_1821_1971_derry.csv");

		PopulationDown populationDown = new PopulationDown();
		populationDown.load("population_1821_1971_down.csv");

		PopulationFermanagh populationFerm = new PopulationFermanagh();
		populationFerm.load("population_1821_1971_fermanagh.csv");

		PopulationTyrone populationTyrone = new PopulationTyrone();
		populationTyrone.load("population_1821_1971_tyrone.csv");

		IndustryMaleCounty1971 industryMale = new IndustryMaleCounty1971();
		industryMale.load("1971_popular_industry_males_belfast.csv", "1971_popular_industry_males_antrim.csv", "1971_popular_industry_males_derry.csv", "1971_popular_industry_males_down.csv");

		IndustryFemaleCounty1971 industryFemale = new IndustryFemaleCounty1971();
		industryFemale.load("1971_popular_industry_females_belfast.csv", "1971_popular_industry_females_antrim.csv", "1971_popular_industry_females_derry.csv", "1971_popular_industry_females_down.csv");

		CarsAntrim carOwnersAntrim1971 = new CarsAntrim();
		carOwnersAntrim1971.load("1971_car_ownership_antrim.csv");
		
		CarsArmagh carOwnersArmagh1971 = new CarsArmagh();
		carOwnersArmagh1971.load("1971_car_ownership_armagh.csv");
		
		CarsBelfast carOwnersBelfast1971 = new CarsBelfast();
		carOwnersBelfast1971.load("1971_car_ownership_belfast.csv");
		
		CarsDerry carOwnersDerry1971 = new CarsDerry();
		carOwnersDerry1971.load("1971_car_ownership_derry.csv");
		
		CarsDown carOwnersDown1971 = new CarsDown();
		carOwnersDown1971.load("1971_car_ownership_down.csv");
		
		CarsFermanagh carOwnersFerm1971 = new CarsFermanagh();
		carOwnersFerm1971.load("1971_car_ownership_fermanagh.csv");
		
		CarsTyrone carOwnersTyrone1971 = new CarsTyrone();
		carOwnersTyrone1971.load("1971_car_ownership_tyrone.csv");

		EmigrationCounty emigCounty = new EmigrationCounty();
		emigCounty.load("emigcou.csv");
		
		BasketGoods1971 basket1971 = new BasketGoods1971();
		basket1971.load("basket_food_1971.csv", "basket_mens_clothing_1971.csv", "basket_womens_clothing_1971.csv", "basket_misc_1971.csv" );

		//Creates a *DatabaseInterface* object
		//when this object is created it will attempt to access the database file
		//it will fix it if there were any problems (e.g. because the webserver crashed while it was writing data)
		//if the database file does not exist it will create it
		//the database is a store of structured information that the webserver can use to 
		//record knowledge used by an application
		//it is good for many small pieces of data that will change frequently
		//this information can be used to dynamically create HTML pages to display information
		//when users interact with web pages (for example clicking on a link) 
		//the page can send information to the server which can then be used to 
		//change the information stored in the database

		String databaseFilepath = "database.mv";
		DatabaseInterface databaseInterface = new DatabaseInterface(databaseFilepath);

		//Creates a *FileStoreInterface* object
		//this object manages access to large files that the webserver will provide to a webbrowser
		//such files might include, images, videos or HTML pages that rarely change
		//if a folder for storing these files does not exist it will be created when this object is constructed

		String fileStoreFilepath = "httpdocs";
		FileStoreInterface fileStoreInterface = new FileStoreInterface(fileStoreFilepath);

		templatepage = TextImportExport.loadTextFileASString("page_template.svg"); //uploads SVG template for page

		String landingPage = TextImportExport.loadTextFileASString("httpdocs/index.html"); //landing page
		String landingSVG = TextImportExport.loadTextFileASString("httpdocs/welcome_page.svg"); //template of landing page
				
		String defaultAddress = "/overviewyear_1971_county_NoPopup_1";
		landingPage = landingPage.replace("defaultPage", defaultAddress);
		landingPage = landingPage.replace("DEFAULTIMAGE", landingSVG);
		
		//Creates a *WebInterface* object
		//when this object is created it will attempt to connect to a network port on the computer
		//and will wait for requests to be made to that port
		//if the connection is successful the URL of the webserver will be printed out to the console
		//when this program is running you can connect to this webserver by typing the URL into a webbrowser on your machine
		//depending on the settings of your firewall you may be able to connect to the webserver from 
		//any other machine connected to the same network 
		//(e.g. other computers in the Lab or on your home network if you share the same wifi connection)
		//Port 80 is the default port for serving webpages
		//when you type "http://127.0.0.1/" as a URL into a webbrowser it will try to access port 80 on your local machine
		//sometimes this port is used by other applications
		//to deal with this you can run your webserver on a different port e.g. 8080
		//then you can access the webserver using "http://127.0.0.1:8080/"
		int port = 8080;
		WebInterface webInterface = new WebInterface(port);

		//This method attaches a shutdown function to the running java system
		//on most platforms the attached function will be called as the last piece of code to run before the application terminates
		//however it is not safe to rely on this as it is not guaranteed to be called, for example if the computer power fails
		//it obviously won't run as, without power, it will stop immediately
		registerShutdownHook(databaseInterface);

		//An example dynamic page
		//		ExampleView exampleDynamicPage = new ExampleView(databaseInterface,fileStoreInterface);
		//An example dynamic page that responds to a form in html
		//		TestForm testFormPage = new TestForm(databaseInterface,fileStoreInterface);
		//this variable indicates that the program should keep running
		//by setting this variable to false the program will exit
		boolean shouldKeepRunning = true;

		ArrayList<WebRequest> requestToProcess = new ArrayList<WebRequest>();

		//the program goes into an "update" loop repeatedly checking requests from web browsers, 
		//this loop consumes a small amount of resources, 
		//it is based on a model where a program may need to perform regular real time updates
		//such as reading from sensors or updating a simulation
		//this is also a common design for applications with responsive user interfaces
		while(shouldKeepRunning)
		{
			//check the time since the last update of the ip
			//potentially update the otidae redirection

			//This application forces a strict flow of control over requests to the webserver from webbrowsers
			//One request is handled at a time to prevent there being issues with two commands modifying the 
			//database at the same time
			//this approach makes it much easier to understand how a web application will run
			//however if the code for responding to a webbrowser request takes a long time it will delay all other
			//web requests and it may seem as if the server has crashed
			//in fact if the code for a page does get into an infinite loop or has a serious error it will crash the server
			//to make this approach work requires that all pages be tested to a high standard 
			webInterface.getQueue(requestToProcess);

			for (int i = 0; i < requestToProcess.size(); ) 
			{
				WebRequest toProcess = requestToProcess.remove(i);

				System.out.println("A web browser has requested the following address: http://"+toProcess.path);

				//examines each request in turn
				//a set of if statements which identify how a web request should be handled 
				//the URL and parameters determine how to change the database or file store
				//they also determine what data should be sent back to the web browser

				//If no path was specified then set the path to index.html
				//this will mean that a static index.html page will be loaded as default
				//the example dynamic page will process the request if the index.html file is not present

				if(toProcess.path.length()==0) {
					toProcess.path = "index.html";	// if no pathway specified then browser goes to index.html
					
				}
				
			
				if(toProcess.path.equalsIgnoreCase("index.html")){ //this is the landing page
			
					toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, landingPage );
				}
				else
				if(toProcess.path.startsWith("overviewyear_")) { // start of the URL

					String rest = toProcess.path.substring("overviewyear_".length());  // the first part of the URL is a string
					String[] entries = rest.split("_"); //create an array using values passed through the URL

					int year = Integer.parseInt(entries[0]); //the year is at index 0 
					String county = entries[1]; // county is at index 1
					String popup = entries[2]; // pop up at index 2
					String seed = entries[3]; // identifier which saves the randomly generated images to this unique number (also randomly generated)
					
					int seedNumber = Integer.parseInt(seed); // converts seed value in URL to an int
					Random randomImage = new Random();
					randomImage.setSeed(seedNumber); // sets the seed of this random selection to the value in the URL, so that it is effectively recorded
					int newSeedID = randomImage.nextInt(); // sets the next seed number to the next randomly generated number 
					
					String customtemplate = templatepage;
					
					int decade = year/10; //divide the year in the URL by ten to give you the start of the decade
					
					int decadeyear = decade*10; //produces full decade										
					
					String newpopup = popup;
					if(popup.equalsIgnoreCase("CountyData")){ // if the county data box is visible, then a click on the county/area will make it close.
						newpopup = "NoPopup";
					} else {
						newpopup = "CountyData";  // if the data box is invisible (i.e. "NoPopUp") then a click on the county/area will make it appear.
					}
					
					//set visibility of data pop-up box
					if (popup.equalsIgnoreCase("CountyData")) {
						customtemplate = customtemplate.replace("showCountyData", "visible"); // set "visibility" of pop up box to "visible" or "hidden"
					} else {
						customtemplate = customtemplate.replace("showCountyData", "hidden");
					}
					
					
					//Each county/area is surrounded by an <a> tag in the SVG file, which allows the correct configuration of the page to appear when clicked
					String belfastData = "/overviewyear_"+year+"_"+"Belfast"+"_"+newpopup+"_"+newSeedID;
					String antrimData = "/overviewyear_"+year+"_"+"Antrim"+"_"+newpopup+"_"+newSeedID;
					String downData = "/overviewyear_"+year+"_"+"Down"+"_"+newpopup+"_"+newSeedID;
					String armaghData = "/overviewyear_"+year+"_"+"Armagh"+"_"+newpopup+"_"+newSeedID;
					String fermanaghData = "/overviewyear_"+year+"_"+"Fermanagh"+"_"+newpopup+"_"+newSeedID;
					String tyroneData = "/overviewyear_"+year+"_"+"Tyrone"+"_"+newpopup+"_"+newSeedID;		
					String derryData = "/overviewyear_"+year+"_"+"Derry"+"_"+newpopup+"_"+newSeedID;
					
					// when clicking on image, background, or sidebar, the pop-up disappears
					String closeData = "/overviewyear_"+year+"_"+"county"+"_"+"NoPopup"+"_"+newSeedID;
					
					// if an area on the map is clicked on map, the URL will change to that set above
						customtemplate = customtemplate.replace("belfastData", belfastData); // replaces the a link variable in SVG file
						customtemplate = customtemplate.replace("antrimData", antrimData);
						customtemplate = customtemplate.replace("downData", downData);
						customtemplate = customtemplate.replace("armaghData", armaghData);
						customtemplate = customtemplate.replace("fermanaghData", fermanaghData);
						customtemplate = customtemplate.replace("tyroneData", tyroneData);
						customtemplate = customtemplate.replace("derryData", derryData);
						
						// all images currently set to "noPopUp"...
						customtemplate = customtemplate.replace("noPopUp", closeData);
						
						//URL for default landing page using 1971 as pre-populated data
						String defaultPage = "/overviewyear_"+"1971"+"_"+"county"+"_"+"NoPopup"+"_"+newSeedID;
						customtemplate = customtemplate.replace("defaultPage", defaultPage);
						
					// timeline URLs for 1970s
					String go1970 = "/overviewyear_"+"1970"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					String go1971 = "/overviewyear_"+"1971"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					String go1972 = "/overviewyear_"+"1972"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					String go1973 = "/overviewyear_"+"1973"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					String go1974 = "/overviewyear_"+"1974"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					String go1975 = "/overviewyear_"+"1975"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					String go1976 = "/overviewyear_"+"1976"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					String go1977 = "/overviewyear_"+"1977"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					String go1978 = "/overviewyear_"+"1978"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					String go1979 = "/overviewyear_"+"1979"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					String go1980 = "/overviewyear_"+"1980"+"_"+county+"_"+"NoPopup"+"_"+newSeedID;
					
				
					customtemplate = customtemplate.replace("year1970", go1970);
					customtemplate = customtemplate.replace("year1971", go1971);
					customtemplate = customtemplate.replace("year1972", go1972);				
					customtemplate = customtemplate.replace("year1973", go1973);
					customtemplate = customtemplate.replace("year1974", go1974);
					customtemplate = customtemplate.replace("year1975", go1975);
					customtemplate = customtemplate.replace("year1976", go1976);
					customtemplate = customtemplate.replace("year1977", go1977);
					customtemplate = customtemplate.replace("year1978", go1978);
					customtemplate = customtemplate.replace("year1979", go1979);
					customtemplate = customtemplate.replace("year1980", go1980);
					
					// Loading images on webpage
					File employmentFolder = new File("httpdocs/images/Employment/"+decadeyear+"s/"+year);
					if(!employmentFolder.exists()){
						customtemplate = customtemplate.replace("imageOne", "httpdocs/images/Year_noImage_58_73.png"); // if folder doesn't exist, generate a default image
					
					} else {
						
						File[] possibleImages = employmentFolder.listFiles(); // an array of images listed in the folder
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageOne", "/images/Year_noImage_58_73.png");
						}
						//randomly pick an image
						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageOne", "/images/Employment/"+decadeyear+"s/"+year+"/"+filename);

						String imageDetails = filename.substring((""+year+"_").length());  // removes year from String
						String[] imageDimensions = imageDetails.split("_"); // the rest of the filename is used to form an array

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightOne", imageHeight); //replaces the variables in SVG file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthOne", imageWidth);					
						
					}
					
					File landscapeFolder = new File("httpdocs/images/Landscape/"+decadeyear+"s/"+year);
					if(!landscapeFolder.exists()){
						customtemplate = customtemplate.replace("imageTwo", "/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = landscapeFolder.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageTwo", "/images/Year_noImage_58_73.png");
						}
						
						//randomly pick an image
						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageTwo", "/images/Landscape/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightTwo", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthTwo", imageWidth);					
						
					}
					File sportFolder = new File("httpdocs/images/Sport/"+decadeyear+"s/"+year);
					if(!sportFolder.exists()){
						customtemplate = customtemplate.replace("imageThree", "httpdocs/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = sportFolder.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageThree", "/images/Year_noImage_58_73.png");
						}
						
						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageThree", "/images/Sport/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightThree", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthThree", imageWidth);					
						
					}
					
					File collageFolder = new File("httpdocs/images/Collage/"+decadeyear+"s/"+year);
					if(!collageFolder.exists()){
						customtemplate = customtemplate.replace("imageFour", "httpdocs/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = collageFolder.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageFour", "/images/Year_noImage_58_73.png");
						}
						
						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageFour", "/images/Collage/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightFour", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthFour", imageWidth);					
						
					}
					
					File politicsFolder = new File("httpdocs/images/Politics/"+decadeyear+"s/"+year);
					if(!politicsFolder.exists()){
						customtemplate = customtemplate.replace("imageFive", "httpdocs/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = politicsFolder.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageFive", "/images/Year_noImage_58_73.png");
						}
						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageFive", "/images/Politics/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightFive", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthFive", imageWidth);					
						
					}
					
					File peopleFolder = new File("httpdocs/images/SocialPeople/"+decadeyear+"s/"+year);
					if(!peopleFolder.exists()){
						customtemplate = customtemplate.replace("imageSix", "httpdocs/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = peopleFolder.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageSix", "/images/Year_noImage_58_73.png");
						}
						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageSix", "/images/SocialPeople/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightSix", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthSix", imageWidth);					
					}
					
					File artFolder = new File("httpdocs/images/Art/"+decadeyear+"s/"+year);
					if(!artFolder.exists()){
						customtemplate = customtemplate.replace("imageSeven", "httpdocs/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = artFolder.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageSeven", "/images/Year_noImage_58_73.png");
						}

						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageSeven", "/images/Art/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightSeven", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthSeven", imageWidth);					
					}
					
					File musicFolder1 = new File("httpdocs/images/Music/"+decadeyear+"s/"+year);
					if(!musicFolder1.exists()){
						customtemplate = customtemplate.replace("imageEight", "httpdocs/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = musicFolder1.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageEight", "/images/Year_noImage_58_73.png");
						}

						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageEight", "/images/Music/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf("."); //returns the index at which the last "." occurs
						imageWidth = imageWidth.substring(0, iextension); //width value is set from index 0 to the beginning of the index set above
						
						customtemplate = customtemplate.replace("heightEight", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthEight", imageWidth);					
						
					}
					
					File filmFolder1 = new File("httpdocs/images/Film/"+decadeyear+"s/"+year);
					if(!filmFolder1.exists()){
						customtemplate = customtemplate.replace("imageNine", "httpdocs/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = filmFolder1.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageNine", "/images/Year_noImage_58_73.png");
						}
						
						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageNine", "/images/Film/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightNine", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthNine", imageWidth);					
						
					}
				
					File posterFolder1 = new File("httpdocs/images/Posters/"+decadeyear+"s/"+year);
					if(!posterFolder1.exists()){
						customtemplate = customtemplate.replace("imageTen", "httpdocs/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = posterFolder1.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageTen", "/images/Year_noImage_58_73.png");
						}
						
						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageTen", "/images/Posters/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightTen", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthTen", imageWidth);					
						
					}
					
					File schoolFolder = new File("httpdocs/images/School/"+decadeyear+"s/"+year);
					if(!schoolFolder.exists()){
						customtemplate = customtemplate.replace("imageEleven", "httpdocs/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = schoolFolder.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageEleven", "/images/Year_noImage_58_73.png");
						}
						
						int randomIndex = randomImage.nextInt(possibleImages.length);
						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageEleven", "/images/School/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightEleven", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthEleven", imageWidth);					
					}
				
					File transportFolder = new File("httpdocs/images/Transport/"+decadeyear+"s/"+year);
					if(!transportFolder.exists()){
						customtemplate = customtemplate.replace("imageTwelve", "httpdocs/images/Year_noImage_58_73.png");
					} else {
						
						File[] possibleImages = transportFolder.listFiles();
						if(possibleImages.length==0) {
							customtemplate = customtemplate.replace("imageTwelve", "/images/Year_noImage_58_73.png");
						}
						
						int randomIndex = randomImage.nextInt(possibleImages.length);

						String filename = possibleImages[randomIndex].getName();
						customtemplate = customtemplate.replace("imageTwelve", "/images/Transport/"+decadeyear+"s/"+year+"/"+filename);
						
						String imageDetails = filename.substring((""+year+"_").length());  
						String[] imageDimensions = imageDetails.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightTwelve", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthTwelve", imageWidth);					
						
					}
					
					// graphics for webpage - newspaper
					File newsGraphic = new File("httpdocs/graphics/paperstockimg_59.016956_79.36882.jpg");
					if(!newsGraphic.exists()){
						
					} else {
						String filename = newsGraphic.getName();
						customtemplate = customtemplate.replace("newsImg", "graphics/"+filename);
						String[] imageDimensions = filename.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightNews", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthNews", imageWidth);					
					}	
										
					File movieGraphic = new File("httpdocs/graphics/cinema_70.274555_90.56189.png");
					if(!movieGraphic.exists()){
						
					} else {
						String filename = movieGraphic.getName();
						customtemplate = customtemplate.replace("movieGraphic", "graphics/"+filename);
					
						String[] imageDimensions = filename.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightMovie", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthMovie", imageWidth);					
					}	
					
					File musicGraphic = new File("httpdocs/graphics/record-player_65.00061_74.105072.png");
					if(!musicGraphic.exists()){
						
					} else {
						String filename = musicGraphic.getName();
						customtemplate = customtemplate.replace("musicGraphic", "graphics/"+filename);
					
						String[] imageDimensions = filename.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightMusic", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthMusic", imageWidth);					
					}
					
					File countyGraphic = new File("httpdocs/graphics/countyBackground_117.12153_122.95017.png");
					if(!countyGraphic.exists()){
						
					} else {
						String filename = countyGraphic.getName();
						customtemplate = customtemplate.replace("countyGraphic", "graphics/"+filename);
					
						String[] imageDimensions = filename.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightCounty", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthCounty", imageWidth);					
					}
					
					File dataGraphic = new File("httpdocs/graphics/regiondata_215.7603_98.6499(2).png");
					if(!dataGraphic.exists()){
						
					} else {
						String filename = dataGraphic.getName();
						customtemplate = customtemplate.replace("nidataGraphic", "graphics/"+filename);
					
						String[] imageDimensions = filename.split("_");

						String imageDesc = imageDimensions[0]; //image description is at index 0
						String imageHeight = imageDimensions[1]; // height dimension is at index 1 	
						String imageWidth = imageDimensions[2];  // width dimension is at index 2
						int iextension = imageWidth.lastIndexOf(".");
						imageWidth = imageWidth.substring(0, iextension);
						
						customtemplate = customtemplate.replace("heightData", imageHeight); //replaces the variables in svg file with dimensions taken from file name
						customtemplate = customtemplate.replace("widthData", imageWidth);					
					}
					
					
					//	       if(decade==191) {			// if the decade is 1910s then data will be loaded from music class for 1950s
//						int musYear = mus1900s.year1900s.find(year);
//						
//						int musSearch = mus1900s.year1900s.value[musYear+0]; //.value creates an array which starts with the first entry in the data file which matches the year
//						if(musSearch!=year) {
//							customtemplate = customtemplate.replace("music01", "");
//						} else {
//							String artistMus = mus1900s.artist1900s.value[musYear+0];
//							String titleMus = mus1900s.title1900s.value[musYear+0];
//							customtemplate = customtemplate.replace("music01", titleMus+", "+artistMus);
//							
//							if(artistMus.length()==0 || titleMus.length()==0) {
//								customtemplate = customtemplate.replace("music01", "No data available");
//							}
//						}
//					
					//	        			String artist = mus1900s.artist1910s.value[0];
					//	        			String songname = mus1900s.title1910s.value[0];
					//	        			customtemplate = customtemplate.replace("music01", artist+", "+songname);
					//	        			
					//	        			String artist2 = mus1900s.artist1910s.value[1];
					//	        			String songname2 = mus1900s.title1910s.value[1];
					//	        			customtemplate = customtemplate.replace("music02", artist2+", "+songname2);
					//	        			
					//	        			String artist3 = mus1900s.artist1910s.value[2];
					//	        			String songname3 = mus1900s.title1910s.value[2];
					//	        			customtemplate = customtemplate.replace("music03", artist3+", "+songname3);
					//	        			
					//	        			String artist4 = mus1900s.artist1910s.value[3];
					//	        			String songname4 = mus1900s.title1910s.value[3];
					//	        			customtemplate = customtemplate.replace("music04", artist4+", "+songname4);
					//	        			
					//	        			String artist5 = mus1900s.artist1910s.value[4];
					//	        			String songname5 = mus1900s.title1910s.value[4];
					//	        			customtemplate = customtemplate.replace("music05", artist5+", "+songname5);
					//	        			
					//	        		} else 
					
					
					// Music data	
					int searchMusicYear = numOnes.year1970s.find(year);
					
					int songYr = numOnes.year1970s.value[searchMusicYear+0]; //.value creates an array which starts with the first entry in the data file which matches the year
					if(songYr!=year) {
						customtemplate = customtemplate.replace("music01", "No data available");
					} else {
						String artist = numOnes.artist1970s.value[searchMusicYear+0];
						String songname = numOnes.title1970s.value[searchMusicYear+0];
						customtemplate = customtemplate.replace("music01", songname+", "+artist);
						
						if(artist.length()==0 || songname.length()==0) {
							customtemplate = customtemplate.replace("music01", "No data available");
						}
					}

					int songYr2 = numOnes.year1970s.value[searchMusicYear+1];	
					if(songYr2!=year) {
						customtemplate = customtemplate.replace("music02", "");
					} else {
						String artist2 = numOnes.artist1970s.value[searchMusicYear+1];
						String songname2 = numOnes.title1970s.value[searchMusicYear+1];
						customtemplate = customtemplate.replace("music02", songname2+", "+artist2);
						
						if(artist2.length()==0 || songname2.length()==0) {
							customtemplate = customtemplate.replace("music02", "No data available");
						}
					}

					int songYr3 = numOnes.year1970s.value[searchMusicYear+2];	
					if(songYr3!=year) {
						customtemplate = customtemplate.replace("music03", "");	
					} else {	
						String artist3 = numOnes.artist1970s.value[searchMusicYear+2];
						String songname3 = numOnes.title1970s.value[searchMusicYear+2];
						customtemplate = customtemplate.replace("music03", songname3+", "+artist3);
						
						if(artist3.length()==0 || songname3.length()==0) {
							customtemplate = customtemplate.replace("music03", "No data available");
						}
					}

					int songYr4 = numOnes.year1970s.value[searchMusicYear+3];	
					if(songYr4!=year) {
						customtemplate = customtemplate.replace("music04", "");	
					} else {	
						String artist4 = numOnes.artist1970s.value[searchMusicYear+3];
						String songname4 = numOnes.title1970s.value[searchMusicYear+3];
						customtemplate = customtemplate.replace("music04", songname4+", "+artist4);
						
						if(artist4.length()==0 || songname4.length()==0) {
							customtemplate = customtemplate.replace("music04", "No data available");
						}
					}

					int songYr5 = numOnes.year1970s.value[searchMusicYear+4];	
					if(songYr5!=year) {
						customtemplate = customtemplate.replace("music05", "");	
					} else {	
						String artist5 = numOnes.artist1970s.value[searchMusicYear+4];
						String songname5 = numOnes.title1970s.value[searchMusicYear+4];
						customtemplate = customtemplate.replace("music05", songname5+", "+artist5);
						
						if(artist5.length()==0 || songname5.length()==0) {
							customtemplate = customtemplate.replace("music05", "No data available");
						}
					}	
					
					int songYr6 = numOnes.year1970s.value[searchMusicYear+4];	
					if(songYr6!=year) {
						customtemplate = customtemplate.replace("music06", "");	
					} else {	
						String artist6 = numOnes.artist1970s.value[searchMusicYear+5];
						String songname6 = numOnes.title1970s.value[searchMusicYear+5];
						customtemplate = customtemplate.replace("music06", songname6+", "+artist6);
						
						if(artist6.length()==0 || songname6.length()==0) {
							customtemplate = customtemplate.replace("music06", "No data available");
						}
					}	
					
					int songYr7 = numOnes.year1970s.value[searchMusicYear+4];	
					if(songYr7!=year) {
						customtemplate = customtemplate.replace("music07", "");	
					} else {	
						String artist7 = numOnes.artist1970s.value[searchMusicYear+6];
						String songname7 = numOnes.title1970s.value[searchMusicYear+6];
						customtemplate = customtemplate.replace("music07", songname7+", "+artist7);
						
						if(artist7.length()==0 || songname7.length()==0) {
							customtemplate = customtemplate.replace("music07", "No data available");
						}
					}
					
					int songYr8 = numOnes.year1970s.value[searchMusicYear+4];	
					if(songYr8!=year) {
						customtemplate = customtemplate.replace("music08", "");	
					} else {	
						String artist8 = numOnes.artist1970s.value[searchMusicYear+6];
						String songname8 = numOnes.title1970s.value[searchMusicYear+6];
						customtemplate = customtemplate.replace("music08", songname8+", "+artist8);
						
						if(artist8.length()==0 || songname8.length()==0) {
							customtemplate = customtemplate.replace("music08", "No data available");
						}
					}

					// Film data
					int searchFilmYear = filmGross1900s.filmYear.find(year);

					int filmYear1 = filmGross1900s.filmYear.value[searchFilmYear+0]; // search CSV file for the year in URL, creates an array which starts with the first entry which matched the year
					if(filmYear1!=year){
						customtemplate = customtemplate.replace("movie01", "No data available");
					} else {
						String filmTitle1 = filmGross1900s.filmTitle.value[searchFilmYear+0];
						customtemplate = customtemplate.replace("movie01", filmYear1+": "+filmTitle1);
						
						if(filmTitle1.length()==0) {
							customtemplate = customtemplate.replace("movie01", "No data available");
						}
					}

					int filmYear2 = filmGross1900s.filmYear.value[searchFilmYear+1]; // tells the program to move to the next entry in the array, ie. then next row of data for that year
					if(filmYear2!=year){
						customtemplate = customtemplate.replace("movie02", "");
					} else {
						String filmTitle2 = filmGross1900s.filmTitle.value[searchFilmYear+1];
						customtemplate = customtemplate.replace("movie02", filmYear2+": "+filmTitle2);
						
						if(filmTitle2.length()==0) {
							customtemplate = customtemplate.replace("movie02", "No data available");
						}
					}

					int filmYear3 = filmGross1900s.filmYear.value[searchFilmYear+2];
					if(filmYear3!=year){
						customtemplate = customtemplate.replace("movie03", "");
					} else {
						String filmTitle3 = filmGross1900s.filmTitle.value[searchFilmYear+2];
						customtemplate = customtemplate.replace("movie03", filmYear3+": "+filmTitle3);
						
						if(filmTitle3.length()==0) {
							customtemplate = customtemplate.replace("movie03", "No data available");
						}
					}

					int filmYear4 = filmGross1900s.filmYear.value[searchFilmYear+3];
					if(filmYear4!=year){
						customtemplate = customtemplate.replace("movie04", "");
					} else {
						String filmTitle4 = filmGross1900s.filmTitle.value[searchFilmYear+3];
						customtemplate = customtemplate.replace("movie04", filmYear4+": "+filmTitle4);
						
						if(filmTitle4.length()==0) {
							customtemplate = customtemplate.replace("movie04", "No data available");
						}
					}     			

					int filmYear5 = filmGross1900s.filmYear.value[searchFilmYear+4];
					if(filmYear5!=year){
						customtemplate = customtemplate.replace("movie05", "");
					} else {
						String filmTitle5 = filmGross1900s.filmTitle.value[searchFilmYear+4];
						customtemplate = customtemplate.replace("movie05", filmYear5+": "+filmTitle5);
						
						if(filmTitle5.length()==0) {
							customtemplate = customtemplate.replace("movie05", "No data available");
						}
					}

					// News Data
					int newsYear = news1900s.newsYear.find(year);

					String newsTitle1 = news1900s.newsStory1.value[newsYear]; // each story title is held in separate matrices, therefore all values are stored at index 0 in given year
					if(newsTitle1.isEmpty()) {
						customtemplate = customtemplate.replace("news01", "No data available"); // if no story, then print message to screen
					} else {
						if (newsTitle1.length()>32) { //shows only the first 32 characters of headline
						customtemplate = customtemplate.replace("news01", newsTitle1.substring(1, 32)+"...");
						} else {
						customtemplate = customtemplate.replace("news01", newsTitle1.substring(1));	
						}
					}
					
					String newsTitle2 = news1900s.newsStory2.value[newsYear];
					if(newsTitle2.isEmpty()) {
						customtemplate = customtemplate.replace("news02", "");
					} else {
						if (newsTitle2.length()>32) {
						customtemplate = customtemplate.replace("news02", newsTitle2.substring(1, 32)+"...");
						} else {
							customtemplate = customtemplate.replace("news02", newsTitle2.substring(1));	
						}
					}
					
					String newsTitle3 = news1900s.newsStory3.value[newsYear];
					if(newsTitle3.isEmpty()) {
						customtemplate = customtemplate.replace("news03", "");
					} else {
						if (newsTitle3.length()>32) {
						customtemplate = customtemplate.replace("news03", newsTitle3.substring(1, 32)+"...");
						} else {
							customtemplate = customtemplate.replace("news03", newsTitle3.substring(1));
						}
					}
					
					String newsTitle4 = news1900s.newsStory4.value[newsYear];
					if(newsTitle4.isEmpty()) {
						customtemplate = customtemplate.replace("news04", "");
					}else {
						if (newsTitle4.length()>32) {
						customtemplate = customtemplate.replace("news04", newsTitle4.substring(1, 32)+"...");
						} else {
							customtemplate = customtemplate.replace("news04", newsTitle4.substring(1));
						}
					}

					//basket of goods - food
					String yearString = (""+year); // converts the year from int to string
					
					if(!yearString.equals("1971")) { //only hold data for 1971
						customtemplate = customtemplate.replace("food01", "No data available");
						customtemplate = customtemplate.replace("food02", "");
						customtemplate = customtemplate.replace("food03", "");
						customtemplate = customtemplate.replace("food04", "");
						customtemplate = customtemplate.replace("food05", "");
						customtemplate = customtemplate.replace("food06", "");
						customtemplate = customtemplate.replace("food07", "");
						customtemplate = customtemplate.replace("food08", "");
						customtemplate = customtemplate.replace("food09", "");
						customtemplate = customtemplate.replace("food10", "");
						
						customtemplate = customtemplate.replace("clothes01", "No data available");
						customtemplate = customtemplate.replace("clothes02", "");
						customtemplate = customtemplate.replace("clothes03", "");
						customtemplate = customtemplate.replace("clothes04", "");
						customtemplate = customtemplate.replace("clothes05", "");
						customtemplate = customtemplate.replace("clothes06", "No data available");
						customtemplate = customtemplate.replace("clothes07", "");
						customtemplate = customtemplate.replace("clothes08", "");
						customtemplate = customtemplate.replace("clothes09", "");
						customtemplate = customtemplate.replace("clothes10", "");
						
						customtemplate = customtemplate.replace("misc01", "No data available");
						customtemplate = customtemplate.replace("misc02", "");
						customtemplate = customtemplate.replace("misc03", "");
						customtemplate = customtemplate.replace("misc04", "");
						customtemplate = customtemplate.replace("misc05", "");
						customtemplate = customtemplate.replace("misc06", "");
						customtemplate = customtemplate.replace("misc07", "");
						customtemplate = customtemplate.replace("misc08", "");
						customtemplate = customtemplate.replace("misc09", "");
						customtemplate = customtemplate.replace("misc10", "");
					}
											
//				
					else {
					
					int basketYear = basket1971.foodYear.find(year);	
											
					String foodItem1 = basket1971.foodItem.value[basketYear +0]; // data held in separate matrices, all string values will be stored at index 0 for each story item in a given year
					customtemplate = customtemplate.replace("food01", foodItem1);
					String foodItem2 = basket1971.foodItem.value[basketYear +1]; 
					customtemplate = customtemplate.replace("food02", foodItem2);
					String foodItem3 = basket1971.foodItem.value[basketYear +2];
					customtemplate = customtemplate.replace("food03", foodItem3);
					String foodItem4 = basket1971.foodItem.value[basketYear +3];
					customtemplate = customtemplate.replace("food04", foodItem4);
					String foodItem5 = basket1971.foodItem.value[basketYear +4];
					customtemplate = customtemplate.replace("food05", foodItem5);
					String foodItem6 = basket1971.foodItem.value[basketYear +5];
					customtemplate = customtemplate.replace("food06", foodItem6);		
					String foodItem7 = basket1971.foodItem.value[basketYear +6];
					customtemplate = customtemplate.replace("food07", foodItem7);
					String foodItem8 = basket1971.foodItem.value[basketYear +7];
					customtemplate = customtemplate.replace("food08", foodItem8);
					String foodItem9 = basket1971.foodItem.value[basketYear +8];
					customtemplate = customtemplate.replace("food09", foodItem9);
					String foodItem10 = basket1971.foodItem.value[basketYear +9];
					customtemplate = customtemplate.replace("food10", foodItem10);
					
					//basket of goods - clothing mens
					String menClothes1 = basket1971.menClothItem.value[basketYear +0]; // data held in separate matrices, all string values will be stored at index 0 for each story item in a given year
					customtemplate = customtemplate.replace("clothes01", menClothes1);
					String menClothes2 = basket1971.menClothItem.value[basketYear +1]; 
					customtemplate = customtemplate.replace("clothes02", menClothes2);
					String menClothes3 = basket1971.menClothItem.value[basketYear +2]; 
					customtemplate = customtemplate.replace("clothes03", menClothes3);
					String menClothes4 = basket1971.menClothItem.value[basketYear +3]; 
					customtemplate = customtemplate.replace("clothes04", menClothes4);
					String menClothes5 = basket1971.menClothItem.value[basketYear +4]; 
					customtemplate = customtemplate.replace("clothes05", menClothes5);
					
					//basket of goods - clothing womens
					String womenClothes1 = basket1971.womenClothItem.value[basketYear +0]; 
					customtemplate = customtemplate.replace("clothes06", womenClothes1);		
					String womenClothes2 = basket1971.womenClothItem.value[basketYear +1]; 
					customtemplate = customtemplate.replace("clothes07", womenClothes2);
					String womenClothes3 = basket1971.womenClothItem.value[basketYear +2]; 
					customtemplate = customtemplate.replace("clothes08", womenClothes3);
					String womenClothes4 = basket1971.womenClothItem.value[basketYear +3]; 
					customtemplate = customtemplate.replace("clothes09", womenClothes4);
					String womenClothes5 = basket1971.womenClothItem.value[basketYear +4]; 
					customtemplate = customtemplate.replace("clothes10", womenClothes5);
					
					//basket of goods - miscellaneous items
					String misc1 = basket1971.miscItem.value[basketYear +0]; 
					customtemplate = customtemplate.replace("misc01", misc1);	
					String misc2 = basket1971.miscItem.value[basketYear +1]; 
					customtemplate = customtemplate.replace("misc02", misc2);	
					String misc3 = basket1971.miscItem.value[basketYear +2]; 
					customtemplate = customtemplate.replace("misc03", misc3);	
					String misc4 = basket1971.miscItem.value[basketYear +3]; 
					customtemplate = customtemplate.replace("misc04", misc4);	
					String misc5 = basket1971.miscItem.value[basketYear +4]; 
					customtemplate = customtemplate.replace("misc05", misc5);	
					String misc6 = basket1971.miscItem.value[basketYear +5]; 
					customtemplate = customtemplate.replace("misc06", misc6);	
					String misc7 = basket1971.miscItem.value[basketYear +6]; 
					customtemplate = customtemplate.replace("misc07", misc7);	
					String misc8 = basket1971.miscItem.value[basketYear +7]; 
					customtemplate = customtemplate.replace("misc08", misc8);	
					String misc9 = basket1971.miscItem.value[basketYear +9]; 
					customtemplate = customtemplate.replace("misc09", misc9);	
					String misc10 = basket1971.miscItem.value[basketYear +10]; 
					customtemplate = customtemplate.replace("misc10", misc10);	
					}
					
					// county level data...Antrim
					// population
				if(yearString.equals("1971")) {
					if(county.equalsIgnoreCase("Antrim")) {
						int popCounty = 0;
						int malesCounty = 0;
						int femalesCounty = 0;

						for(int loop=0; loop<populationAntrim.popAntrim.height; loop++) { //loop through matrix in popAntrim class

							int yearStats = populationAntrim.popAntrim.get(0, loop); // value for year is taken from index 0
							int populationC = populationAntrim.popAntrim.get(1, loop); // value for population is taken from index 1 and so on
							int birthC = populationAntrim.popAntrim.get(2, loop);
							int deathC = populationAntrim.popAntrim.get(3, loop);

							if(yearStats==year){
								popCounty = populationC;
								malesCounty = birthC;
								femalesCounty = deathC;
								break;
							} 
						}
						String popFormat = String.format("%,d", popCounty);
						String malesFormat = String.format("%,d", malesCounty);
						String femalesFormat = String.format("%,d", femalesCounty);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);	
						
						if(popFormat.isEmpty()) {
							customtemplate = customtemplate.replace("popData", "No data available");
						} else {
							customtemplate = customtemplate.replace("popData", popFormat);
						}
						if(malesFormat.isEmpty()) {
							customtemplate = customtemplate.replace("numMales", "No data available");
						} else {
							customtemplate = customtemplate.replace("numMales", malesFormat);
						}
						if (femalesFormat.isEmpty()) {
							customtemplate = customtemplate.replace("numFemales", "No data available");	
						}else {
							customtemplate = customtemplate.replace("numFemales", femalesFormat);
						}
						
					}
				} else {
					customtemplate = customtemplate.replace("popData", "No data available");
					customtemplate = customtemplate.replace("numMales", "No data available");
					customtemplate = customtemplate.replace("numFemales", "No data available");
				}
					
				// car ownership (only hold data for 1971)
				if(yearString.equals("1971")) {
					if(county.equalsIgnoreCase("Antrim")){
						int totalHouses = 0;
						int houseNoCar = 0;
						int houseOneCar = 0;
						int houseTwoPlus = 0;

						for(int loop=0; loop<carOwnersAntrim1971.carAntrim.height; loop++) { //loop through matrix in popAntrim class

							int yearCars = carOwnersAntrim1971.carAntrim.get(0, loop); // value for year is taken from index 0
							int numberHouse = carOwnersAntrim1971.carAntrim.get(1, loop); // value for population is taken from index 1 and so on
							int noCars = carOwnersAntrim1971.carAntrim.get(2, loop);
							int oneCar = carOwnersAntrim1971.carAntrim.get(3, loop);
							int twoCars = carOwnersAntrim1971.carAntrim.get(4, loop);

							if(yearCars==year){
								totalHouses = numberHouse;
								houseNoCar = noCars;
								houseOneCar = oneCar;
								houseTwoPlus = twoCars;
								break;
							}
						}
						String totalHouseFormat = String.format("%,d", totalHouses);
						String noCarFormat = String.format("%,d", houseNoCar);
						String oneCarFormat = String.format("%,d", houseOneCar);
						String twoCarFormat = String.format("%,d", houseTwoPlus);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);	
						customtemplate = customtemplate.replace("numHouses", totalHouseFormat);
						customtemplate = customtemplate.replace("numNoCar", noCarFormat);
						customtemplate = customtemplate.replace("numOneCar", oneCarFormat);
						customtemplate = customtemplate.replace("numTwoCar", twoCarFormat);
					}
				} else {
					customtemplate = customtemplate.replace("countyName", "County "+county);	
					customtemplate = customtemplate.replace("numHouses", "No data available");
					customtemplate = customtemplate.replace("numNoCar", "No data available");
					customtemplate = customtemplate.replace("numOneCar", "No data available");
					customtemplate = customtemplate.replace("numTwoCar", "No data available");
				}

					// industry males
					int searchYear1 = industryMale.yearIndAntrim.find(year);
					
					if(yearString.equals("1971")) {
					
					if(county.equalsIgnoreCase("Antrim")){

						int indYear1 = industryMale.yearIndAntrim.value[searchYear1 +0];
						if(indYear1!=year) {
							customtemplate = customtemplate.replace("occType01", "");
						} else {
							String industryName1 = industryMale.nameIndAntrim.value[searchYear1 +0];
							int total1 = industryMale.totalAntrim.value[searchYear1 +0];
							if(industryName1.length()>24) {
								customtemplate = customtemplate.replace("occType01", industryName1.substring(0, 25)+"...: "); // reduces string to 25 characters 
							} else if(industryName1.isEmpty()) {
								customtemplate = customtemplate.replace("occType01", "No data available");
							} else {
								customtemplate = customtemplate.replace("occType01", industryName1+": ");
							}
							if(total1<1) {
								customtemplate = customtemplate.replace("total01", "" );
							} else {
							customtemplate = customtemplate.replace("total01", ""+total1+"" );
							}
						}
				

						int indYear2 = industryMale.yearIndAntrim.value[searchYear1 +1];
						if(indYear2!=year) {
							customtemplate = customtemplate.replace("occType02", "");
						} else {
							String industryName2 = industryMale.nameIndAntrim.value[searchYear1 +1];
							int total2 = industryMale.totalAntrim.value[searchYear1 +1];
							if(industryName2.length()>24) {
								customtemplate = customtemplate.replace("occType02", industryName2.substring(0, 25)+"...: ");
							} else if(industryName2.isEmpty()) {
								customtemplate = customtemplate.replace("occType02", "No data available");
							} else {
								customtemplate = customtemplate.replace("occType02", industryName2+": ");
							}
							if(total2<1) {
								customtemplate = customtemplate.replace("total02", "" );
							} else {
							customtemplate = customtemplate.replace("total02", ""+total2+"" );
							}
						}

						int indYear3 = industryMale.yearIndAntrim.value[searchYear1 +2];
						if(indYear3!=year) {
							customtemplate = customtemplate.replace("occType03", "");
						} else {
							String industryName3 = industryMale.nameIndAntrim.value[searchYear1 +2];
							int total3 = industryMale.totalAntrim.value[searchYear1 +2];
							if(industryName3.length()>24) {
								customtemplate = customtemplate.replace("occType03", industryName3.substring(0, 25)+"...: ");
							} else if(industryName3.isEmpty()) {
								customtemplate = customtemplate.replace("occType03", "No data available");
							} else {
								customtemplate = customtemplate.replace("occType03", industryName3+": ");
							}
							if(total3<1) {
								customtemplate = customtemplate.replace("total03", "" );
							} else {
							customtemplate = customtemplate.replace("total03", ""+total3+"" );
							}		
						}
						
						int indYear4 = industryMale.yearIndAntrim.value[searchYear1 +3];
						if(indYear4!=year) {
							customtemplate = customtemplate.replace("occType04", "");
						} else {
							String industryName4 = industryMale.nameIndAntrim.value[searchYear1 +3];
							int total4 = industryMale.totalAntrim.value[searchYear1 +3];
							if(industryName4.length()>24) {
								customtemplate = customtemplate.replace("occType04", industryName4.substring(0, 25)+"...: ");
							} else if(industryName4.isEmpty()) {
								customtemplate = customtemplate.replace("occType04", "No data available");
							} else {
								customtemplate = customtemplate.replace("occType04", industryName4+": ");
							}
							if(total4<1) {
								customtemplate = customtemplate.replace("total04", "" );
							} else {
							customtemplate = customtemplate.replace("total04", ""+total4+"" );
							}
						}
						
						int indYear5 = industryMale.yearIndAntrim.value[searchYear1 +4];
						if(indYear5!=year) {
							customtemplate = customtemplate.replace("occType05", "");
						} else {
							String industryName5 = industryMale.nameIndAntrim.value[searchYear1 +4];
							int total5 = industryMale.totalAntrim.value[searchYear1 +4];
							if(industryName5.length()>24) {
								customtemplate = customtemplate.replace("occType05", industryName5.substring(0, 25)+"...: ");
							} else if(industryName5.isEmpty()) {
								customtemplate = customtemplate.replace("occType05", "No data available");
							} else {
								customtemplate = customtemplate.replace("occType05", industryName5+": ");
							}
							if(total5<1) {
								customtemplate = customtemplate.replace("total05", "" );
							} else {
							customtemplate = customtemplate.replace("total05", ""+total5+"" );
							}
						}
						//female industry
						int indYear6 = industryFemale.yearIndAntrim.value[searchYear1 +5];
						if(indYear6!=year) {
							customtemplate = customtemplate.replace("occType06", "");
						} else {
							String industryName6 = industryFemale.nameIndAntrim.value[searchYear1 +5];
							int total6 = industryFemale.totalAntrim.value[searchYear1 +5];
							if(industryName6.length()>24) {
								customtemplate = customtemplate.replace("occType06", industryName6.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType06", industryName6+": ");
							}
							customtemplate = customtemplate.replace("total06", ""+total6+"");
						}

						int indYear7 = industryFemale.yearIndAntrim.value[searchYear1 +6];
						if(indYear7!=year) {
							customtemplate = customtemplate.replace("occType07", "");
						} else {
							String industryName7 = industryFemale.nameIndAntrim.value[searchYear1 +6];
							int total7 = industryFemale.totalAntrim.value[searchYear1 +6];
							if(industryName7.length()>24) {
								customtemplate = customtemplate.replace("occType07", industryName7.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType07", industryName7+": ");
							}
							customtemplate = customtemplate.replace("total07", ""+total7+"");
						}

						int indYear8 = industryFemale.yearIndAntrim.value[searchYear1 +7];
						if(indYear8!=year) {
							customtemplate = customtemplate.replace("occType08", "");
						} else {
							String industryName8 = industryFemale.nameIndAntrim.value[searchYear1 +7];
							int total8 = industryFemale.totalAntrim.value[searchYear1 +7];
							if(industryName8.length()>24) {
								customtemplate = customtemplate.replace("occType08", industryName8.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType08", industryName8+": ");	
							}
							customtemplate = customtemplate.replace("total08", ""+total8+"");
						}

						int indYear9 = industryFemale.yearIndAntrim.value[searchYear1 +8];
						if(indYear9!=year) {
							customtemplate = customtemplate.replace("occType09", "");
						} else {
							String industryName9 = industryFemale.nameIndAntrim.value[searchYear1 +8];
							int total9 = industryFemale.totalAntrim.value[searchYear1 +8];
							if(industryName9.length()>24) {
								customtemplate = customtemplate.replace("occType09", industryName9.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType09", industryName9+": ");	
							}
							customtemplate = customtemplate.replace("total09", ""+total9+"");
						}

						int indYear10 = industryFemale.yearIndAntrim.value[searchYear1 +9];
						if(indYear10!=year) {
							customtemplate = customtemplate.replace("occType10", "");
						} else {
							String industryName10 = industryFemale.nameIndAntrim.value[searchYear1 +9];
							int total10 = industryFemale.totalAntrim.value[searchYear1 +9];
							if(industryName10.length()>24) {
								customtemplate = customtemplate.replace("occType10", industryName10.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType10", industryName10+": ");
							}
							customtemplate = customtemplate.replace("total10",""+total10+"");
						}
					} 
					
					} else {
						customtemplate = customtemplate.replace("occType01", "No data available");
						customtemplate = customtemplate.replace("occType02", "");
						customtemplate = customtemplate.replace("occType03", "");
						customtemplate = customtemplate.replace("occType04", "");
						customtemplate = customtemplate.replace("occType05", "");
						customtemplate = customtemplate.replace("occType06", "No data available");
						customtemplate = customtemplate.replace("occType07", "");
						customtemplate = customtemplate.replace("occType08", "");
						customtemplate = customtemplate.replace("occType09", "");
						customtemplate = customtemplate.replace("occType10", "");
						
						customtemplate = customtemplate.replace("total01", "");
						customtemplate = customtemplate.replace("total02", "");
						customtemplate = customtemplate.replace("total03", "");
						customtemplate = customtemplate.replace("total04", "");
						customtemplate = customtemplate.replace("total05", "");
						customtemplate = customtemplate.replace("total06", "");
						customtemplate = customtemplate.replace("total07", "");
						customtemplate = customtemplate.replace("total08", "");
						customtemplate = customtemplate.replace("total09", "");
						customtemplate = customtemplate.replace("total10", "");
					}
				
					// Armagh county population data
					if(yearString.equals("1971")) {
					if(county.equalsIgnoreCase("Armagh")){
						int popCounty = 0;
						int malesCounty = 0;
						int femalesCounty = 0;

						for(int loop=0; loop<populationArmagh.popArmagh.height; loop++) { //loop through matrix in popAntrim class

							int yearStats = populationArmagh.popArmagh.get(0, loop); // value for year is taken from index 0
							int populationC = populationArmagh.popArmagh.get(1, loop); // value for population is taken from index 1 and so on
							int birthC = populationArmagh.popArmagh.get(2, loop);
							int deathC = populationArmagh.popArmagh.get(3, loop);

							if(yearStats==year){
								popCounty = populationC;
								malesCounty = birthC;
								femalesCounty = deathC;
								break;
							}
						}
						String popFormat = String.format("%,d", popCounty);
						String malesFormat = String.format("%,d", malesCounty);
						String femalesFormat = String.format("%,d", femalesCounty);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);
						customtemplate = customtemplate.replace("popData", popFormat);
						customtemplate = customtemplate.replace("numMales", malesFormat);
						customtemplate = customtemplate.replace("numFemales", femalesFormat);
					}
				} else {
					customtemplate = customtemplate.replace("countyName", "County "+county);
					customtemplate = customtemplate.replace("popData", "No data available");
					customtemplate = customtemplate.replace("numMales", "No data available");
					customtemplate = customtemplate.replace("numFemales", "No data available");	
				}

					// car ownership (only hold data for 1971)
				if(yearString.equals("1971")) {
					if(county.equalsIgnoreCase("Armagh")){
						int totalHouses = 0;
						int houseNoCar = 0;
						int houseOneCar = 0;
						int houseTwoPlus = 0;

						for(int loop=0; loop<carOwnersArmagh1971.carArmagh.height; loop++) { //loop through matrix in popAntrim class

							int yearCars = carOwnersArmagh1971.carArmagh.get(0, loop); // value for year is taken from index 0
							int numberHouse = carOwnersArmagh1971.carArmagh.get(1, loop); // value for population is taken from index 1 and so on
							int noCars = carOwnersArmagh1971.carArmagh.get(2, loop);
							int oneCar = carOwnersArmagh1971.carArmagh.get(3, loop);
							int twoCars = carOwnersArmagh1971.carArmagh.get(4, loop);

							if(yearCars==year){
								totalHouses = numberHouse;
								houseNoCar = noCars;
								houseOneCar = oneCar;
								houseTwoPlus = twoCars;
								break;
							}
						}
						String totalHouseFormat = String.format("%,d", totalHouses);
						String noCarFormat = String.format("%,d", houseNoCar);
						String oneCarFormat = String.format("%,d", houseOneCar);
						String twoCarFormat = String.format("%,d", houseTwoPlus);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);	
						customtemplate = customtemplate.replace("numHouses", totalHouseFormat);
						customtemplate = customtemplate.replace("numNoCar", noCarFormat);
						customtemplate = customtemplate.replace("numOneCar", oneCarFormat);
						customtemplate = customtemplate.replace("numTwoCar", twoCarFormat);
					}
					} else {
						customtemplate = customtemplate.replace("countyName", "County "+county);	
						customtemplate = customtemplate.replace("numHouses", "No data available");
						customtemplate = customtemplate.replace("numNoCar", "No data available");
						customtemplate = customtemplate.replace("numOneCar", "No data available");
						customtemplate = customtemplate.replace("numTwoCar", "No data available");
					}
					
					// county population data	
				if(yearString.equals("1971")) {
					if(county.equalsIgnoreCase("Belfast")){
						int popCounty = 0;
						int malesCounty = 0;
						int femalesCounty = 0;

						for(int loop=0; loop<populationBelfast.popBelfast.height; loop++) { //loop through matrix in popAntrim class

							int yearStats = populationBelfast.popBelfast.get(0, loop); // value for year is taken from index 0
							int populationC = populationBelfast.popBelfast.get(1, loop); // value for population is taken from index 1 and so on
							int birthC = populationBelfast.popBelfast.get(2, loop);
							int deathC = populationBelfast.popBelfast.get(3, loop);

							if(yearStats==year){
								popCounty = populationC;
								malesCounty = birthC;
								femalesCounty = deathC;
								break;
							}
						}
						String popFormat = String.format("%,d", popCounty);
						String malesFormat = String.format("%,d", malesCounty);
						String femalesFormat = String.format("%,d", femalesCounty);
						
						customtemplate = customtemplate.replace("countyName", ""+county);
						customtemplate = customtemplate.replace("popData", popFormat);
						customtemplate = customtemplate.replace("numMales", malesFormat);
						customtemplate = customtemplate.replace("numFemales", femalesFormat);
					}
				} else {
					customtemplate = customtemplate.replace("countyName", ""+county);
					customtemplate = customtemplate.replace("popData", "No data available");
					customtemplate = customtemplate.replace("numMales", "No data available");
					customtemplate = customtemplate.replace("numFemales", "No data available");
				}
					
					// car ownership (only hold data for 1971)
				if(yearString.equals("1971")) {
					if(county.equalsIgnoreCase("Belfast")){
						int totalHouses = 0;
						int houseNoCar = 0;
						int houseOneCar = 0;
						int houseTwoPlus = 0;

						for(int loop=0; loop<carOwnersBelfast1971.carBelfast.height; loop++) { //loop through matrix in popAntrim class

							int yearCars = carOwnersBelfast1971.carBelfast.get(0, loop); // value for year is taken from index 0
							int numberHouse = carOwnersBelfast1971.carBelfast.get(1, loop); // value for population is taken from index 1 and so on
							int noCars = carOwnersBelfast1971.carBelfast.get(2, loop);
							int oneCar = carOwnersBelfast1971.carBelfast.get(3, loop);
							int twoCars = carOwnersBelfast1971.carBelfast.get(4, loop);

							if(yearCars==year){
								totalHouses = numberHouse;
								houseNoCar = noCars;
								houseOneCar = oneCar;
								houseTwoPlus = twoCars;
								break;
							}
						}
						String totalHouseFormat = String.format("%,d", totalHouses);
						String noCarFormat = String.format("%,d", houseNoCar);
						String oneCarFormat = String.format("%,d", houseOneCar);
						String twoCarFormat = String.format("%,d", houseTwoPlus);
						
						customtemplate = customtemplate.replace("countyName", ""+county);	
						customtemplate = customtemplate.replace("numHouses", totalHouseFormat);
						customtemplate = customtemplate.replace("numNoCar", noCarFormat);
						customtemplate = customtemplate.replace("numOneCar", oneCarFormat);
						customtemplate = customtemplate.replace("numTwoCar", twoCarFormat);
					}
				} else {
					customtemplate = customtemplate.replace("countyName", ""+county);	
					customtemplate = customtemplate.replace("numHouses", "No data available");
					customtemplate = customtemplate.replace("numNoCar", "No data available");
					customtemplate = customtemplate.replace("numOneCar", "No data available");
					customtemplate = customtemplate.replace("numTwoCar", "No data available");
				}
					
					// industry males
					int searchYear2 = industryMale.yearIndBelf.find(year);
					
					if(yearString.equals("1971")) {
					
					if(county.equalsIgnoreCase("Belfast")){

						int indYear1 = industryMale.yearIndBelf.value[searchYear2 +0];
						if(indYear1!=year) {
							customtemplate = customtemplate.replace("occType01", "");
						} else {
							String industryName1 = industryMale.nameIndBelf.value[searchYear2 +0];
							int total1 = industryMale.totalBelf.value[searchYear2 +0];
							if (industryName1.length()>24) {
								customtemplate = customtemplate.replace("occType01", industryName1.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType01", industryName1+": ");
							}
							customtemplate = customtemplate.replace("total01", ""+total1+"");
						}

						int indYear2 = industryMale.yearIndBelf.value[searchYear2 +1];
						if(indYear2!=year) {
							customtemplate = customtemplate.replace("occType02", "");
						} else {
							String industryName2 = industryMale.nameIndBelf.value[searchYear2 +1];
							int total2 = industryMale.totalBelf.value[searchYear2 +1];
							if (industryName2.length()>24) {
								customtemplate = customtemplate.replace("occType02", industryName2.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType02", industryName2+": ");	
							}
							customtemplate = customtemplate.replace("total02", ""+total2+"");
						}

						int indYear3 = industryMale.yearIndBelf.value[searchYear2 +2];
						if(indYear3!=year) {
							customtemplate = customtemplate.replace("occType03", "");
						} else {
							String industryName3 = industryMale.nameIndBelf.value[searchYear2 +2];
							int total3 = industryMale.totalBelf.value[searchYear2 +2];
							if (industryName3.length()>24) {
								customtemplate = customtemplate.replace("occType03", industryName3.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType03", industryName3+": ");	
							}
							customtemplate = customtemplate.replace("total03", ""+total3+"");
						}		

						int indYear4 = industryMale.yearIndBelf.value[searchYear2 +3];
						if(indYear4!=year) {
							customtemplate = customtemplate.replace("occType04", "");
						} else {
							String industryName4 = industryMale.nameIndBelf.value[searchYear2 +3];
							int total4 = industryMale.totalBelf.value[searchYear2 +3];
							if (industryName4.length()>24) {
								customtemplate = customtemplate.replace("occType04", industryName4.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType04", industryName4+": ");
							}							
							customtemplate = customtemplate.replace("total04", ""+total4+"");
						}

						int indYear5 = industryMale.yearIndBelf.value[searchYear2 +4];
						if(indYear5!=year) {
							customtemplate = customtemplate.replace("occType05", "");
						} else {
							String industryName5 = industryMale.nameIndBelf.value[searchYear2 +4];
							int total5 = industryMale.totalBelf.value[searchYear2 +4];
							if (industryName5.length()>24) {
								customtemplate = customtemplate.replace("occType05", industryName5.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType05", industryName5+": ");
							}
							customtemplate = customtemplate.replace("total05", ""+total5+"");
						}

						//female industry
						int indYear6 = industryFemale.yearIndBelf.value[searchYear2 +5];
						if(indYear6!=year) {
							customtemplate = customtemplate.replace("occType06", "");
						} else {
							String industryName6 = industryFemale.nameIndBelf.value[searchYear2 +5];
							int total6 = industryFemale.totalBelf.value[searchYear2 +5];
							if (industryName6.length()>24) {
								customtemplate = customtemplate.replace("occType06", industryName6.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType06", industryName6+": ");
							}
							customtemplate = customtemplate.replace("total06", ""+total6+"");
						}

						int indYear7 = industryFemale.yearIndBelf.value[searchYear2 +6];
						if(indYear7!=year) {
							customtemplate = customtemplate.replace("occType07", "");
						} else {
							String industryName7 = industryFemale.nameIndBelf.value[searchYear2 +6];
							int total7 = industryFemale.totalBelf.value[searchYear2 +6];
							if (industryName7.length()>24) {
								customtemplate = customtemplate.replace("occType07", industryName7.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType07", industryName7+": ");
							}
							customtemplate = customtemplate.replace("total07", ""+total7+"");
						}

						int indYear8 = industryFemale.yearIndBelf.value[searchYear2 +7];
						if(indYear8!=year) {
							customtemplate = customtemplate.replace("occType08", "");
						} else {
							String industryName8 = industryFemale.nameIndBelf.value[searchYear2 +7];
							int total8 = industryFemale.totalBelf.value[searchYear2 +7];
							if (industryName8.length()>24) {
								customtemplate = customtemplate.replace("occType08", industryName8.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType08", industryName8+": ");
							}
							customtemplate = customtemplate.replace("total08", ""+total8+"");
						}

						int indYear9 = industryFemale.yearIndBelf.value[searchYear2 +8];
						if(indYear9!=year) {
							customtemplate = customtemplate.replace("occType09", "");
						} else {
							String industryName9 = industryFemale.nameIndBelf.value[searchYear2 +8];
							int total9 = industryFemale.totalBelf.value[searchYear2 +8];
							if (industryName9.length()>24) {
								customtemplate = customtemplate.replace("occType09", industryName9.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType09", industryName9+": ");
							}
							customtemplate = customtemplate.replace("total09", ""+total9+"");
						}

						int indYear10 = industryFemale.yearIndBelf.value[searchYear2 +9];
						if(indYear10!=year) {
							customtemplate = customtemplate.replace("occType10", "");
						} else {
							String industryName10 = industryFemale.nameIndBelf.value[searchYear2 +9];
							int total10 = industryFemale.totalBelf.value[searchYear2 +9];
							if (industryName10.length()>24) {
								customtemplate = customtemplate.replace("occType10", industryName10.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType10", industryName10+": ");
							}
							customtemplate = customtemplate.replace("total10", ""+total10+"");
						}

					}
				} else {
					customtemplate = customtemplate.replace("occType01", "No data available");
					customtemplate = customtemplate.replace("occType02", "");
					customtemplate = customtemplate.replace("occType03", "");
					customtemplate = customtemplate.replace("occType04", "");
					customtemplate = customtemplate.replace("occType05", "");
					customtemplate = customtemplate.replace("occType06", "No data available");
					customtemplate = customtemplate.replace("occType07", "");
					customtemplate = customtemplate.replace("occType08", "");
					customtemplate = customtemplate.replace("occType09", "");
					customtemplate = customtemplate.replace("occType10", "");
					
					customtemplate = customtemplate.replace("total01", "");
					customtemplate = customtemplate.replace("total02", "");
					customtemplate = customtemplate.replace("total03", "");
					customtemplate = customtemplate.replace("total04", "");
					customtemplate = customtemplate.replace("total05", "");
					customtemplate = customtemplate.replace("total06", "");
					customtemplate = customtemplate.replace("total07", "");
					customtemplate = customtemplate.replace("total08", "");
					customtemplate = customtemplate.replace("total09", "");
					customtemplate = customtemplate.replace("total10", "");
				}	
				
					//county population data
				if(yearString.equals("1971")) {	
					if(county.equalsIgnoreCase("Derry")){
						int popCounty = 0;
						int malesCounty = 0;
						int femalesCounty = 0;

						for(int loop=0; loop<populationDerry.popDerry.height; loop++) { //loop through matrix in popAntrim class

							int yearStats = populationDerry.popDerry.get(0, loop); // value for year is taken from index 0
							int populationC = populationDerry.popDerry.get(1, loop); // value for population is taken from index 1 and so on
							int birthC = populationDerry.popDerry.get(2, loop);
							int deathC = populationDerry.popDerry.get(3, loop);

							if(yearStats==year){
								popCounty = populationC;
								malesCounty = birthC;
								femalesCounty = deathC;
								break;
							}
						}
						String popFormat = String.format("%,d", popCounty);
						String malesFormat = String.format("%,d", malesCounty);
						String femalesFormat = String.format("%,d", femalesCounty);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);
						customtemplate = customtemplate.replace("popData", popFormat);
						customtemplate = customtemplate.replace("numMales", malesFormat);
						customtemplate = customtemplate.replace("numFemales", femalesFormat);
					}
				} else {
					customtemplate = customtemplate.replace("countyName", "County "+county);
					customtemplate = customtemplate.replace("popData", "No data available");
					customtemplate = customtemplate.replace("numMales", "No data available");
					customtemplate = customtemplate.replace("numFemales", "No data available");
				}
					
					// car ownership (only hold data for 1971)
				if(yearString.equals("1971")) {
					if(county.equalsIgnoreCase("Derry")){
						int totalHouses = 0;
						int houseNoCar = 0;
						int houseOneCar = 0;
						int houseTwoPlus = 0;

						for(int loop=0; loop<carOwnersDerry1971.carDerry.height; loop++) { //loop through matrix in popAntrim class

							int yearCars = carOwnersDerry1971.carDerry.get(0, loop); // value for year is taken from index 0
							int numberHouse = carOwnersDerry1971.carDerry.get(1, loop); // value for population is taken from index 1 and so on
							int noCars = carOwnersDerry1971.carDerry.get(2, loop);
							int oneCar = carOwnersDerry1971.carDerry.get(3, loop);
							int twoCars = carOwnersDerry1971.carDerry.get(4, loop);

							if(yearCars==year){
								totalHouses = numberHouse;
								houseNoCar = noCars;
								houseOneCar = oneCar;
								houseTwoPlus = twoCars;
								break;
							}
						}
						String totalHouseFormat = String.format("%,d", totalHouses);
						String noCarFormat = String.format("%,d", houseNoCar);
						String oneCarFormat = String.format("%,d", houseOneCar);
						String twoCarFormat = String.format("%,d", houseTwoPlus);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);	
						customtemplate = customtemplate.replace("numHouses", totalHouseFormat);
						customtemplate = customtemplate.replace("numNoCar", noCarFormat);
						customtemplate = customtemplate.replace("numOneCar", oneCarFormat);
						customtemplate = customtemplate.replace("numTwoCar", twoCarFormat);
					}
				} else {
					customtemplate = customtemplate.replace("countyName", "County "+county);	
					customtemplate = customtemplate.replace("numHouses", "No data available");
					customtemplate = customtemplate.replace("numNoCar", "No data available");
					customtemplate = customtemplate.replace("numOneCar", "No data available");
					customtemplate = customtemplate.replace("numTwoCar", "No data available");
				}
					// industry males
					int searchYear3 = industryMale.yearIndDerry.find(year);
					
					if(yearString.equals("1971")) {
			
					if(county.equalsIgnoreCase("Derry")){

						int indYear1 = industryMale.yearIndDerry.value[searchYear3 +0];
						if(indYear1!=year) {
							customtemplate = customtemplate.replace("occType01", "");
						} else {
							String industryName1 = industryMale.nameIndDerry.value[searchYear3 +0];
							int total1 = industryMale.totalDerry.value[searchYear3 +0];
							if(industryName1.length()>24) {
								customtemplate = customtemplate.replace("occType01", industryName1.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType01", industryName1+": ");
							} 
							customtemplate = customtemplate.replace("total01", ""+total1+"");
						} 

						int indYear2 = industryMale.yearIndDerry.value[searchYear3 +1];
						if(indYear2!=year) {
							customtemplate = customtemplate.replace("occType02", "");
						} else {
							String industryName2 = industryMale.nameIndDerry.value[searchYear3 +1];
							int total2 = industryMale.totalDerry.value[searchYear3 +1];
							if(industryName2.length()>24) {
								customtemplate = customtemplate.replace("occType02", industryName2.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType02", industryName2+": ");
							}
							customtemplate = customtemplate.replace("total02", ""+total2+"");
						}

						int indYear3 = industryMale.yearIndDerry.value[searchYear3 +2];
						if(indYear3!=year) {
							customtemplate = customtemplate.replace("occType03", "");
						} else {
							String industryName3 = industryMale.nameIndDerry.value[searchYear3 +2];
							int total3 = industryMale.totalDerry.value[searchYear3 +2];
							if(industryName3.length()>24) {
								customtemplate = customtemplate.replace("occType03", industryName3.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType03", industryName3+": ");
							}
							customtemplate = customtemplate.replace("total03", ""+total3+"");
						}		

						int indYear4 = industryMale.yearIndDerry.value[searchYear3 +3];
						if(indYear4!=year) {
							customtemplate = customtemplate.replace("occType04", "");
						} else {
							String industryName4 = industryMale.nameIndDerry.value[searchYear3 +3];
							int total4 = industryMale.totalDerry.value[searchYear3 +3];
							if(industryName4.length()>24) {
								customtemplate = customtemplate.replace("occType04", industryName4.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType04", industryName4+": ");
							}
							customtemplate = customtemplate.replace("total04", ""+total4+"");
						}

						int indYear5 = industryMale.yearIndDerry.value[searchYear3 +4];
						if(indYear5!=year) {
							customtemplate = customtemplate.replace("occType05", "");
						} else {
							String industryName5 = industryMale.nameIndDerry.value[searchYear3 +4];
							int total5 = industryMale.totalDerry.value[searchYear3 +4];
							if(industryName5.length()>24) {
								customtemplate = customtemplate.replace("occType05", industryName5.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType05", industryName5+": ");
							}
							customtemplate = customtemplate.replace("total05", ""+total5+"");
						}

						//female industry
						int indYear6 = industryFemale.yearIndDerry.value[searchYear3 +5];
						if(indYear6!=year) {
							customtemplate = customtemplate.replace("occType06", "");
						} else {
							String industryName6 = industryFemale.nameIndDerry.value[searchYear3 +5];
							int total6 = industryFemale.totalDerry.value[searchYear3 +5];
							if(industryName6.length()>24) {
								customtemplate = customtemplate.replace("occType06", industryName6.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType06", industryName6+": ");
							}
							customtemplate = customtemplate.replace("total06", ""+total6+"");
						}

						int indYear7 = industryFemale.yearIndDerry.value[searchYear3 +6];
						if(indYear7!=year) {
							customtemplate = customtemplate.replace("occType07", "");
						} else {
							String industryName7 = industryFemale.nameIndDerry.value[searchYear3 +6];
							int total7 = industryFemale.totalDerry.value[searchYear3 +6];
							if(industryName7.length()>24) {
								customtemplate = customtemplate.replace("occType07", industryName7.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType07", industryName7+": ");
							}
							customtemplate = customtemplate.replace("total07", ""+total7+"");
						}

						int indYear8 = industryFemale.yearIndDerry.value[searchYear3 +7];
						if(indYear8!=year) {
							customtemplate = customtemplate.replace("occType08", "");
						} else {
							String industryName8 = industryFemale.nameIndDerry.value[searchYear3 +7];
							int total8 = industryFemale.totalDerry.value[searchYear3 +7];
							if(industryName8.length()>24) {
								customtemplate = customtemplate.replace("occType08", industryName8.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType08", industryName8+": ");
							}
							customtemplate = customtemplate.replace("total08", ""+total8+"");
						}

						int indYear9 = industryFemale.yearIndDerry.value[searchYear3 +8];
						if(indYear9!=year) {
							customtemplate = customtemplate.replace("occType09", "");
						} else {
							String industryName9 = industryFemale.nameIndDerry.value[searchYear3 +8];
							int total9 = industryFemale.totalDerry.value[searchYear3 +8];
							if(industryName9.length()>24) {
								customtemplate = customtemplate.replace("occType09", industryName9.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType09", industryName9+": ");
							}
							customtemplate = customtemplate.replace("total09", ""+total9+"");
						}

						int indYear10 = industryFemale.yearIndDerry.value[searchYear3 +9];
						if(indYear10!=year) {
							customtemplate = customtemplate.replace("occType10", "");
						} else {
							String industryName10 = industryFemale.nameIndDerry.value[searchYear3 +9];
							int total10 = industryFemale.totalDerry.value[searchYear3 +9];
							if(industryName10.length()>24) {
								customtemplate = customtemplate.replace("occType10", industryName10.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType10", industryName10+": ");
							}
							customtemplate = customtemplate.replace("total10", ""+total10+"");
						}
					}
				} else {
					customtemplate = customtemplate.replace("occType01", "No data available");
					customtemplate = customtemplate.replace("occType02", "");
					customtemplate = customtemplate.replace("occType03", "");
					customtemplate = customtemplate.replace("occType04", "");
					customtemplate = customtemplate.replace("occType05", "");
					customtemplate = customtemplate.replace("occType06", "No data available");
					customtemplate = customtemplate.replace("occType07", "");
					customtemplate = customtemplate.replace("occType08", "");
					customtemplate = customtemplate.replace("occType09", "");
					customtemplate = customtemplate.replace("occType10", "");
					
					customtemplate = customtemplate.replace("total01", "");
					customtemplate = customtemplate.replace("total02", "");
					customtemplate = customtemplate.replace("total03", "");
					customtemplate = customtemplate.replace("total04", "");
					customtemplate = customtemplate.replace("total05", "");
					customtemplate = customtemplate.replace("total06", "");
					customtemplate = customtemplate.replace("total07", "");
					customtemplate = customtemplate.replace("total08", "");
					customtemplate = customtemplate.replace("total09", "");
					customtemplate = customtemplate.replace("total10", "");
				}
					
					//county population data
				if(yearString.equals("1971")) {	
					if(county.equalsIgnoreCase("Down")){
						int popCounty = 0;
						int malesCounty = 0;
						int femalesCounty = 0;

						for(int loop=0; loop<populationDown.popDown.height; loop++) { //loop through matrix in popAntrim class

							int yearStats = populationDown.popDown.get(0, loop); // value for year is taken from index 0
							int populationC = populationDown.popDown.get(1, loop); // value for population is taken from index 1 and so on
							int birthC = populationDown.popDown.get(2, loop);
							int deathC = populationDown.popDown.get(3, loop);

							if(yearStats==year){
								popCounty = populationC;
								malesCounty = birthC;
								femalesCounty = deathC;
								break;
							}
						}
						String popFormat = String.format("%,d", popCounty);
						String malesFormat = String.format("%,d", malesCounty);
						String femalesFormat = String.format("%,d", femalesCounty);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);
						customtemplate = customtemplate.replace("popData", popFormat);
						customtemplate = customtemplate.replace("numMales", malesFormat);
						customtemplate = customtemplate.replace("numFemales", femalesFormat);
					}
				} else {
					customtemplate = customtemplate.replace("countyName", "County "+county);
					customtemplate = customtemplate.replace("popData", "No data available");
					customtemplate = customtemplate.replace("numMales", "No data available");
					customtemplate = customtemplate.replace("numFemales", "No data available");
				}
					
				// car ownership (only hold data for 1971)
				if(yearString.equals("1971")) {
					if(county.equalsIgnoreCase("Down")){
						int totalHouses = 0;
						int houseNoCar = 0;
						int houseOneCar = 0;
						int houseTwoPlus = 0;

						for(int loop=0; loop<carOwnersDown1971.carDown.height; loop++) { //loop through matrix in popAntrim class

							int yearCars = carOwnersDown1971.carDown.get(0, loop); // value for year is taken from index 0
							int numberHouse = carOwnersDown1971.carDown.get(1, loop); // value for population is taken from index 1 and so on
							int noCars = carOwnersDown1971.carDown.get(2, loop);
							int oneCar = carOwnersDown1971.carDown.get(3, loop);
							int twoCars = carOwnersDown1971.carDown.get(4, loop);

							if(yearCars==year){
								totalHouses = numberHouse;
								houseNoCar = noCars;
								houseOneCar = oneCar;
								houseTwoPlus = twoCars;
								break;
							}
						}
						String totalHouseFormat = String.format("%,d", totalHouses);
						String noCarFormat = String.format("%,d", houseNoCar);
						String oneCarFormat = String.format("%,d", houseOneCar);
						String twoCarFormat = String.format("%,d", houseTwoPlus);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);	
						customtemplate = customtemplate.replace("numHouses", totalHouseFormat);
						customtemplate = customtemplate.replace("numNoCar", noCarFormat);
						customtemplate = customtemplate.replace("numOneCar", oneCarFormat);
						customtemplate = customtemplate.replace("numTwoCar", twoCarFormat);
					} 
				} else {
					customtemplate = customtemplate.replace("countyName", "County "+county);	
					customtemplate = customtemplate.replace("numHouses", "No data available");
					customtemplate = customtemplate.replace("numNoCar", "No data available");
					customtemplate = customtemplate.replace("numOneCar", "No data available");
					customtemplate = customtemplate.replace("numTwoCar", "No data available");
				}
					
					// industry males
					int searchYear4 = industryMale.yearIndDown.find(year);
					
					if(yearString.equals("1971")) {
						
					if(county.equalsIgnoreCase("Down")){

						int indYear1 = industryMale.yearIndDown.value[searchYear4 +0];
						if(indYear1!=year) {
							customtemplate = customtemplate.replace("occType01", "");
						} else {
							String industryName1 = industryMale.nameIndDown.value[searchYear4 +0];
							int total1 = industryMale.totalDown.value[searchYear4 +0];
							if(industryName1.length()>24) {
								customtemplate = customtemplate.replace("occType01", industryName1.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType01", industryName1+": ");
							}
							customtemplate = customtemplate.replace("total01", ""+total1+"");
						}

						int indYear2 = industryMale.yearIndDown.value[searchYear4 +1];
						if(indYear2!=year) {
							customtemplate = customtemplate.replace("occType02", "");
						} else {
							String industryName2 = industryMale.nameIndDown.value[searchYear4 +1];
							int total2 = industryMale.totalDown.value[searchYear4 +1];
							if(industryName2.length()>24) {
								customtemplate = customtemplate.replace("occType02", industryName2.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType02", industryName2+": ");
							}
							customtemplate = customtemplate.replace("total02", ""+total2+"");
						}

						int indYear3 = industryMale.yearIndDown.value[searchYear4 +2];
						if(indYear3!=year) {
							customtemplate = customtemplate.replace("occType03", "");
						} else {
							String industryName3 = industryMale.nameIndDown.value[searchYear4 +2];
							int total3 = industryMale.totalDown.value[searchYear4 +2];
							if(industryName3.length()>24) {
								customtemplate = customtemplate.replace("occType03", industryName3.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType03", industryName3+": ");
							}
							customtemplate = customtemplate.replace("total03", ""+total3+"");
						}		

						int indYear4 = industryMale.yearIndDown.value[searchYear4 +3];
						if(indYear4!=year) {
							customtemplate = customtemplate.replace("occType04", "");
						} else {
							String industryName4 = industryMale.nameIndDown.value[searchYear4 +3];
							int total4 = industryMale.totalDown.value[searchYear4 +3];
							if(industryName4.length()>24) {
								customtemplate = customtemplate.replace("occType04", industryName4.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType04", industryName4+": ");
							}
							customtemplate = customtemplate.replace("total04", ""+total4+"");
						}

						int indYear5 = industryMale.yearIndDown.value[searchYear4 +4];
						if(indYear5!=year) {
							customtemplate = customtemplate.replace("occType05", "");
						} else {
							String industryName5 = industryMale.nameIndDown.value[searchYear4 +4];
							int total5 = industryMale.totalDown.value[searchYear4 +4];
							if(industryName5.length()>24) {
								customtemplate = customtemplate.replace("occType05", industryName5.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType05", industryName5+": ");
							}
							customtemplate = customtemplate.replace("total05", ""+total5+"");
						}

						//female industry
						int indYear6 = industryFemale.yearIndDown.value[searchYear4 +5];
						if(indYear6!=year) {
							customtemplate = customtemplate.replace("occType06", "");
						} else {
							String industryName6 = industryFemale.nameIndDown.value[searchYear4 +5];
							int total6 = industryFemale.totalDown.value[searchYear4 +5];
							if(industryName6.length()>24) {
								customtemplate = customtemplate.replace("occType06", industryName6.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType06", industryName6+": ");
							}
							customtemplate = customtemplate.replace("total06", ""+total6+"");
						}

						int indYear7 = industryFemale.yearIndDown.value[searchYear4 +6];
						if(indYear7!=year) {
							customtemplate = customtemplate.replace("occType07", "");
						} else {
							String industryName7 = industryFemale.nameIndDown.value[searchYear4 +6];
							int total7 = industryFemale.totalDown.value[searchYear4 +6];
							if(industryName7.length()>24) {
								customtemplate = customtemplate.replace("occType07", industryName7.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType07", industryName7+": ");
							}
							customtemplate = customtemplate.replace("total07", ""+total7+"");
						}

						int indYear8 = industryFemale.yearIndDown.value[searchYear4 +7];
						if(indYear8!=year) {
							customtemplate = customtemplate.replace("occType08", "");
						} else {
							String industryName8 = industryFemale.nameIndDown.value[searchYear4 +7];
							int total8 = industryFemale.totalDown.value[searchYear4 +7];
							if(industryName8.length()>24) {
								customtemplate = customtemplate.replace("occType08", industryName8.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType08", industryName8+": ");
							}
							customtemplate = customtemplate.replace("total08", ""+total8+"");
						}

						int indYear9 = industryFemale.yearIndDown.value[searchYear4 +8];
						if(indYear9!=year) {
							customtemplate = customtemplate.replace("occType09", "");
						} else {
							String industryName9 = industryFemale.nameIndDown.value[searchYear4 +8];
							int total9 = industryFemale.totalDown.value[searchYear4 +8];
							if(industryName9.length()>24) {
								customtemplate = customtemplate.replace("occType09", industryName9.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType09", industryName9+": ");
							}
							customtemplate = customtemplate.replace("total09", ""+total9+"");
						}

						int indYear10 = industryFemale.yearIndDown.value[searchYear4 +9];
						if(indYear10!=year) {
							customtemplate = customtemplate.replace("occType10", "");
						} else {
							String industryName10 = industryFemale.nameIndDown.value[searchYear4 +9];
							int total10 = industryFemale.totalDown.value[searchYear4 +9];
							if(industryName10.length()>24) {
								customtemplate = customtemplate.replace("occType10", industryName10.substring(0, 25)+"...: ");
							} else {
								customtemplate = customtemplate.replace("occType10", industryName10+": ");
							}
							customtemplate = customtemplate.replace("total10", ""+total10+"");
						}
					}
				} else {
					customtemplate = customtemplate.replace("occType01", "No data available");
					customtemplate = customtemplate.replace("occType02", "");
					customtemplate = customtemplate.replace("occType03", "");
					customtemplate = customtemplate.replace("occType04", "");
					customtemplate = customtemplate.replace("occType05", "");
					customtemplate = customtemplate.replace("occType06", "No data available");
					customtemplate = customtemplate.replace("occType07", "");
					customtemplate = customtemplate.replace("occType08", "");
					customtemplate = customtemplate.replace("occType09", "");
					customtemplate = customtemplate.replace("occType10", "");
					
					customtemplate = customtemplate.replace("total01", "");
					customtemplate = customtemplate.replace("total02", "");
					customtemplate = customtemplate.replace("total03", "");
					customtemplate = customtemplate.replace("total04", "");
					customtemplate = customtemplate.replace("total05", "");
					customtemplate = customtemplate.replace("total06", "");
					customtemplate = customtemplate.replace("total07", "");
					customtemplate = customtemplate.replace("total08", "");
					customtemplate = customtemplate.replace("total09", "");
					customtemplate = customtemplate.replace("total10", "");	
				}
					// fermanagh county data
				if(yearString.equals("1971")) {	
					if(county.equalsIgnoreCase("Fermanagh")){
						int popCounty = 0;
						int malesCounty = 0;
						int femalesCounty = 0;

						for(int loop=0; loop<populationFerm.popFermanagh.height; loop++) { //loop through matrix in popAntrim class

							int yearStats = populationFerm.popFermanagh.get(0, loop); // value for year is taken from index 0
							int populationC = populationFerm.popFermanagh.get(1, loop); // value for population is taken from index 1 and so on
							int birthC = populationFerm.popFermanagh.get(2, loop);
							int deathC = populationFerm.popFermanagh.get(3, loop);

							if(yearStats==year){
								popCounty = populationC;
								malesCounty = birthC;
								femalesCounty = deathC;
								break;
							}
						}
						String popFormat = String.format("%,d", popCounty);
						String malesFormat = String.format("%,d", malesCounty);
						String femalesFormat = String.format("%,d", femalesCounty);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);
						customtemplate = customtemplate.replace("popData", popFormat);
						customtemplate = customtemplate.replace("numMales", malesFormat);
						customtemplate = customtemplate.replace("numFemales", femalesFormat);
					}
				} else {
					customtemplate = customtemplate.replace("countyName", "County "+county);
					customtemplate = customtemplate.replace("popData", "No data available");
					customtemplate = customtemplate.replace("numMales", "No data available");
					customtemplate = customtemplate.replace("numFemales", "No data available");
				}
					
					// car ownership (only hold data for 1971)
				if(yearString.equals("1971")) {	
					if(county.equalsIgnoreCase("Fermanagh")){
						int totalHouses = 0;
						int houseNoCar = 0;
						int houseOneCar = 0;
						int houseTwoPlus = 0;

						for(int loop=0; loop<carOwnersFerm1971.carFermanagh.height; loop++) { //loop through matrix in popAntrim class

							int yearCars = carOwnersFerm1971.carFermanagh.get(0, loop); // value for year is taken from index 0
							int numberHouse = carOwnersFerm1971.carFermanagh.get(1, loop); // value for population is taken from index 1 and so on
							int noCars = carOwnersFerm1971.carFermanagh.get(2, loop);
							int oneCar = carOwnersFerm1971.carFermanagh.get(3, loop);
							int twoCars = carOwnersFerm1971.carFermanagh.get(4, loop);

							if(yearCars==year){
								totalHouses = numberHouse;
								houseNoCar = noCars;
								houseOneCar = oneCar;
								houseTwoPlus = twoCars;
								break;
							}
						}
						String totalHouseFormat = String.format("%,d", totalHouses);
						String noCarFormat = String.format("%,d", houseNoCar);
						String oneCarFormat = String.format("%,d", houseOneCar);
						String twoCarFormat = String.format("%,d", houseTwoPlus);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);	
						customtemplate = customtemplate.replace("numHouses", totalHouseFormat);
						customtemplate = customtemplate.replace("numNoCar", noCarFormat);
						customtemplate = customtemplate.replace("numOneCar", oneCarFormat);
						customtemplate = customtemplate.replace("numTwoCar", twoCarFormat);
						
						customtemplate = customtemplate.replace("occType01", "No data available"); // no data available for this topic
						customtemplate = customtemplate.replace("occType02", "");
						customtemplate = customtemplate.replace("occType03", "");
						customtemplate = customtemplate.replace("occType04", "");
						customtemplate = customtemplate.replace("occType05", "");
						customtemplate = customtemplate.replace("occType06", "No data available");
						customtemplate = customtemplate.replace("occType07", "");
						customtemplate = customtemplate.replace("occType08", "");
						customtemplate = customtemplate.replace("occType09", "");
						customtemplate = customtemplate.replace("occType10", "");
						
						customtemplate = customtemplate.replace("total01", "");
						customtemplate = customtemplate.replace("total02", "");
						customtemplate = customtemplate.replace("total03", "");
						customtemplate = customtemplate.replace("total04", "");
						customtemplate = customtemplate.replace("total05", "");
						customtemplate = customtemplate.replace("total06", "");
						customtemplate = customtemplate.replace("total07", "");
						customtemplate = customtemplate.replace("total08", "");
						customtemplate = customtemplate.replace("total09", "");
						customtemplate = customtemplate.replace("total10", "");
					}
				} else {
					customtemplate = customtemplate.replace("countyName", "County "+county);	
					customtemplate = customtemplate.replace("numHouses", "No data available");
					customtemplate = customtemplate.replace("numNoCar", "No data available");
					customtemplate = customtemplate.replace("numOneCar", "No data available");
					customtemplate = customtemplate.replace("numTwoCar", "No data available");
					
					customtemplate = customtemplate.replace("occType01", "No data available");
					customtemplate = customtemplate.replace("occType02", "");
					customtemplate = customtemplate.replace("occType03", "");
					customtemplate = customtemplate.replace("occType04", "");
					customtemplate = customtemplate.replace("occType05", "");
					customtemplate = customtemplate.replace("occType06", "No data available");
					customtemplate = customtemplate.replace("occType07", "");
					customtemplate = customtemplate.replace("occType08", "");
					customtemplate = customtemplate.replace("occType09", "");
					customtemplate = customtemplate.replace("occType10", "");
					
					customtemplate = customtemplate.replace("total01", "");
					customtemplate = customtemplate.replace("total02", "");
					customtemplate = customtemplate.replace("total03", "");
					customtemplate = customtemplate.replace("total04", "");
					customtemplate = customtemplate.replace("total05", "");
					customtemplate = customtemplate.replace("total06", "");
					customtemplate = customtemplate.replace("total07", "");
					customtemplate = customtemplate.replace("total08", "");
					customtemplate = customtemplate.replace("total09", "");
					customtemplate = customtemplate.replace("total10", "");				
					
				}
							
				// tyrone county data
				// population
				if(yearString.equals("1971")) {	
					if(county.equalsIgnoreCase("Tyrone")){
						int popCounty = 0;
						int malesCounty = 0;
						int femalesCounty = 0;

						for(int loop=0; loop<populationTyrone.popTyrone.height; loop++) { //loop through matrix in popAntrim class

							int yearStats = populationTyrone.popTyrone.get(0, loop); // value for year is taken from index 0
							int populationC = populationTyrone.popTyrone.get(1, loop); // value for population is taken from index 1 and so on
							int birthC = populationTyrone.popTyrone.get(2, loop);
							int deathC = populationTyrone.popTyrone.get(3, loop);

							if(yearStats==year){
								popCounty = populationC;
								malesCounty = birthC;
								femalesCounty = deathC;
								break;
							}
						}
						String popFormat = String.format("%,d", popCounty);
						String malesFormat = String.format("%,d", malesCounty);
						String femalesFormat = String.format("%,d", femalesCounty);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);
						customtemplate = customtemplate.replace("popData", popFormat);
						customtemplate = customtemplate.replace("numMales", malesFormat);
						customtemplate = customtemplate.replace("numFemales", femalesFormat);
					}
				} else {
					customtemplate = customtemplate.replace("countyName", "County "+county);
					customtemplate = customtemplate.replace("popData", "No data available");
					customtemplate = customtemplate.replace("numMales", "No data available");
					customtemplate = customtemplate.replace("numFemales", "No data available");
				}
					
					// car ownership (only hold data for 1971)
				if(yearString.equals("1971")) {	
					if(county.equalsIgnoreCase("Tyrone")){
						int totalHouses = 0;
						int houseNoCar = 0;
						int houseOneCar = 0;
						int houseTwoPlus = 0;

						for(int loop=0; loop<carOwnersTyrone1971.carTyrone.height; loop++) { //loop through matrix in popAntrim class

							int yearCars = carOwnersTyrone1971.carTyrone.get(0, loop); // value for year is taken from index 0
							int numberHouse = carOwnersTyrone1971.carTyrone.get(1, loop); // value for population is taken from index 1 and so on
							int noCars = carOwnersTyrone1971.carTyrone.get(2, loop);
							int oneCar = carOwnersTyrone1971.carTyrone.get(3, loop);
							int twoCars = carOwnersTyrone1971.carTyrone.get(4, loop);

							if(yearCars==year){
								totalHouses = numberHouse;
								houseNoCar = noCars;
								houseOneCar = oneCar;
								houseTwoPlus = twoCars;
								break;
							}
						}
						String totalHouseFormat = String.format("%,d", totalHouses);
						String noCarFormat = String.format("%,d", houseNoCar);
						String oneCarFormat = String.format("%,d", houseOneCar);
						String twoCarFormat = String.format("%,d", houseTwoPlus);
						
						customtemplate = customtemplate.replace("countyName", "County "+county);	
						customtemplate = customtemplate.replace("numHouses", totalHouseFormat);
						customtemplate = customtemplate.replace("numNoCar", noCarFormat);
						customtemplate = customtemplate.replace("numOneCar", oneCarFormat);
						customtemplate = customtemplate.replace("numTwoCar", twoCarFormat);
						
						customtemplate = customtemplate.replace("occType01", "No data available");
						customtemplate = customtemplate.replace("occType02", "");
						customtemplate = customtemplate.replace("occType03", "");
						customtemplate = customtemplate.replace("occType04", "");
						customtemplate = customtemplate.replace("occType05", "");
						customtemplate = customtemplate.replace("occType06", "No data available");
						customtemplate = customtemplate.replace("occType07", "");
						customtemplate = customtemplate.replace("occType08", "");
						customtemplate = customtemplate.replace("occType09", "");
						customtemplate = customtemplate.replace("occType10", "");
						
						customtemplate = customtemplate.replace("total01", "");
						customtemplate = customtemplate.replace("total02", "");
						customtemplate = customtemplate.replace("total03", "");
						customtemplate = customtemplate.replace("total04", "");
						customtemplate = customtemplate.replace("total05", "");
						customtemplate = customtemplate.replace("total06", "");
						customtemplate = customtemplate.replace("total07", "");
						customtemplate = customtemplate.replace("total08", "");
						customtemplate = customtemplate.replace("total09", "");
						customtemplate = customtemplate.replace("total10", "");
					}
				} else {
					customtemplate = customtemplate.replace("countyName", "County "+county);	
					customtemplate = customtemplate.replace("numHouses", "No data available");
					customtemplate = customtemplate.replace("numNoCar", "No data available");
					customtemplate = customtemplate.replace("numOneCar", "No data available");
					customtemplate = customtemplate.replace("numTwoCar", "No data available");
					
					customtemplate = customtemplate.replace("occType01", "No data available");
					customtemplate = customtemplate.replace("occType02", "");
					customtemplate = customtemplate.replace("occType03", "");
					customtemplate = customtemplate.replace("occType04", "");
					customtemplate = customtemplate.replace("occType05", "");
					customtemplate = customtemplate.replace("occType06", "No data available");
					customtemplate = customtemplate.replace("occType07", "");
					customtemplate = customtemplate.replace("occType08", "");
					customtemplate = customtemplate.replace("occType09", "");
					customtemplate = customtemplate.replace("occType10", "");
					
					customtemplate = customtemplate.replace("total01", "");
					customtemplate = customtemplate.replace("total02", "");
					customtemplate = customtemplate.replace("total03", "");
					customtemplate = customtemplate.replace("total04", "");
					customtemplate = customtemplate.replace("total05", "");
					customtemplate = customtemplate.replace("total06", "");
					customtemplate = customtemplate.replace("total07", "");
					customtemplate = customtemplate.replace("total08", "");
					customtemplate = customtemplate.replace("total09", "");
					customtemplate = customtemplate.replace("total10", "");
				}

					// NI population data
					int thispop = 0;
					int thisbirths = 0;
					int thisdeaths = 0;

					for(int loop=0; loop< niVitals.popvitalstats.height; loop++) { //loop through matrix in popvitalstats object

						int yearStats = niVitals.popvitalstats.get(0, loop); // value for year is taken from index 0
						int pop = niVitals.popvitalstats.get(1, loop); // value for population is taken from index 1 and so on
						int birth = niVitals.popvitalstats.get(2, loop);
						int death = niVitals.popvitalstats.get(3, loop);

						if(yearStats==year){
							thispop = pop;
							thisbirths = birth;
							thisdeaths = death;
							break;
						}
					}
					String popNIFormat = String.format("%,d", thispop);
					String birthsFormat = String.format("%,d", thisbirths);
					String deathsFormat = String.format("%,d", thisdeaths);
									
					customtemplate = customtemplate.replace("thisyear", ""+year);
					customtemplate = customtemplate.replace("popNI", popNIFormat);
					customtemplate = customtemplate.replace("birthsNI", birthsFormat);
					customtemplate = customtemplate.replace("deathsNI", deathsFormat);


					toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, customtemplate ); // HTTP_OK and MIME_HTML standard checks, 

				} else {
					String asFilepath = fileStoreInterface.decodeFilePath(toProcess.path);
						if((asFilepath!=null)&&fileStoreInterface.exists(asFilepath)){
							toProcess.r = WebResponse.serveFile(toProcess.params, asFilepath);
						}
				}

				//

				//If none of the previous rules identified a response then either the 
				//website is broken pointing to a url that no longer exists
				//or someone is deliberately trying to access the site in a way that wasn't designed for
				//either way respond with a redirection message to the index page
				if(toProcess.r == null)
				{
					if(toProcess.path.equalsIgnoreCase("index.html"))
					{
						toProcess.r = new WebResponse( WebResponse.HTTP_NOTFOUND, WebResponse.MIME_PLAINTEXT,
								"Error 404, file not found." );
					}
					else
					{
						String url = "/";
						toProcess.r = new WebResponse( WebResponse.HTTP_REDIRECT, WebResponse.MIME_HTML,
								"<html><body>Redirected: <a href=\"" + url + "\">" +
										url + "</a></body></html>");
						toProcess.r.addHeader( "Location", url );
					}
				}

				//create a new thread that will send the response to the webbrowser
				Thread t = new Thread( toProcess );
				//daemon means that even if the program is closed this thread will continue until
				//it is finished
				t.setDaemon( true );
				//this starts the thread running
				t.start();		        
			}			

			//This command pauses the update loop for 10 milliseconds
			//the goal is to have the total time to calculate each loop be under 16.7 milliseconds
			//if this performance goal is reached then the server will be able to respond to
			//changes faster than the eye can detect changes 
			//if a graphical user interface were added to this application 
			//it would appear to be responding instantaneously
			Thread.sleep(10);
		}

		//Cleanly shuts down the application
		//it disconnects from the database and the file store, ensuring that information is saved
		//to disk as required
	}

	//NOTE
	//It is useful to have a simple main class for your application that can be easily modified
	//This ensures that you can quickly produce demos or add one-off functionality
	//some libraries take control of the main function and require that you
	//supply functions that modify parts of how they work, 
	//these are typically referred to as "opinionated" libraries
	//"opinionated" libraries try to make development simple and fast by hiding details from the user
	//they also restrict how you can modify them to reduce errors and improve performance
	//the disadvantage of using "opinionated" libraries is that you typically become 
	//locked-in to the libraries. By using them you develop skills with using a library 
	//but not necessarily with core programming skills. To truly master programming you want
	//to be able to get a computer to do anything you want and to understand any part of how it
	//works. To develop this skill it is a good idea to get practice working with "unopinionated" libraries
	//and to understand the software you use to such an extent you are happy to modify any part of it

	public static void registerShutdownHook( final DatabaseInterface s )
	{
		// Registers a shutdown hook for the database so that it
		// shuts down nicely when the Virtual Machine exits (even if you "Ctrl-C" the
		// running application).
		Runtime.getRuntime().addShutdownHook( new Thread()
		{
			@Override
			public void run()
			{
				s.close();
			}
		} );
	}

}