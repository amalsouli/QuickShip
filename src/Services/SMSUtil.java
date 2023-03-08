/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author ThinkPad
 */
public class SMSUtil {
     private static final String ACCOUNT_SID = "AC92982973f0e287813e3c04d3338b9cc4";
   // Votre jeton d'authentification Twilio
   private static final String AUTH_TOKEN = "12d5fec947daf39c4a48d8c9e3129ab6";
   // Votre numéro de téléphone Twilio
   private static final String TWILIO_NUMBER = "+15672922690";

   public static void sendSMS(String toPhoneNumber, String message) throws Exception {
      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

      Message sms = Message.creator(
            new PhoneNumber(toPhoneNumber),
            new PhoneNumber(TWILIO_NUMBER),
            message
      ).create();

      System.out.println("SMS sent: " + sms.getSid());
   }
}
