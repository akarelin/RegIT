package regit;

import com.nanoxml.XMLElement;
import com.udi.isy.jsdk.insteon.*;
import com.universaldevices.client.NoDeviceException;
import com.universaldevices.common.properties.UDProperty;
import com.universaldevices.device.model.UDControl;
import com.universaldevices.device.model.UDFolder;
import com.universaldevices.device.model.UDGroup;
import com.universaldevices.device.model.UDNode;
import com.universaldevices.security.upnp.UPnPSecurity;
import com.universaldevices.upnp.UDProxyDevice;

public class Insteon extends ISYInsteonClient 
{
    public Insteon() { 
        super(); 
    }
    
    @Override
    public void onNewDeviceAnnounced(UDProxyDevice device) {
        if (device.securityLevel>UPnPSecurity.NO_SECURITY) {
            if (device.isAuthenticated && device.isOnline) return;
            
            try { super.authenticate("admin", "admin"); }
            catch(NoDeviceException e) {
                System.err.println("This should never happen!");
            }
        }
        else {
            device.subscribeToEvents(true);
        }
    }
    
    @Override
    public void onDiscoveringNodes() { 
        System.out.println("I am in Linking Mode ...");
    }

    @Override
    public void onNodeDiscoveryStopped() {
        System.out.println("I am no longer in Linking mode ...");
    }

    @Override
    public void onGroupRemoved(String groupAddress) { 
        System.out.println("Scene: "+groupAddress+ "was removed by someone or something!");
    }

    @Override
    public void onGroupRenamed( UDGroup group) {
        System.out.println("Scene: "+group.address+"was renamed to "+group.name);
    }
    
    @Override
    public void onModelChanged(UDControl control, Object value, UDNode node) {
        System.out.println("Someone or something changed "+control.label+ " to "+value+" at "+node.name);
    }

    @Override
    public void onNetworkRenamed(String newName) {
            System.out.println("Ah, the network was renamed to " + newName);
    }

    @Override
    public void onNewGroup(UDGroup newGroup) {
        System.out.println("Yummy: we now have a new scene with address"+newGroup.address+" and name "+newGroup.name);
    }

    @Override
    public void onNewNode(UDNode newNode) {
        System.out.println("Yummy: we now have a new Insteon device with address "+newNode.address+" and name "+newNode.name);
    }
    
    @Override
    public void onNodeError(UDNode node) {
        System.out.println("What's going on? The Insteon device at address "+node.address + " and name "+ node.name+" is no longer responding to my communication attempts!");
    }

    @Override
    public void onNodeRemoved(String nodeAddress) { 
        System.out.println("Whooah ... node with address "+nodeAddress+" was permanently removed from ISY");
    }

    @Override
    public void onNodeRemovedFromGroup(UDNode node, UDGroup group) { 
        System.out.println("Insteon device with address "+node.address+" and name "+node.name+" is no longer part of the "+group.name+ " scene!");
    }

    @Override
    public void onNodeRenamed(UDNode node) {
        System.out.println("Insteon device with address "+node.address+" was renamed to "+node.name);
    }

    @Override
    public void onNodeMovedAsSlave(UDNode node, UDGroup group) { 
        System.out.println("Insteon device "+node.name+" is now part of the "+group.name+ " scene as a slave/responder");
    }

    @Override
    public void onDeviceOffLine() {
        System.out.println("oo; ISY is offLine. Did you unplug it?");
    }

    @Override
    public void onDeviceOnLine() { 
        System.out.println("Hooray: ISY is on line ...");
    }

    public void onSchedulesUpdated() {
        System.out.println("Some of the schedules have been updated; get the lastest schedules");
    }
    
    @Override
    public void onSystemStatus( boolean busy) {
        if (busy) System.out.println("I am busy now; please give me some reprieve and don't ask me for more!");
        else System.out.println("I am ready and at your service");
    }

    @Override
    public void onInternetAccessDisabled() {
        System.out.println("You can no longer reach me through the internet");
    }

    @Override
    public void onInternetAccessEnabled(String url) { 
        System.out.println("I can now be accessed on the internet at "+url); }

    @Override
    public void onNodeEnabled(UDNode udnode, boolean bln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeMovedAsMaster(UDNode udnode, UDGroup udg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeToGroupRoleChanged(UDNode udnode, UDGroup udg, char c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onTriggerStatus(String string, XMLElement xmle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onProgress(String string, XMLElement xmle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDeviceSpecific(String string, String string1, XMLElement xmle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onSystemConfigChanged(String string, XMLElement xmle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onFolderRemoved(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onFolderRenamed(UDFolder udf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNewFolder(UDFolder udf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeParentChanged(UDNode udnode, UDNode udnode1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeHasPendingDeviceWrites(UDNode udnode, boolean bln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeIsWritingToDevice(UDNode udnode, boolean bln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodePowerInfoChanged(UDNode udnode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onLinkerEvent(UDProxyDevice udpd, String string, XMLElement xmle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeRevised(UDProxyDevice udpd, UDNode udnode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeDeviceIdChanged(UDProxyDevice udpd, UDNode udnode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeSupportedTypeInfoChanged(UDProxyDevice udpd, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeErrorCleared(UDProxyDevice udpd, UDNode udnode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeDevicePropertyChanged(UDProxyDevice udpd, UDNode udnode, UDProperty<?> udp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeDevicePropertiesRefreshed(UDProxyDevice udpd, UDNode udnode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNodeDevicePropertiesRefreshedComplete(UDProxyDevice udpd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
