/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regit;

import com.universaldevices.common.Constants;
import com.universaldevices.device.model.UDNode;
import com.universaldevices.resources.errormessages.Errors;
import com.universaldevices.security.upnp.UPnPSecurity;
import com.universaldevices.soap.UDHTTPResponse;
import com.universaldevices.upnp.UDProxyDevice;
import java.util.Arrays;

public class RegIT {
    protected static Insteon insteon = null;
    
    public static boolean subscribe(String url)
    {
        if (insteon == null)
            return false;
        UDProxyDevice device = insteon.getDevice();
        if (device == null || !device.isOnline)
            return false;
        StringBuilder request;
        request = new StringBuilder();
        request.append("<reportURL>");
        request.append(url);
        request.append("</reportURL><duration>");
        request.append(Constants.UD_SUBSCRIPTION_DURATION);
        request.append("</duration>");
        StringBuffer sb = new StringBuffer(request);
        
        UDHTTPResponse response;
        response = device.submitSOAPRequest("Subscribe", sb, UPnPSecurity.SIGN_WITH_HMAC_KEY, false, false);
        if (!response.opStat || response.body == null)
        {
            Errors.showError(response.status, null, insteon.getDevice());
            return false;
        }
        System.out.println(response.body);
        return true;
    }
    
    public RegIT() throws Exception
    {
        insteon = new Insteon();
        insteon.start("uuid:00:21:b9:02:17:1b","http://192.168.172.199:80/");
    }
    
    public static void main(String[] args) throws Exception {
        final RegIT regit = new RegIT();
        try {
//            RegIT.insteon.queryNode("33 12 67 F");
            UDProxyDevice device = RegIT.insteon.getDevice();
            UDNode node = device.getNode("33 12 67 F");
            System.out.println(node.name);
            
/*            if (!subscribe("http://192.168.172.30:8026/ORServer/events/uuid:00:21:b9:02:17:1b"))
            {
                System.out.println("Could not subscribe");
            }
            while (true)
            {
                Thread.sleep(10000);
            }*/
        }
        catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
