package scripts.friends;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.input.Keyboard;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Ending;
import org.tribot.script.interfaces.Starting;
import org.tribot.util.Util;

@ScriptManifest(authors = { "Jerminater" }, category = "Tools", 
	name = "jFriends", version = 1.00, 
	description = "Will Add friends to ignore or friends list", gameMode = 1)

public class JFriends extends Script implements  Starting, Ending {
		
	public void onStart() {
		
	}
	
	@Override
	public void run() {
		//making sure we are logged in
		while (Login.getLoginState() != STATE.INGAME)
			General.sleep(300, 500);

		 // The name of the file to open.
        String fileName = Util.getWorkingDirectory() + "\\FriendsList\\scripts\\friends\\Friends.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) 
            {
            	General.sleep(500);
            	// add > parent 429 child 12
            	// ignore > 432 > 11
            	if (line.substring(0, 2).equals("f-")) 
            	{
            		if (GameTab.open(GameTab.TABS.FRIENDS)) 
            		{
            			RSInterfaceChild i = Interfaces.get(429,12);
            			if (Interfaces.isInterfaceSubstantiated(i)) 
            			{
            				Clicking.click(i);
            				General.sleep(500);
            				Keyboard.typeSend(line.substring(2));
            			}
            		}
            	} else if (line.substring(0, 2).equals("i-")) 
            	{
            		if (GameTab.open(GameTab.TABS.IGNORE)) {
            			RSInterfaceChild i = Interfaces.get(432,11);
            			if (Interfaces.isInterfaceSubstantiated(i)) 
            			{
            				Clicking.click(i);
            				General.sleep(500);
            				Keyboard.typeSend(line.substring(2));
            			}
            		}
            	}
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }	
	
	@Override
	public void onEnd() {}

}


	
	

