//Authors: Gabriella Morelli, Mkyong. Jan 05, 2020
// Sending email API adopted from: https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.io.*;
import java.util.*;

public class secretSanta {

    public static void main(String[] args) {
	
	//Using a gmail account as an SMPT server. In order to run this you have to
	//go into your account settings and allow less secure apps to login to your account.
	//This will not work if you have two-factor authentication enabled.
        final String username = "youremail0@gmail.com";
        final String password = "password";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
		
			File fName = new File(args[0]);
			Scanner s;
			Random rand = new Random();

			s = new Scanner(fName);
			
			List<String> friends = new ArrayList<String>();
			List<List<String>> combos = new ArrayList<List<String>>();
			List<String> emails = new ArrayList<String>();
		
		    	//reading in friends list
			while(s.hasNextLine()) {
				friends.add(s.nextLine());
			}
			
			//reading in friends email list
			
			File fEmail = new File(args[1]);
			Scanner s2 = new Scanner(fEmail);
			
			while(s2.hasNextLine()){
				emails.add(s2.nextLine());
			}

			Scanner t = new Scanner(System.in);

			List<String> categories = new ArrayList<String>();

			for(int i = 0; i < friends.size(); i++){
				System.out.println("Current friend: " + friends.get(i) );
				System.out.println("Enter next category.");
				categories.add(t.nextLine());

			}

			List<String> first = copyArrayList(friends);
			ArrayList<String> choose = copyArrayList(friends);

			combos.add(first);
			

			for(int j = friends.size()-1; j > 0; j--){
				int lastIndex = (friends.size()-1);
				String last = friends.get(lastIndex);

				//below print statements are for testing
				//System.out.println("moving to first: " + last);

				friends.add(0, last);
				//System.out.println("being removed: " + friends.get(lastIndex));
				friends.remove(friends.size()-1);
						
				List<String> next = copyArrayList(friends);
				combos.add(next);
			}

			for(int i = 0; i < choose.size(); i++){

				int rando = rand.nextInt(choose.size());
				//System.out.println("value of rand: " + rando);

				while((choose.get(rando)).equals("done")){
					rando = rand.nextInt(choose.size());
				}
				if(!(choose.get(rando)).equals("done")){
					String rec = choose.get(rando);
					String eRec = emails.get(rando);
					System.out.println(eRec);
					
					//String result = "";
					Message message = new MimeMessage(session);
            		message.setFrom(new InternetAddress("youremail@gmail.com"));
            		message.setRecipients(
                    	Message.RecipientType.TO,
                    	InternetAddress.parse(eRec)
            		);
            		message.setSubject("You have been invited to join...CYSTMAS! (v2.0 stuffHesperusHates)");
            

            
					String result = (choose.get(rando) + " gets...\n\n");
					for(int j = 0; j < combos.size(); j++){
						//System.out.println(choose.get(rando) + " gets...");
						result = result + "You will give a " + categories.get(j) + " to: ";
						result = result + combos.get(rando).get(j);
						result = result + "\n\n";
					}
					message.setText("Congregations! \n Dear unfortunate, your list has come in: " + result + "\n\n Love you, \n Ella's computer \n 01110100 01101000 01100101 01111001 00101111 01110100 01101000 01100101 01101101 00001010");
					
					Transport.send(message);
					choose.set(rando, "done");
					System.out.println("Email sent. moving on to next person...");

				}
			}

          

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }catch(IndexOutOfBoundsException h){
			System.out.println("dumb arrays.");
		}
		catch(FileNotFoundException y) {

			System.out.println("file does not exist");
			
		} catch(Exception g){
			System.out.println("General error");
		}
    }
	private static ArrayList<String> copyArrayList(List<String> input){
		ArrayList<String> output = new ArrayList<String>();

		for(String dup: input){
			output.add(dup);
		}
		return output;
	}
	

}
