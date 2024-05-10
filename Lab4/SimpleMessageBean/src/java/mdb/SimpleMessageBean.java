/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/MessageDrivenBean.java to edit this template
 */
package mdb;

import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Notebook
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/Queue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class SimpleMessageBean implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    public SimpleMessageBean() {
    }
    static final Logger logger = Logger.getLogger("SimpleMessageBean");

    @Override
    public void onMessage(Message inMessage) {

        TextMessage msg = null;
        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                String msg_s = msg.getText();
                char[] msg_c = msg_s.toCharArray();
                logger.info("MESSAGE BEAN: Message received: "
                        + msg.getText());
                BufferedReader abc = new BufferedReader(new FileReader("D:\\FCP\\SEM6\\Rice\\Lab4\\dataLab4.txt"));
                List<String> lines = new ArrayList<String>();
                String line;
                while ((line = abc.readLine()) != null) {
                    lines.add(line);
                }
                abc.close();
                for (int i = 0; i < lines.size(); i++) {
                    char[] line_c = lines.get(i).toCharArray();
                    for (int j = 0; j < line_c.length; j++) {
                        if(line_c[j] == msg_c[0])
                        {
                            
                            
                            boolean equal = true;
                            int counter = 1;
                            for(int k = 1, len = j+1; k < msg_c.length && len < line_c.length; k++, len++)
                            {
                                
                                if(line_c[len] != msg_c[k])
                                {
                                    equal = false;
                                    break;
                                }
                                counter++;
                                
                            }
                            if(equal == true && counter == msg_c.length)
                            {
                                for(int k = j, l = 0; l < msg_c.length && k < line_c.length; k++, l++)
                                {
                                    line_c[k] = '*';
                                }
                                logger.info(String.valueOf(line_c));
                                lines.set(i, String.valueOf(line_c));
                            }
                        }
                    }
                }
                Files.write(Paths.get("D:\\FCP\\SEM6\\Rice\\Lab4\\dataLab4.txt"), lines, StandardOpenOption.CREATE);
            } else {
                logger.warning("Message of wrong type: "
                        + inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }
}
